package com.example.dubbo_server.controller;

import com.alibaba.fastjson.JSON;
import com.example.dubbo_server.api.FileService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 無
 * </p>
 *
 * @author NWT)hxl
 * @version 1.0
 * <p>
 * 変更履歴:
 * 2020/08/17 ： NWT)hxl ： 新規作成
 * @date 2020/08/17 19:24
 */
@Controller
public class FileController {

    @DubboReference(version = "1.0.0")
    FileService fileService;

    @RequestMapping("text2bmp")
    public String text2bmp() {
        return "text2bmp";
    }

    @RequestMapping("myPage")
    public String myPage() {
        return "myPage";
    }

    @ResponseBody
    @RequestMapping("uploadTxt")
    public String uploadTxt(@RequestParam("file") MultipartFile file) throws IOException {
        ByteArrayInputStream in = null;
        if (file.isEmpty()) {
            return "file is empty";
        }
        try {
            //将multipartFile抓换为File
            File realFile = File.createTempFile("input", "txt");
            file.transferTo(realFile);
            //转换string为image
            byte[] bytes = fileService.encodeText(realFile);
            //将b作为输入流；
            in = new ByteArrayInputStream(bytes);
            BufferedImage image = ImageIO.read(in);
            File newFile = new File(System.currentTimeMillis() + "output.bmp");
            ImageIO.write(image, "BMP", newFile);
            Map<String, String> map = new HashMap(8);
            map.put("filename", newFile.getName());
            return JSON.toJSONString(map);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (in != null){
                in.close();
            }
        }
        return "upload failure";
    }

    @ResponseBody
    @RequestMapping("uploadBmp")
    public String uploadBmp(@RequestParam("file")MultipartFile file) {
        PrintStream ps = null;
        try {
            if (file.isEmpty()) {
                return "file is empty";
            }

            //将multipartFile抓换为File
            File realFile = File.createTempFile("input", "bmp");
            file.transferTo(realFile);
            String text = fileService.decodeText(realFile);
            File outFile = new File(System.currentTimeMillis() + "output.txt");
            try {
                ps = new PrintStream(new FileOutputStream(outFile));
                ps.append(text);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } finally {
                ps.close();
            }
            Map map = new HashMap();
            map.put("filename", outFile.getName());
            return JSON.toJSONString(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "upload failure";
    }

    @ResponseBody
    @RequestMapping(value = "download", method = RequestMethod.GET)
    public void download(String filename, HttpServletResponse response) throws IOException {
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        if (filename != null){
            File file = new File(filename);
            if (file.exists()){
                try {

                    response.setHeader("content-type", "application/octet-stream");
                    response.setContentType("application/octet-stream");
                    // 下载文件能正常显示中文
                    response.setHeader("Content-Disposition", "attachment;filename=" + filename);
                    byte[] buffer = new byte[1024];
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i!=-1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    os.flush();
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    if (fis != null) {
                        fis.close();
                    }
                    if (bis != null) {
                        bis.close();
                    }
                }
            }
        }
    }
}

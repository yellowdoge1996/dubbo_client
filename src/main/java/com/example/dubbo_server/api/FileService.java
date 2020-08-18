package com.example.dubbo_server.api;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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
 * @date 2020/08/17 19:19
 */
public interface FileService {
    /**
     * 加密文字
     * @param
     * @return
     */
    byte[] encodeText(File file) throws IOException;

    /**
     * 解密图片
     * @param
     * @return
     */
    String decodeText(File file);
}

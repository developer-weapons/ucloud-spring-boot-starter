package com.github.developer.weapons.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.UUID;

/**
 * 专注于处理图片的工具
 */
@Slf4j
public class FileUtils {
    public static String newUUIDFileName(String fileName) {
        if (StringUtils.isBlank(fileName)) {
            return fileName;
        }
        String generatedFileName;
        String[] filePaths = fileName.split("\\.");
        if (filePaths.length > 1) {
            generatedFileName = UUID.randomUUID().toString().replace("-", "") + "." + filePaths[filePaths.length - 1];
        } else {
            return newUUIDPNGFileName();
        }
        return generatedFileName;
    }

    public static String newUUIDPNGFileName() {
        return UUID.randomUUID().toString().replace("-", "") + ".png";
    }

    public static String newLocalFileName(String fileName) {
        String path = System.getProperty("user.dir") + File.separator;
        return newLocalFileName(path, fileName);
    }

    public static String newLocalFileName(String path, String fileName) {
        return path + newUUIDFileName(fileName);
    }

    public static File newFile(String url) {
        File file;
        String localFile = newLocalFileName(url);
        log.info("FILE_UTILS_NEW_INFO, url : {}, localFile : {}", url, localFile);
        file = new File(localFile);
        try {
            org.apache.commons.io.FileUtils.copyURLToFile(new URL(url), file);
        } catch (IOException e) {
            log.error("FILE_UTILS_NEW_ERROR, url : {}", url);
            return null;
        }
        return file;
    }

    public static void deleteFile(File file) {
        file.delete();
    }

    public static void main(String[] args) {
        String x = FileUtils.newLocalFileName("http://luckydraw.cn-bj.ufileos.com/ffb5134b-8070-4d65-be0f-c3b1a0050dbc.jpg");
        System.out.println(x);
    }
}

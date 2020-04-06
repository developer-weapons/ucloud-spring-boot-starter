package com.github.developer.weapons.service;

import cn.ucloud.ufile.UfileClient;
import cn.ucloud.ufile.api.object.ObjectConfig;
import cn.ucloud.ufile.auth.ObjectAuthorization;
import cn.ucloud.ufile.auth.UfileObjectLocalAuthorization;
import cn.ucloud.ufile.bean.PutObjectResultBean;
import com.github.developer.weapons.config.UFileProperties;
import com.github.developer.weapons.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by codedrinker on 2019/6/28.
 */
@Slf4j
public class UFileService {

    @Autowired
    private UFileProperties uFileProperties;

    /**
     * 通过 HTTP 图片 URL 上传
     *
     * @param url HTTP 图片 URL
     * @return
     * @throws FileNotFoundException
     */
    public String upload(String url) {
        File newFile = FileUtils.newFile(url);
        assert newFile != null;
        String uploadedFileName;
        try {
            uploadedFileName = upload(new FileInputStream(newFile), "image/png", newFile.getName());
        } catch (FileNotFoundException e) {
            throw new RuntimeException("new file exception", e);
        }
        FileUtils.deleteFile(newFile);
        return uploadedFileName;
    }

    /**
     * 上传
     *
     * @param fileStream 文件的 input 流
     * @param mimeType   文件类型 eg. image/png
     * @param fileName   文件名称
     * @return
     */
    public String upload(InputStream fileStream, String mimeType, String fileName) {
        try {

            if (uFileProperties.getPublicKey() == null) {
                throw new RuntimeException("ucloud.ufile.publicKey is missing.");
            }

            if (uFileProperties.getPrivateKey() == null) {
                throw new RuntimeException("ucloud.ufile.privateKey is missing.");
            }
            if (uFileProperties.getUploadDomain() == null) {
                throw new RuntimeException("ucloud.ufile.uploadDomain is missing, eg. bucketname.ufile.cn-north-04.ucloud.cn.");
            }

            if (uFileProperties.getBucketName() == null) {
                throw new RuntimeException("ucloud.ufile.bucketName is missing.");
            }
            if (uFileProperties.getDownloadDomain() == null) {
                throw new RuntimeException("ucloud.ufile.downloadDomain is missing, eg. bucketname.cn-bj.ufileos.com.");
            }
            ObjectAuthorization objectAuthorization = new UfileObjectLocalAuthorization(uFileProperties.getPublicKey(), uFileProperties.getPrivateKey());
            ObjectConfig config = new ObjectConfig(uFileProperties.getUploadDomain());
            PutObjectResultBean response = UfileClient.object(objectAuthorization, config)
                    .putObject(fileStream, mimeType)
                    .nameAs( FileUtils.newUUIDFileName(fileName))
                    .toBucket(uFileProperties.getBucketName())
                    .setOnProgressListener((bytesWritten, contentLength) -> {
                    })
                    .execute();
            if (response != null && response.getRetCode() == 0) {
                if (uFileProperties.getBucketType() != null && StringUtils.equals(uFileProperties.getBucketType(), "private")) {
                    if (uFileProperties.getExpiresDuration() == null) {
                        throw new RuntimeException("ucloud.ufile.expiresDuration is missing, eg. 1000.");
                    }
                    return UfileClient.object(objectAuthorization, new ObjectConfig(uFileProperties.getDownloadDomain()))
                            .getDownloadUrlFromPrivateBucket(fileName, uFileProperties.getBucketName(), uFileProperties.getExpiresDuration())
                            .createUrl();

                } else {
                    return UfileClient.object(objectAuthorization, new ObjectConfig(uFileProperties.getDownloadDomain()))
                            .getDownloadUrlFromPublicBucket(fileName, uFileProperties.getBucketName())
                            .createUrl();
                }
            } else {
                log.error("upload error,{}", response);
                return null;
            }
        } catch (Exception e) {
            log.error("upload error,{}", fileName, e);
            return null;
        }
    }
}

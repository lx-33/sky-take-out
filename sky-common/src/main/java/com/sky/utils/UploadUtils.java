package com.sky.utils;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Component
public class UploadUtils {

    /*// 本地文件保存路径
    private static final String LOCAL_DIRECTORY = "D:\\develop\\tliasImage\\";

    public String upload(MultipartFile file) throws IOException {
        //避免文件覆盖
        String originalFilename = file.getOriginalFilename();
        String fileName = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));
        // 将文件保存到本地路径
        String localFilePath = LOCAL_DIRECTORY + fileName;
        file.transferTo(new File(localFilePath));
        // 返回本地路径
        return localFilePath;
    }*/

    private static final String CLOUD_NAME = "dsotc44yk";
    private static final String API_KEY = "339232127966333";
    private static final String API_SECRET = "k186Wgmpw1J_i3H0JHYiCvw97Lo";

    private final Cloudinary cloudinary;

    public UploadUtils() {
        // 初始化 Cloudinary 配置
        cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", CLOUD_NAME,
                "api_key", API_KEY,
                "api_secret", API_SECRET
        ));
    }

    public String upload(MultipartFile file) throws IOException {
        // 使用 Cloudinary 上传文件
        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());

        // 获取返回的 URL
        return (String) uploadResult.get("secure_url");
    }
}

package com.neu.buybook.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/common")
public class CommonController {

    @Value("${web.upload-path}")
    private String path;

    /**
     * 上传文件
     * @param file
     * @return
     */
    @RequestMapping("upload")
    public String upload(MultipartFile file){
        //原文件名
        String originFileName = file.getOriginalFilename();
        //新的文件名
        String newFileName = UUID.randomUUID() + originFileName.substring(originFileName.lastIndexOf("."));
        //目标文件地址
        String desName = path + newFileName;
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(desName);
            //复制
            FileCopyUtils.copy(file.getInputStream(),fileOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newFileName;
    }
}

package com.aili.wechatdome.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface UploadService {
    String uploadImg(MultipartFile file, HttpServletRequest request);

   Boolean deleteImg(String imgURL);

    String uploadVoice(MultipartFile file, HttpServletRequest request);

    String upPic(MultipartFile file, HttpServletRequest request);
}

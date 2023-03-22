package com.aili.wechatdome.controller;

import com.aili.wechatdome.service.UploadService;
import com.aili.wechatdome.utils.result.R;
import com.aili.wechatdome.utils.result.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    private UploadService uploadService;


    //    上传用户头像
    @PostMapping("/uploadUserPic")
    public R<Object> uploadImg(@RequestBody MultipartFile file, HttpServletRequest request) {
        String imgURL = uploadService.uploadImg(file, request);
         return R.buildR(Status.OK, "头像上传成功！",imgURL);
    }

    //上传音频
    @PostMapping("/uploadVoice")
    public R<Object> uploadVoice(@RequestBody MultipartFile file, HttpServletRequest request) {
        String voiceURL = uploadService.uploadVoice(file, request);
        return R.buildR(Status.OK, "上传成功！",voiceURL);
    }

   //上传图片
    @PostMapping("/upPic")
    public R<Object> upPic(@RequestBody MultipartFile file, HttpServletRequest request) {
        String imgURL = uploadService.upPic(file, request);
        return R.buildR(Status.OK, "头像上传成功！",imgURL);
    }




}

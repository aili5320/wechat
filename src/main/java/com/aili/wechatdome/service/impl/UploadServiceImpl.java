package com.aili.wechatdome.service.impl;

import com.aili.wechatdome.service.UploadService;
import com.aili.wechatdome.utils.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Service
public class UploadServiceImpl implements UploadService {
    //获取根路径

    @Value("${userPicPath}")
    private String dirPath;

    @Value("${voicePath}")
    private String voicePath;

    @Value("${sendPicPath}")
    private String sendPicPath;

     @Autowired
     private UploadUtil uploadUtil;
    /**
     * 上传用户头像
     * @param file
     * @param request
     * @return
     */
    @Override
    public String uploadImg(MultipartFile file, HttpServletRequest request) {
        Boolean img = UploadUtil.checkImg(file);
        if (img) {
            String URL = uploadUtil.upload(file, request, dirPath);
            return URL;
        } else {
            return "请上传bmp/gif/jpg/png格式的图片";
        }
    }

//    删除图片
    @Override
    public Boolean deleteImg(String imgURL) {

        return  uploadUtil.deleteFile(dirPath,imgURL);
    }

//    上传语言
    @Override
    public String uploadVoice(MultipartFile file, HttpServletRequest request) {
        String URL = uploadUtil.upload(file, request, voicePath);
        return URL;
    }

//    发送图片
    @Override
    public String upPic(MultipartFile file, HttpServletRequest request) {
        String URL = uploadUtil.upload(file, request, sendPicPath);
        return URL;
    }
}

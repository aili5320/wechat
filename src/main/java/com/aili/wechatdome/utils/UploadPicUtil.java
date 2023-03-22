//package com.aili.wechatdome.utils;
//
//import com.aili.wechatdome.utils.result.R;
//import com.aili.wechatdome.utils.result.Status;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//
//@Component
//public class UploadPicUtil {
//
//    @Value("${userPicPath}")
//    private String userPicPath;
//
//    public R<Object> uploadUserPic(MultipartFile file) {
//
//        if (file.isEmpty()) {
//            return R.buildR(Status.SYSTEM_ERROR, "头像上传失败！");
//        }
//        String fileName = file.getOriginalFilename();
//        File dest = new File(new File(userPicPath).getAbsolutePath() + "/" + fileName);
//        if (!dest.getParentFile().exists()) {
//            dest.getParentFile().mkdirs();
//        }
//        try {
//            file.transferTo(dest); // 保存文件
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return R.buildR(Status.OK, "头像上传成功！", dest.getPath());
//    }
//
//   // 删除头像
//    public static boolean deleteUserPic(String Path) {
//        boolean flag = false;
//        File file = new File(Path);
//        if (!file.exists()) {
//            return flag;
//        } else {
//            return file.delete();
//        }
//    }
//
//}
//

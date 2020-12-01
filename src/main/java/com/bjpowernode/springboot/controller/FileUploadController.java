//package com.bjpowernode.springboot.controller;
//
//import com.bjpowernode.springboot.common.utils.Response;
//import com.bjpowernode.springboot.common.utils.ResponseUtils;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.List;
//
///**
// * 文件上传
// */
//@RestController
//public class FileUploadController {
//    /**
//     * 处理文件上传
//     */
//    @RequestMapping("/fileUploadController")
//    public Response fileUpload(MultipartFile filename) throws Exception {
//        System.out.println(filename.getOriginalFilename());
//        //打印文件上传名称
//        filename.transferTo(new File("E:/" + filename.getOriginalFilename()));//文件保存
//        return ResponseUtils.success();
//    }
//
//
//    @PostMapping("/upload")
//    public Response upload(@RequestParam("files") List<MultipartFile> files) {
//        if (files.isEmpty()) {
//            return ResponseUtils.success("上传失败，未选择文件");
//        }
//        for (MultipartFile file : files) {
//            String fileName = file.getOriginalFilename();
//            // 获取文件后缀名
//            String suffixName = fileName.substring(fileName.lastIndexOf("."));
//            // 重新生成文件名
//            String fName = System.currentTimeMillis() + suffixName;
//            System.out.println("文件名：" + fName);
//            String filePath = "E:/";
//            File dest = new File(filePath + fName);
//            try {
//                file.transferTo(dest);
//                System.out.println(fName + "上传成功！");
//            } catch (IOException e) {
//                System.out.println(fName + "上传异常！" + e);
//                return ResponseUtils.success("上传异常");
//            }
//        }
//        return ResponseUtils.success();
//    }
//
//
//}

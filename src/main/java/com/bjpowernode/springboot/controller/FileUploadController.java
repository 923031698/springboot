package com.bjpowernode.springboot.controller;

import com.bjpowernode.springboot.common.utils.ResponseUtils;
import com.bjpowernode.springboot.common.utils.Response;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * 文件上传
 */
@RestController
public class FileUploadController {
    /**
     * 处理文件上传
     */
    @RequestMapping("/fileUploadController")
    public Response fileUpload(MultipartFile filename) throws Exception {
        System.out.println(filename.getOriginalFilename());
        //打印文件上传名称
        filename.transferTo(new File("D:/" + filename.getOriginalFilename()));//文件保存
        return ResponseUtils.success();
    }
}

package com.bjpowernode.springboot.controller;

import com.bjpowernode.springboot.common.utils.ResponseUtils;
import com.bjpowernode.springboot.model.common.Response;
import com.bjpowernode.springboot.model.elasticsearch.Course;
import com.bjpowernode.springboot.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: bjb
 * @Description:
 * @Date: 2020-06-21 22:23:17
 * @Company: 乐木几网络科技有限公司
 */
@RestController()
public class CourseController {
    @Autowired
    CourseService courseService;
    @PostMapping("/insert")
    public Response  insert(Course course){
        return ResponseUtils.success(courseService.insert(course));
    }
}
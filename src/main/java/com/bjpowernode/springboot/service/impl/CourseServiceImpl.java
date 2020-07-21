package com.bjpowernode.springboot.service.impl;

import com.bjpowernode.springboot.common.utils.ResponseUtils;
import com.bjpowernode.springboot.common.utils.Response;
import com.bjpowernode.springboot.model.elasticsearch.Course;
import com.bjpowernode.springboot.repository.CourseRepository;
import com.bjpowernode.springboot.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: bjb
 * @Description: //TODO
 * @Date: 2020-06-21 22:09:03
 */
@Service
public class CourseServiceImpl  implements CourseService {
    @Autowired
    CourseRepository courseRepository;

    public Response insert(Course course){
        courseRepository.index(course);
        return  ResponseUtils.success(course);
    }
}

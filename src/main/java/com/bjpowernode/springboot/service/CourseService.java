package com.bjpowernode.springboot.service;

import com.bjpowernode.springboot.common.utils.Response;
import com.bjpowernode.springboot.model.domian.elasticsearch.Course;

public interface CourseService {

    Response insert(Course course);
}

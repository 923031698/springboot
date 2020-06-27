package com.bjpowernode.springboot.service;

import com.bjpowernode.springboot.model.common.Response;
import com.bjpowernode.springboot.model.elasticsearch.Course;

public interface CourseService {

     Response insert(Course course);
}

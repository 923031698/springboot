package com.bjpowernode.springboot.repository;

import com.bjpowernode.springboot.model.elasticsearch.Course;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface CourseRepository extends ElasticsearchRepository<Course,String> {
}

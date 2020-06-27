package com.bjpowernode.springboot.service;

import com.bjpowernode.springboot.model.elasticsearch.Student;

import java.util.List;

public interface StudentService {

    /**
     * @Author bjb
     * @Description 创建学生
     * @Date 2020/6/27 14:39
     */
    void index(Student student);

    void delete(Student student);

    void save(Student student);

    void saveAll(List<Student> list);

    Student findById(String id);

    Iterable<Student> findAll();
}

package com.bjpowernode.springboot.controller;

import com.bjpowernode.springboot.common.utils.ResponseUtils;
import com.bjpowernode.springboot.model.common.Response;
import com.bjpowernode.springboot.model.elasticsearch.Student;
import com.bjpowernode.springboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: bjb
 * @Description: //TODO
 * @Date: 2020-06-27 14:43:25
 */
@RestController
public class StudentController {
    @Autowired
    StudentService studentService;

    @PostMapping(value = "/student/insert")
    public Response insert(Student student) {
        studentService.index(student);
        return ResponseUtils.success();
    }


    @PostMapping(value = "/student/delete")
    public   Response delete(Student student){
        studentService.delete(student);
        return ResponseUtils.success();
    }
    @PostMapping(value = "/student/save")
    public  Response save(Student student){
        studentService.save(student);
        return ResponseUtils.success();
    }
    @PostMapping(value = "/student/saveAll")
    public  Response saveAll(@RequestBody List<Student> list){
        studentService.saveAll(list);
        return ResponseUtils.success();
    }
    @GetMapping(value = "/student/findById")
    public  Response findById(String id){
        return ResponseUtils.success(studentService.findById(id));
    }

    @GetMapping(value = "/student/findAll")
    public   Response findAll( ){
        return ResponseUtils.success(studentService.findAll());
    }

}

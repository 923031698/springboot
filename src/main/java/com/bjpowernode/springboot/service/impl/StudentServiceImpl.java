package com.bjpowernode.springboot.service.impl;

import cn.hutool.core.util.IdUtil;
import com.bjpowernode.springboot.model.elasticsearch.Student;
import com.bjpowernode.springboot.repository.StudentRepository;
import com.bjpowernode.springboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @Author: bjb
 * @Description: //TODO
 * @Date: 2020-06-27 14:13:50
 */
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentRepository studentRepository;

    /**
     * @Author bjb
     * @Description 新增索引
     * @Date 2020/6/27 15:12
     */
    public void index(Student student) {
     //   studentRepository.index(Student.builder().build());
    }


    /**
     * @Author bjb
     * @Description 删除
     * @Date 2020/6/27 15:12
     */
    public void delete(Student student) {
        studentRepository.delete(student);
    }


    /**
     * @Author bjb
     * @Description 添加 /修改
     * @Date 2020/6/27 15:12
     */
    public void save(Student student) {
        studentRepository.save(student);
    }


    /**
     * @Author bjb
     * @Description 添加 /修改 (批量)
     * @Date 2020/6/27 15:12
     */
    public void saveAll(List<Student> list) {
        for (Student student : list) {
            student.setId(IdUtil.objectId());
        }
        studentRepository.saveAll(list);
    }

    /**
     * @Author bjb
     * @Description 根据id查询
     * @Date 2020/6/27 15:12
     */
    public Student findById(String id) {
        Optional<Student> student = studentRepository.findById(id);
        return student.get();
    }


    /**
     * @Author bjb
     * @Description 查询所有
     * @Date 2020/6/27 15:12
     */
    public Iterable<Student> findAll( ) {
        System.out.println("12312312321");

        //查询所有
        Iterable<Student> list = studentRepository.findAll();
//        Iterable<Student> ageList = studentRepository.findAll(Sort.by(Sort.Order.desc("age")));
//        Page<Student> pageList = studentRepository.findAll(PageRequest.of(0, 10));
//        List<String> idList = new ArrayList<>();
       // Iterable<Student> allById = studentRepository.findAllById(idList);
        return list;
    }


}

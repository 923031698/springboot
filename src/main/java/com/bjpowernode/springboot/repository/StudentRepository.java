package com.bjpowernode.springboot.repository;

import com.bjpowernode.springboot.model.domian.elasticsearch.Student;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @Author: bjb
 * @Description: //TODO
 * @Date: 2020-06-27 14:05:09
 * @Company: 乐木几网络科技有限公司
 */
public interface StudentRepository extends ElasticsearchRepository<Student,String> {
}

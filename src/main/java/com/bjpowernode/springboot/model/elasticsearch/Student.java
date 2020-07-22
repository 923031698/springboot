package com.bjpowernode.springboot.model.elasticsearch;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * @Author: bjb
 * @Date: 2020-06-27 13:49:25
 * @Company: 乐木几网络科技有限公司
 */
@Document(indexName = "student", type = "_doc")
@Data
public class Student implements Serializable {
    @Id
    private String id;
    @Field(type = FieldType.Text)
    private String name;

    @Field(type = FieldType.Integer)
    private Integer age;


    @Field(type = FieldType.Integer)
    private Integer sex;

    @Field(type = FieldType.Integer)
    private  Integer height;

}

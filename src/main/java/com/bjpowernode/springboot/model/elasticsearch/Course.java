package com.bjpowernode.springboot.model.elasticsearch;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * @Author: bjb
 * @Description: 课程表检索
 * @Date: 2020-06-21 22:06:33
 * @Company: 乐木几网络科技有限公司
 */
@Document(indexName = "course",type = "_doc")
@Data
public class Course implements Serializable {
    @Id
    private  String id;

   @Field(analyzer = "ik_max_word",searchAnalyzer = "ik_max_word",type = FieldType.Text)
    private String courseTitle;
    /**
     * 課程老師
     */
    private String courseTeacher;

    private String courseHot;
    /**
     * 课程价格
     */
    private String coursePrice;
    /**
     * 课程图片
     */
    private  String courseImage;
    /**
     * 課程連接
     */
    private String courseLink;
    /**
     * 课程介绍
     */
    private String courseIntroduce;




}
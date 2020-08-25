import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.json.JSONUtil;
import com.bjpowernode.springboot.model.elasticsearch.Student;
import com.bjpowernode.springboot.model.elasticsearch.StudentVo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Jdk8_Test {


    public static void main(String[] args) {
        StudentVo student1 = new StudentVo();
        student1.setId("1");
        student1.setAge(1);
        student1.setHeight(1);

        Student student2 = new Student();
        BeanUtil.copyProperties(student1, student2, new CopyOptions().ignoreNullValue().setIgnoreProperties(new String[]{"id", "age"}));
        System.out.println(student2);


    /*    List<StudentVo> list = new ArrayList<StudentVo>();
        list.add(student1);
        list.add(student2);
*/


   /*     List<Student> studentList = list.stream().filter(a -> 1 == a.getAge()).map(a -> {
            Student student = new Student();
            BeanUtil.copyProperties(a, student,new CopyOptions().setIgnoreNullValue());
            Student student3 = JSONUtil.toBean(JSONUtil.toJsonStr(a), Student.class);
            return student;
          // ;
        }).collect(Collectors.toList());
*/
        //  System.out.println(studentList);

    }
}
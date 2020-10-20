import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Stream;

public class Jdk8_Test {


    public static void main(String[] args) throws ParseException {
      /*  StudentVo student1 = new StudentVo();
        student1.setId("1");
        student1.setAge(1);
        student1.setHeight(1);

        Student student2 = new Student();
        BeanUtil.copyProperties(student1, student2, new CopyOptions().ignoreNullValue().setIgnoreProperties(new String[]{"id", "age"}));
        System.out.println(student2);*/


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

      /*  String str1 = "2020-03-01";
        String str2 = "2020-10-16";
        getBetweenDate(str1, str2);*/

        //方法1
        String strDate = "2019-02-19";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = new GregorianCalendar();
        Date date1 = sdf.parse(strDate);
        calendar.setTime(date1); //放入你的日期
        System.out.println("天数为=" + calendar.getActualMaximum(Calendar.DAY_OF_MONTH));

        //方法2
        System.out.println("天数为=" + new Date(2007,02,0).getDate());

    }

    /**
     * 获取一段时间的每一天日期
     *
     * @param start
     * @param end
     * @return
     * @throws Exception
     */

    public static List<String> getBetweenDate(String start, String end) {
        List<String> list = new ArrayList<>();
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);
        long distance = ChronoUnit.DAYS.between(startDate, endDate);
        if (distance < 1) {
            return list;
        }
        Stream.iterate(startDate, d -> d.plusDays(1)).limit(distance + 1).forEach(f -> list.add(f.toString()));
        return list;
    }

//调用  List<String> betweenDate = getBetweenDate("2019-02-01", "2019-05-31");
}
package com.jamesp1949.greendaodemo.util;

import com.jamesp1949.greendaodemo.bean.GirlFriend;
import com.jamesp1949.greendaodemo.bean.Student;
import com.jamesp1949.greendaodemo.bean.Teacher;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JamesP949 on 2017/3/17.
 * Function:
 */

public class DataUtil {
    public static List<Teacher> getSimple() {
        List<Teacher> teachers = new ArrayList<>();
        Teacher teacher = new Teacher();
        teacher.setName("张老师");
        teachers.add(teacher);
        teacher = new Teacher();
        teacher.setName("刘老师");
        teachers.add(teacher);
        return teachers;
    }


    public static GirlFriend getGirlFriend() {
        GirlFriend girlFriend = new GirlFriend();
        String name = "茉莉" + String.valueOf(System.currentTimeMillis());
        girlFriend.setName(name);
        KLog.e("女友名字：" + name);
        return girlFriend;
    }

    public static List<Student> getStudents() {
        List<Student> students = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            Student student = new Student();
            student.setName("小明" + String.valueOf(System.currentTimeMillis()));
            student.setScore((float) (Math.random() * 60 + 40));
            students.add(student);
            KLog.e("学生：" + student.getName());
        }

        return students;
    }
}

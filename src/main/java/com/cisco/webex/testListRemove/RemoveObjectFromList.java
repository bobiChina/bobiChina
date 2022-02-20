package com.cisco.webex.testListRemove;

import java.util.ArrayList;
import java.util.List;

/**
 * @author biebi@cisco.com
 * @date 2022/2/17
 */
public class RemoveObjectFromList {

    public static void main(String[] args) {
        List<Student> students = initStudent();
        List<Integer> removeNo = new ArrayList<>();
        removeNo.add(1);
        removeNo.add(4);
        removeNo.add(6);

        for (Integer no : removeNo) {
            for (int i = students.size()-1; i >= 0; i--) {
                Student student = students.get(i);
                if(student.getStuNo() == no){
                    students.remove(student);
                }
            }
        }

        students.stream().forEach(student -> System.out.println(student));

    }

    public static List<Student> initStudent(){
        List<Student> studentList = new ArrayList<>();
        Student student1 = new Student(1,"张三");
        Student student2 = new Student(2,"李四");
        Student student3 = new Student(3,"王五");
        Student student4 = new Student(4,"赵六");
        Student student5 = new Student(5,"朱七");
        Student student6 = new Student(6,"韩八");
        studentList.add(student1);
        studentList.add(student2);
        studentList.add(student3);
        studentList.add(student4);
        studentList.add(student5);
        studentList.add(student6);
        return studentList;
    }

}

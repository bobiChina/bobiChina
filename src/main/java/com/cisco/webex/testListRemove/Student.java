package com.cisco.webex.testListRemove;

/**
 * @author biebi@cisco.com
 * @date 2022/2/17
 */
public class Student {

    private Integer stuNo;
    private String name;

    public Student(Integer stuNo, String name) {
        this.stuNo = stuNo;
        this.name = name;
    }

    public Integer getStuNo() {
        return stuNo;
    }

    public void setStuNo(Integer stuNo) {
        this.stuNo = stuNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "stuNo=" + stuNo +
                ", name='" + name + '\'' +
                '}';
    }
}

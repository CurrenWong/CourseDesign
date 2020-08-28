package com.courseDesign.javabean;

import java.sql.Date;

public class university_enroll_student {
    public int id;
    public int studentid;
    public int universityid;
    public int year;
    public String type;
    public int class_id;
    public int is_approved;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentid() {
        return studentid;
    }

    public void setStudentid(int studentid) {
        this.studentid = studentid;
    }

    public int getUniversityid() {
        return universityid;
    }

    public void setUniversityid(int universityid) {
        this.universityid = universityid;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
    }

    public int getIs_approved() {
        return is_approved;
    }

    public void setIs_approved(int is_approved) {
        this.is_approved = is_approved;
    }
}

package com.courseDesign.servlet;

import java.util.ArrayList;

import com.courseDesign.dao.University_Enroll_StudentDao;
import com.courseDesign.object.StudentAndVolunteer;

public class Run {
    public static void main(String[] args) {
        University_Enroll_StudentDao universityEnrollStudentDao = new University_Enroll_StudentDao();
        ArrayList<StudentAndVolunteer> studentAndVolunteers = universityEnrollStudentDao.searchVolunteer(1, "统招");
        System.out.println(studentAndVolunteers.size());
    }
}

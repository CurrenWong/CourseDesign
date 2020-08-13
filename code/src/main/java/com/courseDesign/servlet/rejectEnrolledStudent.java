package com.courseDesign.servlet;

import com.alibaba.fastjson.JSONObject;
import com.courseDesign.dao.BaseDao;
import com.courseDesign.javabean.university_enroll_student;
import com.courseDesign.javabean.volunteer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.SimpleFormatter;

@WebServlet(name = "rejectEnrolledStudent")
public class rejectEnrolledStudent extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");

        //设置缓冲区编码
        response.setCharacterEncoding("UTF-8");
        //浏览器打开页面时编码设置和缓冲区相同
        response.setHeader("content-type","text/html;charset=UTF-8");

        request.setCharacterEncoding("UTF-8");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");



        // 读取请求内容
        //批量退选按钮，多个推选复选框都选后，点击批量退选按钮，前台传送学生id？

        String students_id[]= request.getParameterValues("students_id");
        university_enroll_student universityenrollstudent=new university_enroll_student();
        BaseDao baseDao=new BaseDao();
        int id=0;
        if(students_id ==null){
            System.out.println("没进行批量退选");
        }else {
            for(int i=0;i<students_id.length;i++){
                id=Integer.parseInt(students_id[i]);
                String sql="update university_enroll_student set is_approved=-1 where id=?";
                baseDao.executeUpdate(sql,id);
            }

        }
        
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

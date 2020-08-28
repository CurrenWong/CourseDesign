package com.courseDesign.servlet;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.courseDesign.dao.BaseDao;
import com.courseDesign.dao.University_Enroll_StudentDao;
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
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.SimpleFormatter;

@WebServlet(name = "approveEnrolledStudent")
public class approveEnrolledStudent extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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



        JSONArray studentId = (JSONArray) JSONArray.parse(request.getParameter("studentId"));

        University_Enroll_StudentDao uesDao=new University_Enroll_StudentDao();

        if(studentId.size()!=0){
            for(int i=0;i<studentId.size();i++){
                int j= Integer.parseInt(studentId.getString(i));
                university_enroll_student student=uesDao.SearchSignal1(j);
                if(!"null".equals(String.valueOf(student.getStudentid()))&&!"0".equals(String.valueOf(student.getStudentid()))){
                    uesDao.updateApproved(j);
                }else {
                    response.sendError(403, "提交错误，请刷新后重试");
                    break;
                }
            }
        }else {
            response.sendError(403, "提交错误，请刷新后重试");
        }
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}

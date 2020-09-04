package com.courseDesign.servlet;

import com.alibaba.fastjson.JSONObject;
import com.courseDesign.dao.BaseDao;
import com.courseDesign.javabean.student;
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
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "submitVolunteer")
public class submitVolunteer extends HttpServlet {
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
        PrintWriter out=response.getWriter();



        // 读取请求内容
        int volunteerno=Integer.parseInt(request.getParameter("volunteerNo").toString());
        int batch=Integer.parseInt(request.getParameter("batch").toString());
        int is_adjust=Integer.parseInt(request.getParameter("isAdjust").toString());
        int studentid=Integer.parseInt(request.getParameter("studentId").toString());
        int universityid=Integer.parseInt(request.getParameter("universityId").toString());
        int classid=Integer.parseInt(request.getParameter("classId").toString());
        String type=request.getParameter("type").toString();
        int classRank= Integer.parseInt(request.getParameter("classRank"));

        BaseDao baseDao=new BaseDao();

        String sql="insert into volunteer（volunteerno,batch,is_adjust,studentid,universityid,classid,type,class_rank） values(?,?,?，?，?，?，?，?)";
        baseDao.executeUpdate(sql,volunteerno,batch,is_adjust,studentid,universityid,classid,type,classRank);


    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

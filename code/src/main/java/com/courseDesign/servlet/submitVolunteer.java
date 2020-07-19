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
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(),"utf-8"));
        String line = null;
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }


        HashMap map1 = JSONObject.parseObject(sb.toString(), HashMap.class);
        int volunteerid= Integer.parseInt(map1.get("volunteerid").toString());
        int volunteerno=Integer.parseInt(map1.get("volunteerno").toString());
        int batch=Integer.parseInt(map1.get("batch").toString());
        int is_adjust=Integer.parseInt(map1.get("is_adjust").toString());
        int studentid=Integer.parseInt(map1.get("studentid").toString());
        int universityid=Integer.parseInt(map1.get("universityid").toString());
        int classid=Integer.parseInt(map1.get("classid").toString());
        String type=map1.get("type").toString();

        BaseDao baseDao=new BaseDao();

        String sql="insert into volunteer（volunteerid,volunteerno,batch,is_adjust,studentid,universityid,classid,type） values(?,?,?，?，?，?，?，?)";
        baseDao.executeUpdate(sql,volunteerid,volunteerno,batch,is_adjust,studentid,universityid,classid,type);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

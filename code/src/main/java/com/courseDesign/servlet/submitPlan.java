package com.courseDesign.servlet;

import com.alibaba.fastjson.JSONObject;
import com.courseDesign.dao.BaseDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;

@WebServlet(name = "submitPlan",urlPatterns = "/submitPlan.do")
public class submitPlan extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setHeader("content-type","text/html;charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        PrintWriter out=response.getWriter();
        // 读取请求内容
  
        int planid= Integer.parseInt(request.getParameter("planId").toString());
        int year =Integer.parseInt(request.getParameter("year").toString());
        int regionid=Integer.parseInt(request.getParameter("regionId").toString());
        int classid=Integer.parseInt(request.getParameter("classId").toString());
        int universotyid=Integer.parseInt(request.getParameter("universityId").toString());
        int number=Integer.parseInt(request.getParameter("number").toString());
        int is_approved=Integer.parseInt(request.getParameter("isApproved").toString());
        String approved_by=request.getParameter("approvedBy").toString();
        BaseDao baseDao=new BaseDao();
        String sql = "insert into plan (planid,year,regionid,classid,universityid,number,is_approved,approved_by) values(null,?,?,?,?,?,?,?)";
        baseDao.executeUpdate(sql,year,regionid,classid,universotyid,number,is_approved,approved_by);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}

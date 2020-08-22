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

@WebServlet(name = "rejectPlan",urlPatterns = "/rejectPlan.do")
public class rejectPlan  extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setHeader("content-type","text/html;charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        PrintWriter out=response.getWriter();
    
        int is_approved=Integer.parseInt(request.getParameter("is_approved").toString());
        BaseDao baseDao=new BaseDao();
        String sql="update plan set is_approve=0";
        baseDao.executeUpdate(sql,is_approved);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}



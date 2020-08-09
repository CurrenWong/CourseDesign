package com.courseDesign.servlet;

import com.alibaba.fastjson.JSONObject;
import com.courseDesign.dao.CountrydepartDao;
import com.courseDesign.dao.ManagerDao;
import com.courseDesign.dao.StudentDao;
import com.courseDesign.dao.UniversityDao;
import com.courseDesign.javabean.education_department;
import com.courseDesign.javabean.manager;
import com.courseDesign.javabean.student;
import com.courseDesign.javabean.university;

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

@WebServlet(name = "loginservlet",urlPatterns = "/loginservlet.do")
public class loginservlet  extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        response.setHeader("content-type","text/html;charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        PrintWriter out=response.getWriter();
        // 读取请求内容0
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(),"utf-8"));
        String line = null;
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        HashMap map1 = JSONObject.parseObject(sb.toString(), HashMap.class);
        String identity=map1.get("identity").toString();
        String username=map1.get("username").toString();
        String password=map1.get("password").toString();
        if(identity.equals("student")){
            StudentDao studentDao=new StudentDao();
            student students=studentDao.login(username,password);
            if(students!=null){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id",students.getId());
                jsonObject.put("identity","student");
            }else{
                response.sendError(402, "用户名或密码错误!!!" );
            }
        }
   
          else if(identity.equals("university")){
            UniversityDao universityDao=new UniversityDao();
            university university=universityDao.login(username,password);
            if(university!=null){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id",university.getUniversityid());
                jsonObject.put("identity","university");
            }
            else{
                response.sendError(402, "用户名或密码错误!!!" );
            }
        }
                
                else if(identity.equals("manager")){
            ManagerDao managerDao=new ManagerDao();
            manager ma=managerDao.login(username,password);
            if(ma!=null){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id",ma.getId());
                jsonObject.put("identity","manager");
            }else{
                response.sendError(402, "用户名或密码错误!!!" );
            }
        }else {
            CountrydepartDao countrydepartDao=new CountrydepartDao();
            education_department edu=countrydepartDao.login(username,password);
            if(edu!=null){
                JSONObject jsonObject=new JSONObject();
                jsonObject.put("id",edu.getId());
                jsonObject.put("identity","education_department");
            }else{
                response.sendError(402, "用户名或密码错误!!!" );
            }
        }

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}

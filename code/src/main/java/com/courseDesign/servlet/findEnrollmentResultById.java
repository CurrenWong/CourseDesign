package com.courseDesign.servlet;

import com.alibaba.fastjson.JSONObject;
import com.courseDesign.dao.BaseDao;
import com.courseDesign.javabean.student;
import com.courseDesign.javabean.university_enroll_student;

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
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "findEnrollmentResultById", urlPatterns = "/findEnrollmentResultById.do")
public class findEnrollmentResultById extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        int studentid = Integer.parseInt(request.getParameter("studentid").toString());
        

        university_enroll_student ues=new university_enroll_student(); //录取信息
        BaseDao baseDao=new BaseDao();
        List<Map<String,Object>> maps=new ArrayList<>();
        String sql="select * from university_enroll_student where studentid=?";
        maps=baseDao.executeQuery(sql,studentid);
        if(maps.size()!=0){
            PrintWriter out=response.getWriter();
            Map<String, Object> map=maps.get(0);//整个maps只有一条map
            ues.setId((Integer) map.get("id"));
            ues.setStudentid((Integer) map.get("studentid"));
            String sql3="select * from student where id=?";
            List<Map<String,Object>> maps3=baseDao.executeQuery(sql3,map.get("studentid"));
            ues.setUniversityid((Integer) map.get("universityid"));
            String sql2="select * from university where universityid=?";
            List<Map<String,Object>> maps2=baseDao.executeQuery(sql2,map.get("universityid"));
            Date d= (Date) map.get("year");
            int i=d.getYear()+1900;
            ues.setYear(i);
            ues.setType((String) map.get("type"));
            ues.setClass_id((Integer) map.get("class_id"));
            String sql4="select * from major where classid=?";
            List<Map<String,Object>> maps4=baseDao.executeQuery(sql4,map.get("class_id"));
            ues.setIs_approved((Integer) map.get("is_approved"));

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id",ues.getId());
            jsonObject.put("studentId",ues.getStudentid());
            jsonObject.put("universityId",ues.getUniversityid());
            jsonObject.put("universityName", maps2.get(0).get("universityname"));
            jsonObject.put("studentName",maps3.get(0).get("name"));
            jsonObject.put("year",ues.getYear()+"");
            jsonObject.put("type",ues.getType());
            jsonObject.put("classId",ues.getClass_id());
            jsonObject.put("className",maps4.get(0).get("nclass"));
            jsonObject.put("isApproved",ues.getIs_approved());

            out.print(jsonObject.toString());
        }else{
            response.sendError(403,"无录取结果");
        }

    }
}

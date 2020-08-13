package com.courseDesign.servlet;

import com.alibaba.fastjson.JSONObject;
import com.courseDesign.dao.BaseDao;
import com.courseDesign.dao.StudentDao;
import com.courseDesign.javabean.student;

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

@WebServlet(name = "findStudentByTestid",urlPatterns = "/findStudentByTestid.do")
public class findStudentByTestid extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        response.setHeader("content-type","text/html;charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        PrintWriter out=response.getWriter();

        int testid= Integer.parseInt(request.getParameter("testId").toString());
        String name=request.getParameter("name");

        StudentDao studentDao=new StudentDao();
        student student=studentDao.searchStudentbytestid(testid);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id",student.getId());
        jsonObject.put("testId",student.getTestid());
        jsonObject.put("name",student.getName());
        jsonObject.put("gender",student.getGender());
        jsonObject.put("regionId",student.getRegionid());
        jsonObject.put("totalScore",student.getTotal_score());
        jsonObject.put("rank",student.getRank());
        jsonObject.put("chinesrScore",student.getChinese_score());
        jsonObject.put("mathScore",student.getMath_score());
        jsonObject.put("englishScore",student.getEnglish_score());
        jsonObject.put("compScore",student.getComp_score());
        jsonObject.put("kind",student.getKind());

        out.print(jsonObject.toString());

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}

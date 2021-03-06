package com.courseDesign.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.courseDesign.dao.StudentDao;
import com.courseDesign.javabean.student;

@WebServlet(name = "FindStudentById", urlPatterns = "/findStudentById.do")
public class findStudentById extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        response.setHeader("content-type", "text/html;charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");

        int id = Integer.parseInt(request.getParameter("id").toString());

        StudentDao studentDao = new StudentDao();
        student student = studentDao.searchStudent(id);
        // 若学生存在
        if (!"null".equals(String.valueOf(student.getId())) && !"0".equals(String.valueOf(student.getId()))) {
            PrintWriter out = response.getWriter();

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", student.getId());
            jsonObject.put("testId", student.getTestid());
            jsonObject.put("name", student.getName());
            jsonObject.put("gender", student.getGender());
            jsonObject.put("regionId", student.getRegionid());
            jsonObject.put("totalScore", student.getTotal_score());
            jsonObject.put("rank", student.getRank());
            jsonObject.put("chinesrScore", student.getChinese_score());
            jsonObject.put("mathScore", student.getMath_score());
            jsonObject.put("englishScore", student.getEnglish_score());
            jsonObject.put("compScore", student.getComp_score());
            jsonObject.put("kind", student.getKind());
            out.print(jsonObject.toString());
        } else {
            // 若学生不存在，返回状态码402
            response.sendError(402, "id输入错误");
        }

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}

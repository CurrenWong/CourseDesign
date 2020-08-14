package com.courseDesign.servlet;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.courseDesign.dao.MajorDao;
import com.courseDesign.dao.StudentDao;
import com.courseDesign.dao.UniversityDao;
import com.courseDesign.javabean.major;
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
import java.util.ArrayList;
import java.util.HashMap;

@WebServlet(name = "findEnrolledStudent", urlPatterns = "/findEnrollStudent.do")
public class findEnrolledStudent extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        response.setHeader("content-type", "text/html;charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");

        int universityId = Integer.parseInt(request.getParameter("universityId").toString());

        UniversityDao universityDao = new UniversityDao();
        MajorDao majorDao = new MajorDao();
        ArrayList<volunteer> volunteers = universityDao.searchVolunteer(universityId);
        if(!(volunteers.size() ==0)){
            PrintWriter out = response.getWriter();
            JSONArray jsonArray = new JSONArray();
            for (int i = 0; i < volunteers.size(); i++) {
                JSONObject jsonObject = new JSONObject();
                JSONArray jsonArray1 = new JSONArray();
                ArrayList<major> majors = majorDao.searchMajor(volunteers.get(i).getClassid());
                StudentDao studentDao = new StudentDao();
                student student = studentDao.searchStudent(volunteers.get(i).getStudentid());
                for (int j = 0; j < majors.size(); j++) {
                    JSONObject anotherjsonObject = new JSONObject();
                    anotherjsonObject.put("classId", volunteers.get(i).getClassid());
                    anotherjsonObject.put("regionId", student.getRegionid());
                    anotherjsonObject.put("nmajor", majors.get(j).getNmajor());
                    anotherjsonObject.put("nclass", majors.get(j).getNclass());
                    anotherjsonObject.put("batch", volunteers.get(i).getBatch());
                    anotherjsonObject.put("kind", majors.get(j).getKind());
                    jsonArray1.add(anotherjsonObject);
                }
                jsonObject.put("majors", jsonArray1);
                jsonObject.put("id", student.getId());
                jsonObject.put("testId", student.getTestid());
                jsonObject.put("name", student.getName());
                jsonObject.put("gender", student.getGender());
                jsonObject.put("regionId", student.getRegionid());
                jsonObject.put("totalScore", student.getTotal_score());
                jsonObject.put("rank", student.getRank());

                jsonArray.add(jsonObject);
            }
            out.print(jsonArray.toString());
        }else{
            response.sendError(403, "访问错误，请刷新后重试");
        }

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}

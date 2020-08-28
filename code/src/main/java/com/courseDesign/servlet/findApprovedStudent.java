package com.courseDesign.servlet;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.courseDesign.dao.MajorDao;
import com.courseDesign.dao.StudentDao;
import com.courseDesign.dao.University_Enroll_StudentDao;
import com.courseDesign.javabean.major;
import com.courseDesign.javabean.student;
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
import java.util.ArrayList;
import java.util.HashMap;

@WebServlet(name = "findApprovedStudent",urlPatterns = "/findApprovedStudent.do")
public class findApprovedStudent extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        response.setHeader("content-type","text/html;charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");


        int universityId= Integer.parseInt(request.getParameter("universityId").toString());

        StudentDao studentDao =new StudentDao();
        University_Enroll_StudentDao universityEnrollStudentDao=new University_Enroll_StudentDao();
        MajorDao majorDao=new MajorDao();
        ArrayList<university_enroll_student> universityEnrollStudents=universityEnrollStudentDao.searchUES1(universityId);

        if (universityEnrollStudents.size()!=0){
            PrintWriter out=response.getWriter();
            JSONArray jsonArray=new JSONArray();
            for(int i=0;i<universityEnrollStudents.size();i++){
                JSONObject jsonObject=new JSONObject();
                student student=studentDao.searchStudent(universityEnrollStudents.get(i).getStudentid());
                jsonObject.put("id",student.getId());
                jsonObject.put("testId",student.getTestid());
                jsonObject.put("name",student.getName());
                jsonObject.put("gender",student.getGender());
                jsonObject.put("regionId",student.getRegionid());
                jsonObject.put("totalScore",student.getTotal_score());
                jsonObject.put("rank",student.getRank());
                JSONArray jsonArray1 = new JSONArray();
                ArrayList<volunteer> volunteers =studentDao.searchVolunteer(universityEnrollStudents.get(i).getStudentid(),universityId);
                for (int m = 0; m < volunteers.size(); m++) {
                    major major=majorDao.searchMajor(volunteers.get(m).getClassid());
                    JSONObject anotherjsonObject=new JSONObject();
                    anotherjsonObject.put("classId",volunteers.get(m).getClassid());
                    anotherjsonObject.put("regionId",student.getRegionid());
                    anotherjsonObject.put("nmajor",major.getNmajor());
                    anotherjsonObject.put("nclass",major.getNclass());
                    anotherjsonObject.put("batch",volunteers.get(m).getBatch());
                    anotherjsonObject.put("kind",major.getKind());
                    jsonArray1.add(anotherjsonObject);
                }
                jsonObject.put("majors",jsonArray1);
                String s=majorDao.searchClassname(universityEnrollStudents.get(i).getClass_id());
                jsonObject.put("approvedMajorName",s);
                jsonObject.put("approvedType",universityEnrollStudents.get(i).getType());
                jsonArray.add(jsonObject);
            }
            out.print(jsonArray.toString());
        }else {
            response.sendError(403, "访问错误，请刷新后重试");
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}

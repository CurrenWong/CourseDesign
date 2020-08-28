package com.courseDesign.servlet;

import com.courseDesign.dao.University_Enroll_StudentDao;
import com.courseDesign.javabean.plan;
import com.courseDesign.javabean.university_enroll_student;
import com.courseDesign.object.StudentAndVolunteer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;

@WebServlet(name = "enroll",urlPatterns = "/enroll.do")
public class enroll extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        response.setHeader("content-type","text/html;charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");


        int batch= Integer.parseInt(request.getParameter("batch").toString());
        String type=request.getParameter("type");
        University_Enroll_StudentDao universityEnrollStudentDao=new University_Enroll_StudentDao();
        ArrayList<StudentAndVolunteer> studentAndVolunteers=universityEnrollStudentDao.searchVolunteer(batch,type);
        ArrayList<plan> plans=universityEnrollStudentDao.SearchPlan();

        if(studentAndVolunteers.size()!=0){
            PrintWriter out=response.getWriter();
            for(int j=0;j<plans.size();j++){
                int year=plans.get(j).getYear();
                int count=plans.get(j).getNumber();
                int classid=plans.get(j).getClassid();
                int regionid=plans.get(j).getRegionid();
                int university=plans.get(j).getUniversotyid();
                for(int m=0;m<studentAndVolunteers.size();m++){
                    if(regionid==studentAndVolunteers.get(m).getRegionid()&&university==studentAndVolunteers.get(m).getUniversityid()&&
                            classid==studentAndVolunteers.get(m).getClassid()&&count>0
                            &&universityEnrollStudentDao.SearchSignal(studentAndVolunteers.get(m).getStudentid())){
                        university_enroll_student student=new university_enroll_student();
                        student.setIs_approved(0);
                        student.setYear(year);
                        student.setStudentid(studentAndVolunteers.get(m).getStudentid());
                        student.setClass_id(classid);
                        student.setUniversityid(university);
                        student.setType(studentAndVolunteers.get(m).getType());
                        universityEnrollStudentDao.insertUES(student);
                        count=count-1;
                    }
                }
            }
        }else{
            response.sendError(403, "提交失败，请刷新后再试");
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}

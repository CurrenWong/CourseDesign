package com.courseDesign.servlet;

import com.courseDesign.dao.ManagerDao;
import com.courseDesign.dao.StudentDao;
import com.courseDesign.dao.UniversityDao;
import com.courseDesign.object.education_department;
import com.courseDesign.object.manager;
import com.courseDesign.object.student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "loginservlet",urlPatterns = "/loginservlet.do")
public class loginservlet  extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
}
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type1 = request.getParameter("identity");
        if ("student".equals(type1)) {//学生
            student st=new student();
            String username=request.getParameter("username");
            String password=request.getParameter("password");
            StudentDao dao=new StudentDao();
            student st1 = dao.login(username,password);
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            if(st1!=null){
                System.out.println("学生成功登录！");
            }else{
                System.out.println("您还未注册注册，请您先去注册！");
            }

        } else if ("manager".equals(type1)) {//管理员
            String musername=request.getParameter("username");
            String password=request.getParameter("password");
            HttpSession session = request.getSession();
            //把用户数据保存在session域对象中
            session.setAttribute("tusername", musername);
            session.setAttribute("password", password);
            ManagerDao dao=new ManagerDao();
            manager t=dao.login(musername,password);
            }
        else { //教育部
            String mname=request.getParameter("username");
            String password=request.getParameter("password");
            HttpSession session = request.getSession();
            //把用户数据保存在session域对象中
            session.setAttribute("mname", mname);
            session.setAttribute("password", password);
            UniversityDao dao=new UniversityDao();
            education_department t=dao.login(mname,password);
        }
    }
}

package sources;

import static org.easymock.EasyMock.expect;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.courseDesign.servlet.findEnrollmentResultById;

import org.easymock.EasyMockSupport;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestFindEnrollmentResultById extends EasyMockSupport {
    private findEnrollmentResultById servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    StringWriter out;
    PrintWriter writer;

    // 测试方法执行前
    @Before
    public void setUp() {
        // 创建Servlet
        servlet = new findEnrollmentResultById();
        // 创建字符输出流
        out = new StringWriter();
        writer = new PrintWriter(out);
        // 创建mock对象
        request = createMock(HttpServletRequest.class);
        response = createMock(HttpServletResponse.class);
        // 初始化
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        response.setHeader("content-type", "text/html;charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
    }

    // 测试方法执行后
    @After
    public void tearDown() {
        // 验证所有mock都被使用
        verifyAll();
    }

    @Test
    public void testValidInput() {

        // 设置参数
        expect(request.getParameter("studentid")).andReturn("1");
        // 设置返回参数
        try {
            expect(response.getWriter()).andReturn(writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 切换到replay模式
        replayAll();
        // 发送post请求
        try {
            servlet.doPost(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
        // 输出结果
        System.out.println("TestFindEnrollmentResultById Input: " + "{\"studentid\":\"1\"}");
        System.out.println("TestFindEnrollmentResultById Output: " + out.toString());

        // 确认返回结果
        // 返回对象
        JSONObject js = (JSONObject) JSONObject.parse(out.toString());
        // 确认信息
        assertEquals(1, js.get("id"));
        assertEquals(1, js.get("universityId"));
        assertEquals("上海理工大学", js.get("universityName"));
        assertEquals(1, js.get("studentId"));
        assertEquals("王一", js.get("studentName"));
        assertEquals("2020", js.get("year"));
        assertEquals("统招", js.get("type"));
        assertEquals(5, js.get("classId"));
        assertEquals("经济与贸易类", js.get("className"));
        assertEquals(0, js.get("isApproved"));
        
    }

    @Test
    public void testInvalidInput() {
        // 设置参数
        expect(request.getParameter("studentid")).andReturn("0");
        // 设置返回参数
        try {
            response.sendError(403, "无录取结果");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 切换到replay模式
        replayAll();
        // 发送post请求
        try {
            servlet.doPost(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
        // 输出结果
        System.out.println("TestFindEnrollmentResultById Input: " + "{\"studentid\":\"0\"}");
        System.out.println("TestFindEnrollmentResultById Output: " + "{\"StatusCode\":\"402\"}");
    }
}

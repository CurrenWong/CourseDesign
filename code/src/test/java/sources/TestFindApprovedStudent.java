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

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.courseDesign.servlet.findApprovedStudent;

import org.easymock.EasyMockSupport;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestFindApprovedStudent extends EasyMockSupport {
    private findApprovedStudent servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    StringWriter out;
    PrintWriter writer;

    // private WebApplicationContext context;
    // 测试方法执行前
    @Before
    public void setUp() {
        // 创建Servlet
        servlet = new findApprovedStudent();
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
        expect(request.getParameter("universityId")).andReturn("1");
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
        System.out.println("TestFindEnrolledStudent Input: " + "{\"universityId\":\"1\"}");
        System.out.println("TestFindEnrolledStudent Output: " + out.toString());
        // 返回结果存储在JSONArray中
        JSONArray jsArray = (JSONArray) JSONObject.parse(out.toString());

        // 确认返回结果
        // 返回对象
        JSONObject js = (JSONObject) jsArray.get(0);
        // 确认个人信息
        assertEquals(1, js.get("id"));
        assertEquals(19875426874625L, js.get("testId"));
        assertEquals("王一", js.get("name"));
        assertEquals("男", js.get("gender"));
        assertEquals(5, js.get("regionId"));
        assertEquals(698, js.get("totalScore"));
        assertEquals(200, js.get("rank"));
        // 确认专业
        JSONArray majors = (JSONArray) js.get("majors");
        JSONObject major = (JSONObject) majors.get(0);
        assertEquals("经济与贸易类", major.get("nclass"));
        assertEquals("国际经济与贸易、金融学、税收学、管理科学", major.get("nmajor"));
        assertEquals(5, major.get("classId"));
        assertEquals(5, major.get("regionId"));
        assertEquals(1, major.get("kind"));
        assertEquals(1, major.get("batch"));
        // 确认录取信息
        assertEquals("经济与贸易类", major.get("approvedMajorName"));
        assertEquals("统招", major.get("approvedType"));
    }

    @Test
    public void testInvalidInput() {
        // 设置参数
        expect(request.getParameter("universityId")).andReturn("0");
        // 设置返回参数
        try {
            response.sendError(403, "访问错误，请刷新后重试");
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
        System.out.println("TestFindEnrolledStudent Input: " + "{\"universityId\":\"0\"}");
        System.out.println("TestFindEnrolledStudent Output: " + "{\"StatusCode\":\"403\"}");
    }
}

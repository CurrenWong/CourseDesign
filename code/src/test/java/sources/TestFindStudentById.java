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
import com.courseDesign.servlet.findStudentById;

import org.easymock.EasyMockSupport;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

//这是JUnit的注解，通过这个注解让SpringJUnit4ClassRunner这个类提供Spring测试上下文。
public class TestFindStudentById extends EasyMockSupport {
    private findStudentById servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    StringWriter out;
    PrintWriter writer;

    // private WebApplicationContext context;
    // 测试方法执行前
    @Before
    public void setUp() {
        // 创建Servlet
        servlet = new findStudentById();
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
        expect(request.getParameter("id")).andReturn("1");
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
        System.out.println("TestFindStudentById Input: " + "{\"id\":\"1\"}");
        System.out.println("TestFindStudentById Output: " + out.toString());
        // 返回结果存储在JSON中
        JSONObject js = (JSONObject) JSONObject.parse(out.toString());
        // 确认返回结果
        assertEquals(1, js.get("id"));
        assertEquals(19875426874625L, js.get("testId"));
        assertEquals("王一", js.get("name"));
        assertEquals("男", js.get("gender"));
        assertEquals(5, js.get("regionId"));
        assertEquals(698, js.get("totalScore"));
        assertEquals(200, js.get("rank"));
        assertEquals(120, js.get("chinesrScore"));
        assertEquals(142, js.get("mathScore"));
        assertEquals(138, js.get("englishScore"));
        assertEquals(298, js.get("compScore"));
        assertEquals(2, js.get("kind"));
    }

    @Test
    public void testInvalidInput() {
        // 设置参数
        expect(request.getParameter("id")).andReturn("0");
        // 设置返回参数
        try {
            // expect(response.getWriter()).andReturn(writer);
            response.sendError(402, "id输入错误");
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
        System.out.println("TestFindStudentById Input: " + "{\"id\":\"0\"}");
        System.out.println("TestFindStudentById Output: " + "{\"StatusCode\":\"402\"}");
    }
}

package Servlet.表白墙;

import com.fasterxml.jackson.databind.ObjectMapper;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 当前无法实现数据持久化
 * 什么是持久化？就是服务器重启后就完犊子，信息都在内存，重启就都没了
 * 因此需要，将数据写入硬盘中
 */
//定义一个类 用于解析json内容以及封装消息
class Message_Json{
    public String from;
    public String to;
    public String message;
}
@WebServlet("/message1")
public class MessageWallServer extends HttpServlet {
    ObjectMapper objectMapper = new ObjectMapper();
    List<Message_Json> list = new ArrayList<>();

//    GET 用于处理从服务器拉取保存的消息至浏览器
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=utf-8");
        objectMapper.writeValue(resp.getWriter(),list);

    }
//    POST 用于提交消息
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Message_Json messages = objectMapper.readValue(req.getInputStream(), Message_Json.class);
        list.add(messages);
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write("{\'ok\':1}");
    }
}

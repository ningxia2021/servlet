package Servlet.表白墙;

import com.fasterxml.jackson.databind.ObjectMapper;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 实现数据持久化存储的方式  1.写入硬盘  2.写入数据库
 * 这里实现写入硬盘的操作，让服务器实现持久化存储
 */

//这里的属性名字 一个要和 前端构造的数据名称一致。否则报错 : Unrecognized field xxx , not marked as ignorable 问题
class json {
    public String from;
    public String to;
    public String mes;
}

@WebServlet("/messageFile1")
public class MessageWallServerFile extends HttpServlet {

//    jackson
    ObjectMapper objectMapper = new ObjectMapper();
//    写入地址
    String filePath = "P:/Idea工作目录/java/mes.txt";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        响应格式
        resp.setContentType("application/json;charset=utf-8");
//        加载消息
        List<json> list = load();
//        返回响应
        objectMapper.writeValue(resp.getWriter(),list);
    }

    private List<json> load() {
//        读取文件，获取文件内容，返回结果
        System.out.println("从文件加载数据...");
        List<json> messageList = new ArrayList<>();
//        此处需要按行读取，所以需要 套一层 BufferedReader
//        这里也可以使用Scanner
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))){
            while(true){
//                按行读取
                String s = bufferedReader.readLine();
//                如果没有读到内容 就跳出循环
                if (s == null){
                    break;
                }
//                将读到的内容解析并构造成json对象
                String[] split = s.split("\t");
                json json = new json();
                json.from = split[0];
                json.to = split[1];
                json.mes = split[2];
//                将构造好的json对象添加至messageList
                messageList.add(json);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return messageList;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //        3.定义响应格式
        resp.setContentType("application/json;charset=utf-8");
//        提交消息至文件
//        1.用jackson将json数据转为字符串
        json message = objectMapper.readValue(req.getInputStream(), json.class);
//        2.将消息存入mes.txt中
        save(message);
//        4.返回状态信息
        resp.getWriter().write("{\"ok\":1}");
    }
    //        这里进行写文件操作
    private void save(json message) {
        System.out.println("文件写入！");
//        FileWriter 的使用类似与 PrintWriter
//        new FileWriter(filePath,true)   后面这个true表示每次追加写入；如果没有则每次写入都是清空之前的内容，然后再写入新内容♥
        try (FileWriter fileWriter = new FileWriter(filePath,true)) {
//            写入文件的格式也有很多格式，可以直接写入json 也可以写入行文本
            fileWriter.write(message.from+"\t"+message.to+"\t"+message.mes+"\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

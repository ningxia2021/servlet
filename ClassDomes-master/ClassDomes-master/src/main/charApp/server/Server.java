package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * 服务端核心功能实现
 * @author lllxh
 * @author cy
 */
public class Server {
    /**
     * 定义一个用户集合
     */
    public static ArrayList<User> list1= new ArrayList<>();
    public User uu;
    private static final int PORT=12000;
    private ServerSocket server;
    public ArrayList<PrintWriter> list;
    public static String user;


    public Server(String user) {
        Server.user =user;
    }

    public void getServer() throws Exception{
        list = new ArrayList<>();
//TODO  ServerSocket建立连接
            server=new ServerSocket(PORT);
            System.out.println("服务器启动，正在监听... (ง •̀_•́)ง ");
            while(true) {
                try {
                    //开始监听客户端线程
                    Socket client = server.accept();
                    PrintWriter writer = new PrintWriter(client.getOutputStream());
                    list.add(writer);
//                    TODO 每建立一次连接 就创建一个线程来处理连接
                    Thread t = new ServerThread(client);
                    t.start();
                } catch (Exception e){
                    e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception{
//        TODO new实例+调方法
        new Server(user).getServer();
    }

//TODO 这是多线程的第一种写法 通过继承Thread类来实现 重写run方法来定制运行的程序
    class ServerThread extends Thread {
        Socket socket;
        private BufferedReader bufferedReader;
        private String msg;
        private String msgStr ="";

        public ServerThread(Socket socket) {
            try{
                this.socket=socket;
            }catch(Exception e){
                e.printStackTrace();
            }
        }

//TODO 重写线程run方法  每获取到一个accept 就会新建一个线程来处理连接 具体如何处理就体现在run方法中
        @Override
        public void run() {
            try{
//                TODO 获取流
                bufferedReader =new BufferedReader(new InputStreamReader(socket.getInputStream()));
//                TODO 按行读取流中的内容给msg
                while((msg= bufferedReader.readLine())!=null) {
                    //显示好友列表
//                    TODO 自定义协议 100代表展示好友列表
                    /**
                     * 100
                     * 用户信息
                     */
                    if(msg.equals("100")) {
                        msg= bufferedReader.readLine();
                        //将用户信息添加到User对象中
                        uu=new User(msg,socket);
                        //将对象添加到用户集合
                        list1.add(uu);
                        //遍历用户集合
                        Iterator<User> it= Server.list1.iterator();
                        while(it.hasNext()) {
                            User use=it.next();
                            msg=use.getName()+":";
                            //将所有的用户信息连接成一个字符串
                            msgStr +=msg;
                        }
                        //显示好友列表匹配标识
                        sendMessage("100");
                        //群发消息
                        sendMessage(msgStr);
                    }
                    //匹配群聊信息
                    else if(msg.equals("200")) {
                        msg= bufferedReader.readLine();
                        System.out.println(msg);
                        sendMessage("200");
                        sendMessage(msg);
                    }
                    //显示进入聊天室
                    else if(msg.equals("400")) {
                        msg= bufferedReader.readLine();
                        System.out.println(msg);
                        sendMessage("400");
                        sendMessage(msg);
                    }
                    //私聊
                    else if(msg.equals("300"))
                    {
                        msg= bufferedReader.readLine();
                        //把传进来的用户信息与说话内容分开
                        String[] rt=msg.split("911");
                        //把源目用户信息分隔开
                        String[] tg=rt[0].split(":");
                        //遍历用户集合
                        Iterator<User> iu= Server.list1.iterator();
                        while(iu.hasNext()) {
                            User se=iu.next();
                            //如果传进来的用户信息跟集合中的用户信息吻合
                            if(tg[1].equals(se.getName())){
                                try{
                                    //建立用户自己的流
                                    PrintWriter pwriter=new PrintWriter(se.getSocket().getOutputStream());
                                    pwriter.println("300");
                                    //像单独用户发送消息
                                    pwriter.println(rt[1]);
                                    pwriter.flush();
                                    System.out.println(rt[1]);
                                }catch(Exception e){
                                    e.printStackTrace();
                                }
                            }
                            //如果传进来的用户信息与集合中的用户信息吻合
                            else if(tg[0].equals(se.getName())) {
                                try{
                                    PrintWriter pwr=new PrintWriter(se.getSocket().getOutputStream());
                                    pwr.println("300");
                                    pwr.println(rt[1]);
                                    pwr.flush();
                                    System.out.println(rt[1]);
                                }catch(Exception e){
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                    //下线
                    else if(msg.equals("500")) {
                        msg= bufferedReader.readLine();
                        System.out.println(msg);
                        sendMessage("500");
                        sendMessage(msg);
                        String[] si=msg.split(":");
                        Iterator<User> at= Server.list1.iterator();
                        while(at.hasNext()) {
                            User sr=at.next();
                            if(sr.getName().equals(si[0])) {
                                list1.remove(sr);
                                sr.getSocket().close();
                            }
                        }
                        break;
                    }
                    //刷新
                    else if(msg.equals("600")) {
                        String mssge="";
                        Iterator<User> iter= Server.list1.iterator();
                        while(iter.hasNext()) {
                            User uus=iter.next();
                            msg=uus.getName()+":";
                            mssge+=msg;
                        }
                        sendMessage("600");
                        sendMessage(mssge);
                    }
                }
            }catch(IOException ex){
                ex.printStackTrace();
            }
        }
    }

    /**
     * 广播消息
     * @param message
     */
    public void sendMessage(String message)
    {
        try{
            //输出流集合
            for(PrintWriter pw:list) {
                pw.println(message);
                pw.flush();
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}





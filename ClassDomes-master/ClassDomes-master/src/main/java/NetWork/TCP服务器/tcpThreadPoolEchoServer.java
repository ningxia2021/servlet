package NetWork.TCP服务器;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class tcpThreadPoolEchoServer {
//    1.绑定端口
    ServerSocket serverSocket = null;
    public tcpThreadPoolEchoServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }
//    2.启动服务
    public void start() throws IOException {
        System.out.println("服务器已经启动\n");
        ExecutorService pool = Executors.newCachedThreadPool();
        while (true){
            Socket socket = serverSocket.accept();
            pool.submit(new Runnable() {
                @Override
                public void run() {
                    processConnect(socket);
                }
            });
        }
    }
//  3.处理连接
    private void processConnect(Socket socket) {
        System.out.printf("[%s %d] 客户端已经连接！\n",socket.getInetAddress().toString(),socket.getPort());
        try(InputStream inputStream = socket.getInputStream()){
            try(OutputStream outputStream = socket.getOutputStream()){
//                4.读取请求
                Scanner scanner = new Scanner(inputStream);
                while (true){
                    if (!scanner.hasNext()){
                        System.out.printf("[%s %d] 客户端已断开连接！\n",socket.getInetAddress().toString(),socket.getPort());
                        break;
                    }
                    String request = scanner.next();
//                    5.根据请求处理响应
                    String response = process(request);
//                    6.将响应写回客户端
                    PrintWriter printWriter = new PrintWriter(outputStream);
                    printWriter.println(response);
                    printWriter.flush();
//                    7.打印日志
                    System.out.printf("[%s %d] req : %s , reps : %s \n",socket.getInetAddress().toString(),socket.getPort(),request,response);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
// 所有的tcp服务器 要想实现功能不同  只需要继承 再 重写这一个 “根据请求计算响应” 的方法即可
    private String process(String request) {
        return request;
    }

    public static void main(String[] args) throws IOException {
        tcpThreadPoolEchoServer server = new tcpThreadPoolEchoServer(9090);
        server.start();
    }

}

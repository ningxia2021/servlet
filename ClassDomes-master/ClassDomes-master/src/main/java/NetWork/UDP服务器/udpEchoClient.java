package NetWork.UDP服务器;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

/**
 * 2022.04.09 周六
 * 实现udp客户端 网络套接字编程
 */

public class udpEchoClient {
    //    这里的ip port是服务器的
    private String ServerIP;
    private int ServerPort;
    //    客户端不用手动指定端口，这里的端口是操作系统自动指定的,至于为什么不需要自己指定，那是因为客户端环境的不确定性可能回导致端口的占用
    DatagramSocket socket = null;

    //    初始化
    public udpEchoClient(String ip, int port) throws SocketException {
        socket = new DatagramSocket();
        ServerIP = ip;
        ServerPort = port;
    }

    public void start() throws IOException {
//    用Scanner接收用户输入的信息
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("请输入->");
            String request = scanner.next();
//    构造请求并发送给服务端
//    需要 DatagramPacket 作为载体 并指定服务器IP地址及端口号
            DatagramPacket requestPacket = new DatagramPacket(request.getBytes(), request.getBytes().length, InetAddress.getByName(ServerIP), ServerPort);
            socket.send(requestPacket);
//        接收服务端的响应
//        同样需要载体接收 UDP 是用数据报通信的 所以还是要构造载体
            DatagramPacket responsePacket = new DatagramPacket(new byte[1024], 1024);
            socket.receive(responsePacket);
//        解析一下
            String response = new String(responsePacket.getData(), 0, responsePacket.getData().length);
//        把结果显示在控制台上
            System.out.printf("req : %s ,reps : %s\n", request, response);
        }
    }

    public static void main(String[] args) throws IOException {
        udpEchoClient client = new udpEchoClient("localhost", 9090);
        client.start();
    }
}

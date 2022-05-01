package NetWork.UDP服务器;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * 2022.04.09 周六
 * 实现udp服务端 网络套接字编程
 */

public class udpEchoServer {
    //    定义属性
    private int port;
    private DatagramSocket socket;

    //        绑定端口
    public udpEchoServer(int port) throws SocketException {
//        这一步就是教你如何绑定呢
        socket = new DatagramSocket(port);
    }

    public void start() throws IOException {
        System.out.println("回显服务已经开启");
        while (true) {
//        读取客户端发来的请求 为了接收数据需要先准备一个空的数据报  （因为UDP通信用的是数据报，所以需要用数据报接收请求，此处它相当与一个载体，有它才有数据）
            DatagramPacket requestPacket = new DatagramPacket(new byte[1024], 1024);
//            receive（）方法为UDP接收数据的方法，在没有数据发送过来时，它会阻塞！
            socket.receive(requestPacket);
//        把DatagramPacket解析成一个String,便于观察
            String req = new String(requestPacket.getData(), 0, requestPacket.getLength());
//        根据请求计算响应
            String process = process(req);
//        把响应写回到客户端，还是需要DatagramPacket为载体，所以这里要构造DatagramPacket变量
            DatagramPacket responsePacket = new DatagramPacket(process.getBytes(), process.getBytes().length, requestPacket.getSocketAddress());
            String resp = new String(requestPacket.getData(),0,responsePacket.getData().length);
            System.out.printf("[%s,%s] req : %s  resp : %s\n", requestPacket.getAddress().toString(), requestPacket.getPort(), req, resp);
            /**
             * 发送数据时需要显示指定 地址+端口
             * 在当前的场景中，哪个客户端发的请求，就返回给谁.
             * 实现方法就是在构造response的时候就指定ip和port  这个方法[getSocketAddress()]就实现了对ip和端口的绑定
             */
            socket.send(responsePacket);
        }
    }

    public String process(String req) {
//        由于这里是一个回显服务器，所以就直接返回接收到的请求信息即可
        return req;
    }

    public static void main(String[] args) throws IOException {
        udpEchoServer udp = new udpEchoServer(9090);
        udp.start();
    }
}

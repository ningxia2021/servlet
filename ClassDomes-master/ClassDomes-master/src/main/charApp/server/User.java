package server;

import java.net.Socket;

/**
 * @author 15068
 * @author cy
 */
public class User {
    private String name;
    private Socket socket;

    public String getName() {
        return name;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User(String name, Socket socket){
        setName(name);
        setSocket(socket);
    }
}

package net.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by pc9507 on 2015/12/6.
 *
 * 运行后开启一个本地服务。
 * 用户可以使用telnet链接到服务器localhost 端口 8189
 * 连接成功以后会看到 "Hello!  Enter quit to exit"
 * 可以随意键入信息，然后观察屏幕上的回送信息
 * 输入quit断开连接同时服务也会关闭
 *
 * 但是这个服务存在一个问题。假设我们希望有多个客户端同时链接到我们的服务器上
 * 但是我们现有的服务器是拒绝多客户端的链接，所以我们要运用多线程来解决这个问题
 *
 */
public class EchoServer {
    public static void main(String[] args) {
        try(ServerSocket ss = new ServerSocket(8189)){
            try(Socket incoming = ss.accept()){
                InputStream inStream = incoming.getInputStream();
                OutputStream outStream = incoming.getOutputStream();
                try(Scanner in =new Scanner(inStream)){
                    PrintWriter pw = new PrintWriter(outStream,true);  //AutoFlush
                    pw.write("Hello!  Enter quit to exit");

                    //客户端输入
                    boolean done = false;
                    while(!done && in.hasNextLine()){
                        String line = in.nextLine();
                        System.out.println(line);
                        if (line.trim().equals("quit")){
                            done = true;
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

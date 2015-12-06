package net.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by pc9507 on 2015/12/6.
 *
 * 每当程序建立一个新的Socket链接，也就是说调用accept()方法时
 * 将启动一个新的线程来处理服务器和该客户端之间的连接
 * 而主程序立刻返回并等待下一个连接
 * 因此多个客户端就可以同时连接到服务器了
 */
public class ThreadedEchoServer {
    public static void main(String[] args) {
        try {
            int i = 1;
            ServerSocket ss = new ServerSocket(8189);
            while(true){
                Socket s = ss.accept();
                System.out.println("Spawning " + i);
                Runnable r = new ThreadedEchoHandler(s);
                Thread t = new Thread(r);
                t.start();
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

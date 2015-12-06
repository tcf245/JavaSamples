package net.socket;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * Created by pc9507 on 2015/12/6.
 * Socket是网络软件中的一个抽象概念，负责启动程序内部和外部之间的通信
 * 我们将远程地址和端口号传递给Socket构造器
 * s.setSoTimeout(3000)方法可以设置超时值
 * 但是Socket(String host,int port)构造器是阻塞的 如果连接不到会一直阻塞下去
 * 所以应使用如下构造方法
 * Socket s = new Socket();
 * s.connect(new InetSocketAddress(host,port),timout);
 *
 */
public class SoketTest {
    public static void main(String[] args) {
        try(
                Socket s = new Socket("time-A.timefreq.bldrdoc.gov",13)
        ){
//            Socket socket = new Socket();
//            socket.connect(new InetSocketAddress("time-A.timefreq.bldrdoc.gov",13),5000);

            s.setSoTimeout(5000);   // 设置连接超时值
            InputStream input = s.getInputStream();
            Scanner in = new Scanner(input);
            while(in.hasNextLine()){
                String line = in.nextLine();
                System.out.println(line);
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

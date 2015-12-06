package net.interruptible;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
 * Created by pc9507 on 2015/12/6.
 *
 * 当连接到一个Socket时，当前线程将会被阻塞直到建立连接或产生超时为止。同样的
 * 当通过Socket读写数据时，当前线程也会被阻塞知道操作成功或产生超时为止
 *
 * 在交互式应用中，也许会考虑为用户提供一个选项，用以取消那些看似不会产生结果的连接
 * 但是，当Socket无法响应而产生阻塞时，则无法通过interrupt来解除阻塞
 * 为了中断Socket操作，可以使用java.nio包提供的一个特性--SocketChannel
 *
 * channel并没有与之相关联的流，实际上，他所拥有的read和write方法都是通过使用buffer来实现的
 * 如果不想用缓冲区，可以使用Sacnner类从SocketChannel中读取信息
 */
public class InterruptibleSocketFrame extends JFrame {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new InterruptibleSocketFrame();
                frame.setTitle("InterruptibleSocketTest");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }

    public static final int TEXT_ROWS = 20;
    public static final int TEXT_COLUMNS = 60;

    private Scanner in;
    private JButton interruptibleButton;
    private JButton blockingButton;
    private JButton cancelButton;
    private JTextArea messages;
    private TestServer server;
    private Thread connectThread;

    public InterruptibleSocketFrame(){
        JPanel northPanel = new JPanel();
        add(northPanel, BorderLayout.NORTH);
        messages = new JTextArea(TEXT_ROWS,TEXT_COLUMNS);
        add(new JScrollPane(messages));

        interruptibleButton = new JButton("Interruptible");
        blockingButton = new JButton("Blocking");
        northPanel.add(interruptibleButton);
        northPanel.add(blockingButton);

        interruptibleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                interruptibleButton.setEnabled(false);
                blockingButton.setEnabled(false);
                cancelButton.setEnabled(true);
                connectThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            connectInterruptibly();
                        } catch (IOException e1) {
                            messages.append("\nInterruptibleSocketTest.connectinterruptibly:" + e);
                        }
                    }
                });
                connectThread.start();
            }
        });


        blockingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                interruptibleButton.setEnabled(false);
                blockingButton.setEnabled(false);
                cancelButton.setEnabled(true);

                connectThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            connectBlocking();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                });
                connectThread.start();
            }
        });

        cancelButton = new JButton("Cancel");
        cancelButton.setEnabled(false);
        northPanel.add(cancelButton);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connectThread.interrupt();
                cancelButton.setEnabled(false);
            }
        });
        server = new TestServer();
        new Thread(server).start();
        pack();
    }

    /**
     * Connects to the test server,using interruptible I/O
     * @throws IOException
     */
    public void connectInterruptibly() throws IOException {
        messages.append("Interrptible:\n");
        try(SocketChannel channel = SocketChannel.open(new InetSocketAddress("localhost",8189))){
            in = new Scanner(channel);
            while(!Thread.currentThread().isInterrupted()){
                messages.append("Reading..");
                if (in.hasNextLine()){
                    String line = in.nextLine();
                    messages.append(line);
                    messages.append("\n");
                }
            }
        } finally{
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    messages.append("Channel closed \n");
                    interruptibleButton.setEnabled(true);
                    blockingButton.setEnabled(true);
                }
            });
        }
    }

    /**
     * Connects ti the test server,using blocking I/O
     * @throws IOException
     */
    public void connectBlocking() throws IOException {
        messages.append("Blocking:\n");
        try(Socket sock = new Socket("localhost",8189)){
            in = new Scanner(sock.getInputStream());
            while(!Thread.currentThread().isInterrupted()){
                messages.append("Reading ");
                if (in.hasNextLine()){
                    String line = in.nextLine();
                    messages.append(line);
                    messages.append("\n");
                }
            }
        }finally{
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    messages.append("Socket closed\n");
                    interruptibleButton.setEnabled(true);
                    blockingButton.setEnabled(true);
                }
            });
        }
    }


    /**
     *
     */
    class TestServer implements Runnable {

        @Override
        public void run() {
            try{
                ServerSocket ss = new ServerSocket(8189);
                while(true){
                    Socket incoming = ss.accept();
                    Runnable r = new TestServerHandler(incoming);
                    Thread t = new Thread(r);
                    t.start();
                }
            } catch (IOException e) {
                messages.append("\nTestServer.run: " + e);
            }
        }
    }


    /**
     *
     */
    class TestServerHandler implements Runnable {
        private Socket incoming;
        private int counter;

        public TestServerHandler(Socket i){
            this.incoming = i;
        }
        @Override
        public void run() {
            try{
                try{
                    OutputStream outStream = incoming.getOutputStream();
                    PrintWriter pw = new PrintWriter(outStream,true);
                    while(counter < 100){
                        counter++ ;
                        if (counter <= 10){
                            pw.print(counter);
                        }
                        Thread.sleep(100);
                    }
                } finally{
                    incoming.close();
                    messages.append("Closing server \n");
                }}
            catch (Exception e) {
                messages.append("\nTestServerHandler.run: " + e);
            }

        }
    }
}

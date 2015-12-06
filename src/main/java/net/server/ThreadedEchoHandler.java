package net.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by pc9507 on 2015/12/6.
 */
public class ThreadedEchoHandler implements Runnable {
    private Socket incoming;

    public ThreadedEchoHandler(Socket i) {
        this.incoming = i;
    }

    @Override
    public void run() {
        try {
            try {
                InputStream inStream = incoming.getInputStream();
                OutputStream outStream = incoming.getOutputStream();

                Scanner in = new Scanner(inStream);
                PrintWriter pw = new PrintWriter(outStream, true); //AutoFlush
                pw.write("Hello! Enter quit to exit.");

                boolean done = false;
                while (!done && in.hasNextLine()) {
                    String line = in.nextLine();
                    pw.print(line);
                    if (line.trim().equals("quit")) {
                        done = true;
                    }
                }
            } finally {
                incoming.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
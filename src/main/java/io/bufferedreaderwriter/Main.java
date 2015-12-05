package io.bufferedreaderwriter;

import java.io.*;

/**
 * Created by pc9507 on 2015/12/5.
 *
 * 字符缓冲读写流的默认缓存区为8192字符。
 * BufferedReader读取文本文件时，会先从文件中读取到缓存区
 * 然后使用read方法从缓存区读取，如果缓存区数据不足，会在从文本文件中读取字符到缓存区
 * 同样，使用BufferedWriter写入数据时不直接写入目的地，而是先写入缓存区，如果缓存区已满，则执行一次对目的地的写操作
 *
 * 通过缓存区可以减少对磁盘的输入输出操作，提高文件存取的效率
 */
public class Main {
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new FileWriter("lib/bufferedWriter.txt"));

            String input = null;
            while(!(input  = br.readLine()).equals("quit")){
                bw.write(input);
                //写入与操作系统相应的换行符
                bw.newLine();
            }
            br.close();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package io.file;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by pc9507 on 2015/12/4.
 * 该示例程序用于遍历文件夹下面的文件，并且输出文件夹的信息。
 * 由于使用了Filter过滤器，所以只读取java文件
 *
 * main方法中讲参数设置为 src  即当前工程src目录
 * 程序运行后会打印出该工程src目录下所有 .java 文件信息
 *
 * 主要通过递归方法readFileList完成文件的信息输出
 * 该方法参数若为文件，则调用fileDesc方法输出信息
 * 若该参数为目录，则递归调用readFileList方法
 */
public class FileMain {
    public static void main(String[] args) {
        String fileName = "src";  //将参数设置为当前工程src目录
        File file = new File(fileName);
        Filter filter = new Filter("java");
        //创建FileMain实例并且调用readFileList方法
        new FileMain().readFileList(file,filter);
    }

    public void readFileList(File file, Filter filter){
        if (file.isDirectory()){ //当参数为目录时
            try {
                File[] files = file.listFiles();
                ArrayList<File> fileList = new ArrayList<>();
                for (int i = 0; i < files.length; i++) {
                    if (files[i].isDirectory()) {
                        System.out.println("[" + files[i].getPath() + "]");
                        readFileList(files[i], filter);
                    } else {
                        fileList.add(files[i]);
                    }
                }
                for (File f : fileList) {
                    readFileList(f, filter);
                }
                System.out.println();
            }catch(ArrayIndexOutOfBoundsException e){
                e.printStackTrace();
            }
        }else if (file.isFile()){  //当参数是文件时
            fileDesc(file);
        }
    }
    public void fileDesc(File file){
        if (file.isFile()){
            System.out.print(file.toString() + "\n该文件 ");
            System.out.print(file.canRead() ? "  可读  ":"  不可读  ");
            System.out.print(file.canWrite() ?"  可写  ":"  不可写  ");
            System.out.println(file.length() + "  字节 ");
        }
    }
}

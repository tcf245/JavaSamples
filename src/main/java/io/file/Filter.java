package io.file;

import java.io.File;
import java.io.FilenameFilter;

/**
 * Created by pc9507 on 2015/12/4.
 * 设置过滤器，使得 操作只对.java文件生效
 */
public class Filter implements FilenameFilter{
    String extent;
    public Filter(String exetend) {
        this.extent = exetend;
    }
    @Override
    public boolean accept(File dir, String name) {
        return name.endsWith("." + extent);
    }
}

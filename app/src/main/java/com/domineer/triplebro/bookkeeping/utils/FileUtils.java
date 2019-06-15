package com.domineer.triplebro.bookkeeping.utils;

import java.io.File;

/**
 * @author Domineer
 * @data 2019/6/16,5:58
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class FileUtils {

    public static void deleteFile(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                File f = files[i];
                deleteFile(f);
            }
            file.delete();//如要保留文件夹，只删除文件，请注释这行
        } else if (file.exists()) {
            file.delete();
        }
    }
}

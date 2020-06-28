package com.soft1851.smart.campus.utils;

import org.springframework.util.ResourceUtils;

import java.io.*;

/**
 * @Description TODO
 * @Author wf
 * @Date 2020/5/26
 * @Version 1.0
 */
public class FileUtil {

    public static void saveLogInfo(String logInfo) {
        try {
            String path = ResourceUtils.getURL("classpath:").getPath() + "/log.txt";
            System.out.println(path);
            byte[] sourceByte = logInfo.getBytes();
            if (sourceByte != null) {
                    try {
                        File file = new File(path);
                        if (!file.exists()) {
                            File dir = new File(file.getParent());
                            dir.mkdirs();
                            file.createNewFile();
                            FileOutputStream outputStream = new FileOutputStream(file);
                            outputStream.write(sourceByte);
                            outputStream.close();
                        } else {
                            File f = new File(path);
                            FileWriter fw = new FileWriter(f, true);
                            PrintWriter pw = new PrintWriter(fw);
                            pw.println(logInfo);
                            pw.flush();
                            fw.flush();
                            pw.close();
                            fw.close();
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        String hello = "hello,jsdjflasjdfas.fjsdafjlaksasdfsaf";
        saveLogInfo(hello);
    }
}

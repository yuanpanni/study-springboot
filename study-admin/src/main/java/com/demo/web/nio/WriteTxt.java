package com.demo.web.nio;

import org.thymeleaf.util.DateUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WriteTxt {

    public static void main(String[] args) {
        String[] arry={"liuyang","wangmin","dabai","xiaoyou","ailisi","chenxian","xiechen","jiaojiao","panni","wenjing","qianxun"};
        File file = null;
        FileWriter fw = null;
        String fileName = "login_log.txt";
        file = new File("G:\\"  + fileName);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            fw = new FileWriter(file);
            SimpleDateFormat sdf=new SimpleDateFormat("YYYY-mm-DD-HH-MM-ss");
            for(int i = 1;i <=300000000;i++){
                fw.write("用户名"+arry[i%11]+"："+ sdf.format(new Date(i*1000)) +":127.0.0.1"+"\r\n");//向文件中写内容
                fw.flush();
            }
            System.out.println("写数据成功！");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally{
            if(fw != null){
                try {
                    fw.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

    }
}

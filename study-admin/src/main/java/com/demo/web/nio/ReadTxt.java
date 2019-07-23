package com.demo.web.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.Arrays;


public class ReadTxt {
    public static void main(String[] args) {
        readFile();
    }

    private static void readFile(){
        String fileName = "login_log.txt";
       // String infile = "G:\\"  + fileName;
        String infile="G:\\temp\\log_0.0665719361244369.txt";
        try {
            FileInputStream fin = new FileInputStream(infile);
            FileChannel channel = fin.getChannel();
            int filesize = 500*1024*1024;//一次读文件的大小
            ByteBuffer buffer = ByteBuffer.allocate(filesize);

            while(true){
                buffer.clear();
                try {
                    int flag = channel.read(buffer); //读取数据的标志

                    if(flag == -1){
                        break ; //没有数据，退出
                    }

                    buffer.flip();

                    //解析数据
                    readDateByLine(buffer);

                    //读到数据，写入到新文件中
                    /*File file = new File("G:\\temp\\"+"log_"+Math.random()+".txt");
                    if(!file.exists()){
                        file.createNewFile();
                    }
                    FileOutputStream fout = new FileOutputStream(file);
                    FileChannel fc = fout.getChannel();

                    fc.write(buffer);*/

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void readDateByLine(ByteBuffer byteBuffer) {
        byte[] bs = new byte[byteBuffer.position()];
        byte[] temp = null;
        byteBuffer.get(bs);
        int startNum=0;
        //判断是否出现了换行符，注意这要区分LF-\n,CR-\r,CRLF-\r\n,这里判断\n
        for(int i=0;i < bs.length;i++) {
            if(bs[i] == 10) {
                //如果换行直接打印数据：
                temp = new byte[i-startNum];
                byteBuffer.get(temp,0,temp.length);
                System.out.println(Arrays.toString(temp));

                startNum = i;
            }
        }
    }



}

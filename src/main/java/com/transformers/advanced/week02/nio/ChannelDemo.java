package com.transformers.advanced.week02.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ChannelDemo {
    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("D:/00_Java_advanced/sandbox/TestDemo.txt");
        FileChannel fisChannel = fis.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(2048);
        int len = -1;
        byte[] bytes;
        while ((len = fisChannel.read(byteBuffer)) != -1) {
            byteBuffer.flip();
            bytes = new byte[byteBuffer.limit()];
            byteBuffer.get(bytes, 0, byteBuffer.limit());
            System.out.println(new String(bytes));
            byteBuffer.clear();
        }

        FileOutputStream fos = new FileOutputStream("D:/00_Java_advanced/sandbox/channel.txt");
        FileChannel fosChannel = fos.getChannel();

    }
}

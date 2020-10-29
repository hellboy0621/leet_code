package com.transformers.advanced.week02.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileNioCopyDemo {
    public static void main(String[] args) {
        nioCopyResourceFile();
    }

    private static void nioCopyResourceFile() {
        String srcPath = "D:/00_Java_advanced/sandbox/TestDemo.txt";
        String destPath = "D:/00_Java_advanced/sandbox/channel.txt";
        nioCopyFile(srcPath, destPath);
    }

    private static void nioCopyFile(String srcPath, String destPath) {
        File srcFile = new File(srcPath);
        File destFile = new File(destPath);
        try {
            if (!destFile.exists()) {
                destFile.createNewFile();
            }
            long start = System.currentTimeMillis();
            FileInputStream fis = null;
            FileOutputStream fos = null;
            FileChannel inChannel = null;
            FileChannel outChannel = null;

            try {
                fis = new FileInputStream(srcFile);
                fos = new FileOutputStream(destFile);
                inChannel = fis.getChannel();
                outChannel = fos.getChannel();
                int len = -1;
                ByteBuffer buf = ByteBuffer.allocate(1024);
                while ((len = inChannel.read(buf)) != -1) {
                    buf.flip();
                    int outLen = 0;
                    while ((outLen = outChannel.write(buf)) != 0) {
                        System.out.println("write bytes " + outLen);
                    }
                    buf.clear();
                }
                outChannel.force(true);
            } finally {
                outChannel.close();
                inChannel.close();
                fos.close();
                fis.close();
            }
            long end = System.currentTimeMillis();
            System.out.println("cost " + (end - start) + "ms");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

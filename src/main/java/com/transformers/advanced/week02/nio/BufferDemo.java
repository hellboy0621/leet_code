package com.transformers.advanced.week02.nio;

import java.nio.ByteBuffer;

public class BufferDemo {
    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(20);
        print(byteBuffer);
        for (int i = 0; i < 5; i++) {
            byteBuffer.put((byte) ('a' + i));
        }
        print(byteBuffer);
        byteBuffer.flip();
        for (int i = 0; i < 2; i++) {
            System.out.println("byteBuffer.get() = " + byteBuffer.get());
        }
        print(byteBuffer);
        for (int i = 0; i < 3; i++) {
            System.out.println("byteBuffer.get() = " + byteBuffer.get());
        }
        print(byteBuffer);
        byteBuffer.rewind();
        print(byteBuffer);
    }

    private static void print(ByteBuffer byteBuffer) {
        System.out.println("****************************************************");
        System.out.println("byteBuffer.position() = " + byteBuffer.position());
        System.out.println("byteBuffer.limit() = " + byteBuffer.limit());
        System.out.println("byteBuffer.capacity() = " + byteBuffer.capacity());
    }
}

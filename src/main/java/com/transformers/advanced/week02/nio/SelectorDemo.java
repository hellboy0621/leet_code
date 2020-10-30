package com.transformers.advanced.week02.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

public class SelectorDemo {
    public static void main(String[] args) throws IOException {
        // 监控通道的多种事件，⽤“按位或”运算符来实现
        // int key = SelectionKey.OP_READ | SelectionKey.OP_WRITE;

        // 1.调⽤静态⼯⼚⽅法open()来获取Selector实例
        Selector selector = Selector.open();

        // 2.获取通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 3.设置为⾮阻塞
        serverSocketChannel.configureBlocking(false);
        // 4.绑定连接
        serverSocketChannel.bind(new InetSocketAddress(9999));
        // 5.将通道注册到选择器上,并制定监听事件为：“接收连接”事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        //轮询，选择感兴趣的IO就绪事件（选择键集合）
        while (selector.select() > 0) {
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = selectedKeys.iterator();
            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();
                //根据具体的IO事件类型，执⾏对应的业务操作
                if (key.isAcceptable()) {
                    // IO事件：ServerSocketChannel服务器监听通道有新连接

                } else if (key.isConnectable()) {
                    // IO事件：传输通道连接成功

                } else if (key.isReadable()) {
                    // IO事件：传输通道可读

                } else if (key.isWritable()) {
                    // IO事件：传输通道可写

                }
                //处理完成后，移除选择键
                keyIterator.remove();
            }
        }
    }
}

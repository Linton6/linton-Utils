package utils.nio;

/**
 * @Date 2019/12/18 14:40
 * @ NIO方式  接受客户端向服务端发送的消息
 */

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class ServerTemplate {

    public static void main(String[] args) throws IOException {

        ServerSocketChannel ssChannel = ServerSocketChannel.open();
        ssChannel.configureBlocking(false);
        ssChannel.bind(new InetSocketAddress(8889));

        Selector selector = Selector.open();

        // 用所给的选择器来注册这个通道，返回一个选择键。
        ssChannel.register(selector, SelectionKey.OP_ACCEPT); //每次在一个通道注册一个选择器时，创建一个选择键。??
        // OP_ACCEPT 套接字接受操作的操作设置位。

        // 轮询式的获取选择器上已经准备就绪的事件
        while (selector.select() > 0) { // select() 返回的值表示有多少通道已经就绪
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator(); // 通过Selector的selectedKeys()方法来访问已经选择键集合
            while (iterator.hasNext()) { //
                SelectionKey key = iterator.next();
                // 很重要！！！删除已选择的 key，以防重复处理
                iterator.remove();

                // 判断具体是什么事件准备就绪
                if (key.isAcceptable()) {

                    // 接收新客户端，获取客户端连接
                    SocketChannel socketChannel = ssChannel.accept();
                    // 设置为非阻塞模式
                    socketChannel.configureBlocking(false);

                    // 将该通道注册到选择器上
                    int interestSet = SelectionKey.OP_READ | SelectionKey.OP_WRITE;
                    socketChannel.register(selector, interestSet);

                } else if (key.isReadable()) { //测试此键的通道是否已准备好阅读。
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    // Received the information from client
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    int len = 0;
                    while ((len = socketChannel.read(buffer)) > 0) {
                        buffer.flip();
                        // received the data from client
                        System.out.println(new String(buffer.array(), 0, len));
                        buffer.clear();
                    }
                } else if (key.isWritable()) { //测试此键的通道是否已准备好写入。
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    ByteBuffer buffer = null;
                    byte[] bytes = "dasd".getBytes();
                    buffer = ByteBuffer.wrap(bytes);
                    socketChannel.write(buffer);
                }
            }
        }
    }
}

/** 服务端的模板代码，有了模板代码，我们在编写程序时，大多时间都在模板代码里添加相应的业务代码
 *
 * ServerSocketChannel ssc = ServerSocketChannel.open();
 * ssc.socket().bind(new InetSocketAddress("localhost", 8080));
 * ssc.configureBlocking(false);
 *
 * Selector selector = Selector.open();
 * ssc.register(selector, SelectionKey.OP_ACCEPT);
 *
 * while(true) {
 *     int readyNum = selector.select();
 *     if (readyNum == 0) {
 *         continue;
 *     }
 *
 *     Set<SelectionKey> selectedKeys = selector.selectedKeys();
 *     Iterator<SelectionKey> it = selectedKeys.iterator();
 *
 *     while(it.hasNext()) {
 *         SelectionKey key = it.next();
 *
 *         if(key.isAcceptable()) {
 *             // 接受连接
 *         } else if (key.isReadable()) {
 *             // 通道可读
 *         } else if (key.isWritable()) {
 *             // 通道可写
 *         }
 *
 *         it.remove();
 *     }
 * }
 *
 */

package utils.nio;

/**
 * @Date 2019/12/18 14:40
 * @ NIO��ʽ  ���ܿͻ��������˷��͵���Ϣ
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

        // ��������ѡ������ע�����ͨ��������һ��ѡ�����
        ssChannel.register(selector, SelectionKey.OP_ACCEPT); //ÿ����һ��ͨ��ע��һ��ѡ����ʱ������һ��ѡ�����??
        // OP_ACCEPT �׽��ֽ��ܲ����Ĳ�������λ��

        // ��ѯʽ�Ļ�ȡѡ�������Ѿ�׼���������¼�
        while (selector.select() > 0) { // select() ���ص�ֵ��ʾ�ж���ͨ���Ѿ�����
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator(); // ͨ��Selector��selectedKeys()�����������Ѿ�ѡ�������
            while (iterator.hasNext()) { //
                SelectionKey key = iterator.next();
                // ����Ҫ������ɾ����ѡ��� key���Է��ظ�����
                iterator.remove();

                // �жϾ�����ʲô�¼�׼������
                if (key.isAcceptable()) {

                    // �����¿ͻ��ˣ���ȡ�ͻ�������
                    SocketChannel socketChannel = ssChannel.accept();
                    // ����Ϊ������ģʽ
                    socketChannel.configureBlocking(false);

                    // ����ͨ��ע�ᵽѡ������
                    int interestSet = SelectionKey.OP_READ | SelectionKey.OP_WRITE;
                    socketChannel.register(selector, interestSet);

                } else if (key.isReadable()) { //���Դ˼���ͨ���Ƿ���׼�����Ķ���
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
                } else if (key.isWritable()) { //���Դ˼���ͨ���Ƿ���׼����д�롣
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

/** ����˵�ģ����룬����ģ����룬�����ڱ�д����ʱ�����ʱ�䶼��ģ������������Ӧ��ҵ�����
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
 *             // ��������
 *         } else if (key.isReadable()) {
 *             // ͨ���ɶ�
 *         } else if (key.isWritable()) {
 *             // ͨ����д
 *         }
 *
 *         it.remove();
 *     }
 * }
 *
 */

package utils.nio;

/**
 * @Date 2019/12/19 13:17
 * @
 */
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class ClientTemplate {
    public static void main(String[] args) throws IOException {
        try {
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.connect(new InetSocketAddress("127.0.0.1", 8000));

            ByteBuffer writeBuffer = ByteBuffer.allocate(32);
            ByteBuffer readBuffer = ByteBuffer.allocate(32);

            writeBuffer.put("hello".getBytes());
            writeBuffer.flip(); // ��buffer��дģʽ����ģʽ�����ǽ�position��0��limit����Ϊposition֮ǰ��λ��

            while (true) {
                writeBuffer.rewind(); // ��position��0��limit���ֲ��䣬�����ظ���ȡbuffer�е�����
                socketChannel.write(writeBuffer);
                readBuffer.clear();
                socketChannel.read(readBuffer);
            }
        } catch (IOException e) {
        }
    }
}
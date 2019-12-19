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
            writeBuffer.flip(); // 把buffer从写模式到读模式，就是将position归0，limit设置为position之前的位置

            while (true) {
                writeBuffer.rewind(); // 将position归0，limit保持不变，可以重复读取buffer中的数据
                socketChannel.write(writeBuffer);
                readBuffer.clear();
                socketChannel.read(readBuffer);
            }
        } catch (IOException e) {
        }
    }
}
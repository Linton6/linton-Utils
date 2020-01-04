package test.TCPcomunication;

/**
 * @Date 2019/12/28 17:17
 * @
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * ����Ϊ���߳��࣬���ڷ����
 */
public class ServerThread implements Runnable {

    private Socket client = null;
    public ServerThread(Socket client){
        this.client = client;
    }

    @Override
    public void run() {
        try{
            //��ȡSocket���������������ͻ��˷�������
            PrintStream out = new PrintStream(client.getOutputStream());
            //��ȡSocket�����������������մӿͻ��˷��͹���������
            BufferedReader buf = new BufferedReader(new InputStreamReader(client.getInputStream()));
            boolean flag =true;
            while(flag){
                //���մӿͻ��˷��͹���������
                String str =  buf.readLine();
                if(str == null || "".equals(str)){
                    flag = false;
                }else{
                    if("bye".equals(str)){
                        flag = false;
                    }else{
                        //�����յ����ַ���ǰ�����echo�����͵���Ӧ�Ŀͻ���
                        out.println("echo:" + str);
                    }
                }
            }
            out.close();
            client.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(8888);
        Socket client = null;
        boolean f = true;
        System.out.println("Server starting...");
        while(f){
            //�ȴ��ͻ��˵����ӣ����û�л�ȡ����
            client = server.accept();
            System.out.println("connected with client?");
            //Ϊÿ���ͻ������ӿ���һ���߳�
            new Thread(new ServerThread(client)).start();
        }
        server.close();
    }
}



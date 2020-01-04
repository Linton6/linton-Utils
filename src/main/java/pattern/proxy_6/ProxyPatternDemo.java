package pattern.proxy_6;

/**
 * @Date 2019/11/30 14:55
 * @ ����ģ��-�ṹ��ģʽ
 * Ŀ�꣺Ϊ���������ṩһ�ִ����Կ��ƶ��������ķ��ʡ�˵���˾���һ���������һ����Ĺ��ܡ�
 * �ؼ����룺�����м�㣬ʵ�ִ������ί������ϣ���Ҫ���߼̳�ͬһ���ӿڣ�
 * ע�����1����������ģʽ������������ģʽ��Ҫ�ı������Ƕ���Ľӿڣ�������ģʽ���ܸı���������Ľӿڡ� 2����װ����ģʽ������װ����ģʽΪ����ǿ���ܣ�������ģʽ��Ϊ�˼��Կ��ơ�
 * �ο���ַ��[����̳�](!https://www.runoob.com/design-pattern/proxy-pattern.html)
 *
 *
 * ���������
 * 1. �������ģʽ������Ⱦ�̬������Ҫ��������Ա������ί�нӿڣ��������ί���࣬�������ί����ʵ��ͬһ���ӿڣ�������ί�нӿڣ�����Ȼ�Ļ������ⲿ����ʱ�������ó���ί�нӿ�(Image)���ý��մ��������
 * 2. ������ֻ��Ҫ��ί������ķ������Լ��ķ������װһ�£�����һ�£��������Ա��ⲿ���á�����и��㣬��Ҫע�⣬������ͨ���ۺϣ�������һ��ί��������ã�����Ϊ������õĴ��ڲſ����ڴ������е���ί����ķ���
 * 3. UML��ͼ����
 *
 * ���䣺
 * 1. ��������ֽ���Ĵ���ʱ�������࣬���Է�Ϊ��̬����Ͷ�̬����
 * - ��ν`��̬����`Ҳ�����ڳ�������ǰ���Ѿ����ڴ�������ֽ����ļ������������ʵ�����ɫ�Ĺ�ϵ������ǰ��ȷ���ˡ�
 * - ��`��̬����`��Դ�����ڳ��������ڼ���JVM���ݷ���Ȼ��ƶ�̬�����ɣ�����������ǰ�������ڴ�������ֽ����ļ���
 *
 * 2. java�Ķ�̬����������ʵ�ַ�ʽ��`JDK ��̬�����Cglib ��̬����`
 *  > JDK��̬����
 *  ԭ������������������������ʵ��InvocationHandler�����Ϸ����������һ��ʵ�ִ���ӿڵ������࣬�ڵ��þ��巽��ǰ����InvokeHandler������
 *  Ҫ��ί���ࣨĿ�������Ҫʵ�ֽӿ�
 *
 *  > CGLIB ��̬����
 *  ԭ������ASM��Դ�����Դ���������class�ļ����ؽ�����ͨ���޸����ֽ�����������������
 *  Ҫ��ί���ࣨĿ�����û��ʵ�ֽӿڣ�����ʹ��CGLIB�����ʵ���˽ӿڿ���ǿ��ʹ��CGLIB��
 *
 *  �ο����ӣ�
 *  [Java��̬����](https://juejin.im/post/5ad3e6b36fb9a028ba1fee6a)
 * [Java ��̬�������](https://juejin.im/post/5c1ca8df6fb9a049b347f55c) �Ƽ�
 */

//����1������һ���������ί���඼��Ҫʵ�ֵĽӿ�
interface Image {
    void display();
}

// ����2������ʵ�ֽӿڵ�ί����RealImage��������ProxyImage
class RealImage implements Image {

    private String fileName;

    public RealImage(String fileName){
        this.fileName = fileName;
        loadFromDisk(fileName);
    }

    @Override
    public void display() {
        System.out.println("Displaying " + fileName);
    }

    private void loadFromDisk(String fileName){
        System.out.println("Loading " + fileName);
    }
}

class ProxyImage implements Image {

    private RealImage realImage;  // ͨ���ۺ���ʵ�֣��ô��������һ��ί��������ü���
    private String fileName;

    public ProxyImage(String fileName){
        this.fileName = fileName;
    }

    @Override
    public void display() {
        if(realImage == null){ // ��һ�ε��ã�realImageû�г�ʼ�����ᴥ��RealImage�Ĺ��������ڶ��ε���ʱ��realImage��Ϊnull�����������ᴥ��
            realImage = new RealImage(fileName);
        }
        realImage.display();
    }
}

// ����3����������ʱ��ʹ��ProxyImage����ȡRealImage��Ķ���
public class ProxyPatternDemo {

    public static void main(String[] args) {
        Image image = new ProxyImage("test_10mb.jpg");

        // ͼ�񽫴Ӵ��̼���
        image.display();
        System.out.println();
        // ͼ����Ҫ�Ӵ��̼���
        image.display();
    }
}
package pattern.proxy_6;

/**
 * @Date 2019/11/30 14:55
 * @ 代理模型-结构型模式
 * 目标：为其他对象提供一种代理，以控制对这个对象的访问。说白了就是一个类代表另一个类的功能。
 * 关键代码：增加中间层，实现代理类和委托类组合（需要二者继承同一个接口）
 * 注意事项：1、和适配器模式的区别：适配器模式主要改变所考虑对象的接口，而代理模式不能改变所代理类的接口。 2、和装饰器模式的区别：装饰器模式为了增强功能，而代理模式是为了加以控制。
 * 参考网址：[菜鸟教程](!https://www.runoob.com/design-pattern/proxy-pattern.html)
 *
 *
 * 代码分析：
 * 1. 这个代理模式可以类比静态代理，主要有三个成员，抽象委托接口，代理类和委托类，代理类和委托类实现同一个接口（即抽象委托接口），不然的话，在外部调用时，不能用抽象委托接口(Image)引用接收代理类对象。
 * 2. 代理类只需要把委托类里的方法在自己的方法里包装一下（处理一下），即可以被外部调用。这里，有个点，需要注意，代理类通过聚合，持有了一个委托类的引用，正因为这个引用的存在才可以在代理类中调用委托类的方法
 * 3. UML类图如下
 *
 * 补充：
 * 1. 如果根据字节码的创建时机来分类，可以分为静态代理和动态代理
 * - 所谓`静态代理`也就是在程序运行前就已经存在代理类的字节码文件，代理类和真实主题角色的关系在运行前就确定了。
 * - 而`动态代理`的源码是在程序运行期间由JVM根据反射等机制动态的生成，所以在运行前并不存在代理类的字节码文件。
 *
 * 2. java的动态代理有两种实现方式，`JDK 动态代理和Cglib 动态代理`
 *  > JDK动态代理：
 *  原理：利用拦截器（拦截器必须实现InvocationHandler）加上反射机制生成一个实现代理接口的匿名类，在调用具体方法前调用InvokeHandler来处理。
 *  要求：委托类（目标对象）需要实现接口
 *
 *  > CGLIB 动态代理：
 *  原理：利用ASM开源包，对代理对象类的class文件加载进来，通过修改其字节码生成子类来处理。
 *  要求：委托类（目标对象）没有实现接口，必须使用CGLIB，如果实现了接口可以强制使用CGLIB。
 *
 *  参考链接：
 *  [Java动态代理](https://juejin.im/post/5ad3e6b36fb9a028ba1fee6a)
 * [Java 动态代理详解](https://juejin.im/post/5c1ca8df6fb9a049b347f55c) 推荐
 */

//步骤1、创建一个代理类和委托类都需要实现的接口
interface Image {
    void display();
}

// 步骤2、创建实现接口的委托类RealImage，代理类ProxyImage
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

    private RealImage realImage;  // 通过聚合来实现，让代理类持有一个委托类的引用即可
    private String fileName;

    public ProxyImage(String fileName){
        this.fileName = fileName;
    }

    @Override
    public void display() {
        if(realImage == null){ // 第一次调用，realImage没有初始化，会触发RealImage的构造器，第二次调用时，realImage不为null，构造器不会触发
            realImage = new RealImage(fileName);
        }
        realImage.display();
    }
}

// 步骤3、当被请求时，使用ProxyImage来获取RealImage类的对象
public class ProxyPatternDemo {

    public static void main(String[] args) {
        Image image = new ProxyImage("test_10mb.jpg");

        // 图像将从磁盘加载
        image.display();
        System.out.println();
        // 图像不需要从磁盘加载
        image.display();
    }
}
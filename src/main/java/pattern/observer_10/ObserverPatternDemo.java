package pattern.observer_10;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date 2019/12/5 16:28
 * @ 观察者模式：行为型模式
 * 作用：定义对象间的一种一对多的依赖关系，当一个对象的状态发生改变时，所有依赖于它的对象都得到通知并自动更新。
 * 何时使用：一个对象（被观察者，示例是Subject类）的状态发生改变，所有的依赖对象（观察者对象，示例是Observer类）都将得到通知，进行广播通知。
 * 关键代码：在抽象类（被观察者类）里有一个ArrayList存放观察者们。
 * 注意事项：
 *      1. JAVA 中已经有了对观察者模式的支持类。
 *      2. 避免循环引用。
 *      3. 如果顺序执行，某一观察者错误会导致系统卡壳，一般采用异步方式。
 * 参考网址：https://www.runoob.com/design-pattern/observer-pattern.html
 *
 * 代码分析：
 * 1. 创建被观察者Subject类，里面有一个ArrayList存放后续添加进去的观察者。
 * 2. 创建观察者类Observer类及其实现类，每个实现类的构造器参数包含了Subject引用，如此一来，在初始化观察者对象时，就可以把自己添加进被观察者的ArrayList中。也可以不通过这种方式，主动调用attach()方法也可以。
 * 3. 当调用subject对象的setState()方法时，触发一系列操作。
 *
 */


// ===========步骤一、创建Subject类（被观察者）===========
class Subject {

    private List<Observer> observers
            = new ArrayList<Observer>();
    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {  // 如果state变动（被调用）就通知所有的观察者对象
        this.state = state;
        notifyAllObservers();
    }

    public void attach(Observer observer){ // 添加观察者对象
        observers.add(observer);
    }

    public void notifyAllObservers(){   // 通知所有的观察者对象（通过调用观察者对象的update()方法）
        for (Observer observer : observers) {
            observer.update();
        }
    }
}

// ===========步骤二、创建Observer类（观察者）===========
abstract class Observer {
    protected Subject subject;
    public abstract void update();  // 在被观察者被修改时，被观察者会通过对象里观察者引用调用他们的update()方法
}

// ===========步骤三、创建实体观察者类===========
class BinaryObserver extends Observer {

    public BinaryObserver(Subject subject){
        this.subject = subject;
        this.subject.attach(this);  // 初始化BinaryObserver对象时，就把自己放入到了指定subject对象的观察者列表中
    }

    @Override
    public void update() {
        System.out.println( "Binary String: "
                + Integer.toBinaryString( subject.getState() ) );
    }
}

class OctalObserver extends Observer {

    public OctalObserver(Subject subject){
        this.subject = subject;
        this.subject.attach(this); // 同上
    }

    @Override
    public void update() {
        System.out.println( "Octal String: "
                + Integer.toOctalString( subject.getState() ) );
    }
}

class HexObserver extends Observer {

    public HexObserver(Subject subject){
        this.subject = subject;
        this.subject.attach(this);  // 同上
    }

    @Override
    public void update() {
        System.out.println( "Hex String: "
                + Integer.toHexString( subject.getState() ).toUpperCase() );
    }
}

// 步骤四、使用被观察者和实体观察者对象

public class ObserverPatternDemo {
    public static void main(String[] args) {
        Subject subject = new Subject();

        new HexObserver(subject);
        new OctalObserver(subject);
        new BinaryObserver(subject);

        System.out.println("First state change: 15");
        subject.setState(15);
        System.out.println("Second state change: 10");
        subject.setState(10);
    }
}


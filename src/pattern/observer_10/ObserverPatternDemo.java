package pattern.observer_10;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date 2019/12/5 16:28
 * @ �۲���ģʽ����Ϊ��ģʽ
 * ���ã����������һ��һ�Զ��������ϵ����һ�������״̬�����ı�ʱ���������������Ķ��󶼵õ�֪ͨ���Զ����¡�
 * ��ʱʹ�ã�һ�����󣨱��۲��ߣ�ʾ����Subject�ࣩ��״̬�����ı䣬���е��������󣨹۲��߶���ʾ����Observer�ࣩ�����õ�֪ͨ�����й㲥֪ͨ��
 * �ؼ����룺�ڳ����ࣨ���۲����ࣩ����һ��ArrayList��Ź۲����ǡ�
 * ע�����
 *      1. JAVA ���Ѿ����˶Թ۲���ģʽ��֧���ࡣ
 *      2. ����ѭ�����á�
 *      3. ���˳��ִ�У�ĳһ�۲��ߴ���ᵼ��ϵͳ���ǣ�һ������첽��ʽ��
 * �ο���ַ��https://www.runoob.com/design-pattern/observer-pattern.html
 *
 * ���������
 * 1. �������۲���Subject�࣬������һ��ArrayList��ź�����ӽ�ȥ�Ĺ۲��ߡ�
 * 2. �����۲�����Observer�༰��ʵ���࣬ÿ��ʵ����Ĺ���������������Subject���ã����һ�����ڳ�ʼ���۲��߶���ʱ���Ϳ��԰��Լ���ӽ����۲��ߵ�ArrayList�С�Ҳ���Բ�ͨ�����ַ�ʽ����������attach()����Ҳ���ԡ�
 * 3. ������subject�����setState()����ʱ������һϵ�в�����
 *
 */


// ===========����һ������Subject�ࣨ���۲��ߣ�===========
class Subject {

    private List<Observer> observers
            = new ArrayList<Observer>();
    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {  // ���state�䶯�������ã���֪ͨ���еĹ۲��߶���
        this.state = state;
        notifyAllObservers();
    }

    public void attach(Observer observer){ // ��ӹ۲��߶���
        observers.add(observer);
    }

    public void notifyAllObservers(){   // ֪ͨ���еĹ۲��߶���ͨ�����ù۲��߶����update()������
        for (Observer observer : observers) {
            observer.update();
        }
    }
}

// ===========�����������Observer�ࣨ�۲��ߣ�===========
abstract class Observer {
    protected Subject subject;
    public abstract void update();  // �ڱ��۲��߱��޸�ʱ�����۲��߻�ͨ��������۲������õ������ǵ�update()����
}

// ===========������������ʵ��۲�����===========
class BinaryObserver extends Observer {

    public BinaryObserver(Subject subject){
        this.subject = subject;
        this.subject.attach(this);  // ��ʼ��BinaryObserver����ʱ���Ͱ��Լ����뵽��ָ��subject����Ĺ۲����б���
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
        this.subject.attach(this); // ͬ��
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
        this.subject.attach(this);  // ͬ��
    }

    @Override
    public void update() {
        System.out.println( "Hex String: "
                + Integer.toHexString( subject.getState() ).toUpperCase() );
    }
}

// �����ġ�ʹ�ñ��۲��ߺ�ʵ��۲��߶���

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


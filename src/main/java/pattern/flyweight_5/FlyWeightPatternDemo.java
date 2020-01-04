package pattern.flyweight_5;

import java.util.HashMap;

/**
 * @Date 2019/12/2 14:23
 * @ ��Ԫģʽ-�ṹ��ģʽ
 * Ŀ�꣺��Ҫ���ڼ��ٴ���������������Լ����ڴ�ռ�ú��ṩ���ܣ����д�������ʱ�������й�ͬ�Ĳ��ֳ���������������ͬ��ҵ������ֱ�ӷ����ڴ������еĶ��󣬱����ظ�������
 * �ؼ����룺 ��HashMap�洢��Щ����
 * Ӧ��ʵ����1.Java�е�String���ַ�������� 2. ���ݿ�����ݳ�
 * �ο���ַ��https://www.runoob.com/design-pattern/flyweight-pattern.html
 *
 *
 * ���������
 * 1. ������Shape�ӿں�ʵ�ֽӿڵ�ʵ����Circle
 * 2. ���Ŷ�����ShapeFactory�����ڲ�������һ��HashMap,������Ų�ͬ��ɫ��Circle����ʵ����ͬ��ɫ��Circle������
 * 3. UML��ͼ��
 * ��Ԫģʽ�����������е�ͬ��������δ�ҵ�ƥ��Ķ����򴴽��¶������ǽ�ͨ������ 5 ������������ 20 ���ֲ��ڲ�ͬλ�õ�Բ����ʾ����ģʽ������ֻ�� 5 �ֿ��õ���ɫ������ color ���Ա�����������е� Circle ����
 *
 */


// ����1������һ���ӿ�
interface Shape {
    void draw();
}

// ����2������ʵ�ֽӿڵ�ʵ����
class Circle implements Shape {
    private String color;
    private int x;
    private int y;
    private int radius;

    public Circle(String color){
        this.color = color;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public void draw() {
        System.out.println("Circle: Draw() [Color : " + color
                +", x : " + x +", y :" + y +", radius :" + radius);
    }
}

// ����3������һ�����������ɻ��ڸ�����Ϣ��ʵ����Ķ���
class ShapeFactory {
    private static final HashMap<String, Shape> circleMap = new HashMap<>();

    public static Shape getCircle(String color) {
        Circle circle = (Circle)circleMap.get(color);

        if(circle == null) {
            circle = new Circle(color);
            circleMap.put(color, circle);
            System.out.println("Creating circle of color : " + color); // ʹ��err�����ʹ˳�򲻶ԣ����õ��߼���һ���������������
        }
        return circle;
    }
}

// ����4��ʹ�����������Ĺ�����ͨ��������ɫ��Ϣ����ȡʵ����Ķ���
public class FlyWeightPatternDemo {
    private static final String colors[] =
            { "Red", "Green", "Blue", "White", "Black" };
    public static void main(String[] args) {

        for(int i=0; i < 20; ++i) {
            Circle circle =
                    (Circle) ShapeFactory.getCircle(getRandomColor());
            circle.setX(getRandomX());
            circle.setY(getRandomY());
            circle.setRadius(100);
            circle.draw();
        }
    }
    private static String getRandomColor() {
        return colors[(int)(Math.random()*colors.length)];
    }
    private static int getRandomX() {
        return (int)(Math.random()*100 );
    }
    private static int getRandomY() {
        return (int)(Math.random()*100);
    }
}


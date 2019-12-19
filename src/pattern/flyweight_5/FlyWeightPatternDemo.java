package pattern.flyweight_5;

import java.util.HashMap;

/**
 * @Date 2019/12/2 14:23
 * @ 享元模式-结构型模式
 * 目标：主要用于减少创建对象的数量，以减少内存占用和提供性能，当有大量对象时，把其中共同的部分抽象出来，如果有相同的业务请求，直接返回内存中已有的对象，避免重复创建。
 * 关键代码： 用HashMap存储这些对象
 * 应用实例：1.Java中的String，字符串缓冲池 2. 数据库的数据池
 * 参考网址：https://www.runoob.com/design-pattern/flyweight-pattern.html
 *
 *
 * 代码分析：
 * 1. 创建了Shape接口和实现接口的实体类Circle
 * 2. 接着定义了ShapeFactory，类内部创建了一个HashMap,用来存放不同颜色的Circle对象，实现相同颜色的Circle对象复用
 * 3. UML类图：
 * 享元模式尝试重用现有的同类对象，如果未找到匹配的对象，则创建新对象。我们将通过创建 5 个对象来画出 20 个分布于不同位置的圆来演示这种模式。由于只有 5 种可用的颜色，所以 color 属性被用来检查现有的 Circle 对象。
 *
 */


// 步骤1、创建一个接口
interface Shape {
    void draw();
}

// 步骤2、创建实现接口的实体类
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

// 步骤3、创建一个工厂，生成基于给定信息的实体类的对象
class ShapeFactory {
    private static final HashMap<String, Shape> circleMap = new HashMap<>();

    public static Shape getCircle(String color) {
        Circle circle = (Circle)circleMap.get(color);

        if(circle == null) {
            circle = new Circle(color);
            circleMap.put(color, circle);
            System.out.println("Creating circle of color : " + color); // 使用err输出会使顺序不对，调用的逻辑不一样，会优先输出。
        }
        return circle;
    }
}

// 步骤4、使用上述创建的工厂，通过传递颜色信息来获取实体类的对象
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


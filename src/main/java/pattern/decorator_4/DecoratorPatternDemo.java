package pattern.decorator_4;

/**
 * @Date 2019/11/30 10:02
 * @ 装饰器模式-结构型模式，是继承的一个替代模式
 * 意图：动态地给一个对象添加一些额外的职责，同时又不改变其结构，作为现在类的一个包装。就增加功能来说装饰器模式相比子类继承更为灵活
 * 使用场景：不想增加很多子类的情况下扩展类.PS:装饰类和被装饰类可以独立发展，不会相互耦合
 * 关键代码：Decorator类充当抽象角色，不应该具体实现；修饰类引用和继承Decorator类，具体扩展类重写父类方法。
 * 参考网址：https://www.runoob.com/design-pattern/decorator-pattern.html
 * https://blog.csdn.net/linton1/article/details/103321995
 *
 * 代码分析：
 * 1. Shape接口和Circle类，Rectangle类原先已存在，在此基础上设计可以画不同颜色的形状
 * 2. 其中ShapeDecorator抽象类实现Shape接口，并将Shape作为它的实例变量（设计原则：装饰器类应为抽象类），装饰器实现了Shape接口，这样后续产生的装饰器对象可以使用draw()方法,而将Shape作为实例变量，这样的好处是新建对象时，可以通过构造器传入类型，初始化Shape引用，从而知道具体调用哪个子类的draw()方法。装饰器类的具体实现类定义了具体想要额外增添的其他功能，在这里是重写了draw()。
 * 3. 通过额外的两个类装饰器类ShapeDecorator 和装饰器实现类RedShapeDecorator，就实现了所有子类形状画出红色的功能。如果不采用这种设计模式，有如下两种其他方案，（1）重写每个Shape子类的draw()方法，加入颜色的逻辑[违反封闭-开放原则，pass] (2)额外增加Shape的子类，能实现红色边框的子类，这样，虽然符合了封闭-开放原则，但是加入Shape有10个子类，就必须新添加10个子类，代码太多且冗余，pass。
 * 4. 使用装饰器模式，新添加2个类就可以完成相同的功能，且代码优雅，逻辑清晰，大大凸显了设计模式的优势。
 * 5. UML类图略
 *
 */


// 步骤1、创建Shape接口
interface Shape {
    void draw();
}

class Rectangle implements Shape {

    @Override
    public void draw() {
        System.out.println("Shape: Rectangle");
    }
}

// 步骤2、创建Shape接口的实现类
class Circle implements Shape {

    @Override
    public void draw() {
        System.out.println("Shape: Circle");
    }
}

// 重要！ 步骤3、创建实现Shape接口的抽象装饰类，同时把Shape作为它的实例变量
abstract class ShapeDecorator implements Shape {
    protected Shape decoratedShape;

    public ShapeDecorator(Shape decoratedShape){
        this.decoratedShape = decoratedShape;
    }

    public void draw(){
        decoratedShape.draw();
    }
}

// 重要！ 步骤4、创建扩展了 ShapeDecorator 类的实体装饰类
class RedShapeDecorator extends ShapeDecorator {

    public RedShapeDecorator(Shape decoratedShape) { // 构造器
        super(decoratedShape);
    }

    @Override
    public void draw() {
        decoratedShape.draw();
        setRedBorder(decoratedShape);
    }

    private void setRedBorder(Shape decoratedShape){
        System.out.println("Border Color: Red");
    }
}

// 步骤5、测试
public class DecoratorPatternDemo {
    public static void main(String[] args) {

        Shape circle = new Circle();
//        ShapeDecorator redCircle = new RedShapeDecorator(new Circle());
//        ShapeDecorator redRectangle = new RedShapeDecorator(new Rectangle());
        Shape redCircle = new RedShapeDecorator(new Circle());
        Shape redRectangle = new RedShapeDecorator(new Rectangle());
        System.out.println("Circle with normal border");
        circle.draw();

        System.out.println("\nCircle of red border");
        redCircle.draw();

        System.out.println("\nRectangle of red border");
        redRectangle.draw();
    }
}
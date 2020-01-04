package pattern.decorator_4;

/**
 * @Date 2019/11/30 10:02
 * @ װ����ģʽ-�ṹ��ģʽ���Ǽ̳е�һ�����ģʽ
 * ��ͼ����̬�ظ�һ���������һЩ�����ְ��ͬʱ�ֲ��ı���ṹ����Ϊ�������һ����װ�������ӹ�����˵װ����ģʽ�������̳и�Ϊ���
 * ʹ�ó������������Ӻܶ�������������չ��.PS:װ����ͱ�װ������Զ�����չ�������໥���
 * �ؼ����룺Decorator��䵱�����ɫ����Ӧ�þ���ʵ�֣����������úͼ̳�Decorator�࣬������չ����д���෽����
 * �ο���ַ��https://www.runoob.com/design-pattern/decorator-pattern.html
 * https://blog.csdn.net/linton1/article/details/103321995
 *
 * ���������
 * 1. Shape�ӿں�Circle�࣬Rectangle��ԭ���Ѵ��ڣ��ڴ˻�������ƿ��Ի���ͬ��ɫ����״
 * 2. ����ShapeDecorator������ʵ��Shape�ӿڣ�����Shape��Ϊ����ʵ�����������ԭ��װ������ӦΪ�����ࣩ��װ����ʵ����Shape�ӿڣ���������������װ�����������ʹ��draw()����,����Shape��Ϊʵ�������������ĺô����½�����ʱ������ͨ���������������ͣ���ʼ��Shape���ã��Ӷ�֪����������ĸ������draw()������װ������ľ���ʵ���ඨ���˾�����Ҫ����������������ܣ�����������д��draw()��
 * 3. ͨ�������������װ������ShapeDecorator ��װ����ʵ����RedShapeDecorator����ʵ��������������״������ɫ�Ĺ��ܡ�����������������ģʽ������������������������1����дÿ��Shape�����draw()������������ɫ���߼�[Υ�����-����ԭ��pass] (2)��������Shape�����࣬��ʵ�ֺ�ɫ�߿�����࣬��������Ȼ�����˷��-����ԭ�򣬵��Ǽ���Shape��10�����࣬�ͱ��������10�����࣬����̫�������࣬pass��
 * 4. ʹ��װ����ģʽ�������2����Ϳ��������ͬ�Ĺ��ܣ��Ҵ������ţ��߼����������͹�������ģʽ�����ơ�
 * 5. UML��ͼ��
 *
 */


// ����1������Shape�ӿ�
interface Shape {
    void draw();
}

class Rectangle implements Shape {

    @Override
    public void draw() {
        System.out.println("Shape: Rectangle");
    }
}

// ����2������Shape�ӿڵ�ʵ����
class Circle implements Shape {

    @Override
    public void draw() {
        System.out.println("Shape: Circle");
    }
}

// ��Ҫ�� ����3������ʵ��Shape�ӿڵĳ���װ���࣬ͬʱ��Shape��Ϊ����ʵ������
abstract class ShapeDecorator implements Shape {
    protected Shape decoratedShape;

    public ShapeDecorator(Shape decoratedShape){
        this.decoratedShape = decoratedShape;
    }

    public void draw(){
        decoratedShape.draw();
    }
}

// ��Ҫ�� ����4��������չ�� ShapeDecorator ���ʵ��װ����
class RedShapeDecorator extends ShapeDecorator {

    public RedShapeDecorator(Shape decoratedShape) { // ������
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

// ����5������
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
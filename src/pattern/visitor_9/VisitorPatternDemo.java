package pattern.visitor_9;

/**
 * @Date 2019/12/5 15:36
 * @ 访问者模式-行为型模式
 * 作用：主要讲数据结构与数据操作分离。主要解决稳定的数据结构和易变的操作耦合问题
 * 关键代码：在数据基础类里面有一个方法接受访问者（就是ComputerPart中的accept方法），将自身引用传入访问者（ComputerPartVisitor接口中用ComputerPart接口的实现类做参数，传入引用）
 * 参考网址：https://www.runoob.com/design-pattern/visitor-pattern.html
 * 使用场景：
 *      1.对象结构中对象对应的类很少改变，但经常需要在此对象结构上定义新的操作。
 *      2.需要对一个对象结构中的对象进行很多不同的并且不相关的操作，而需要避免让这些操作"污染"这些对象的类，也不希望在增加新操作时修改这些类。
 *
 * 代码分析：
 * 1. 创建一个定义接受操作的 ComputerPart 接口。Keyboard、Mouse、Monitor 和 Computer 是实现了 ComputerPart 接口的实体类。
 * 2. 定义另一个接口 ComputerPartVisitor，它定义了访问者类的操作。
 * 3. Computer 使用实体访问者来执行相应的动作。
 *
 * 一句话概括：访问者模式，主要是将数据结构和数据操作分离。
 *
 */


// ============步骤一、定义表示元素的接口============
interface ComputerPart {
    public void accept(ComputerPartVisitor computerPartVisitor); // 这个accept方法就是对外提供接待访问者的接口
}

// ============步骤二、创建扩展上述接口的实体类============
class Keyboard  implements ComputerPart {

    @Override
    public void accept(ComputerPartVisitor computerPartVisitor) {
        computerPartVisitor.visit(this);
    }
}

class Monitor  implements ComputerPart {

    @Override
    public void accept(ComputerPartVisitor computerPartVisitor) {
        computerPartVisitor.visit(this);
    }
}

class Mouse  implements ComputerPart {

    @Override
    public void accept(ComputerPartVisitor computerPartVisitor) {
        computerPartVisitor.visit(this);
    }
}

class Computer implements ComputerPart {  // 四个部分都实现于ComputerPart，但是Computer包含其他三个

    ComputerPart[] parts;

    public Computer(){
        parts = new ComputerPart[] {new Mouse(), new Keyboard(), new Monitor()};
    }


    @Override
    public void accept(ComputerPartVisitor computerPartVisitor) { // 与众不同的地方
        for (int i = 0; i < parts.length; i++) {
            parts[i].accept(computerPartVisitor);
        }
        computerPartVisitor.visit(this);
    }
}

// ============步骤三、定义一个表示访问者的接口============
interface ComputerPartVisitor {
    public void visit(Computer computer);
    public void visit(Mouse mouse);
    public void visit(Keyboard keyboard);
    public void visit(Monitor monitor);
}

// 步骤四、创建实现了上述访问者接口的实体访问者，就用它来访问电脑各个组成部分
class ComputerPartDisplayVisitor implements ComputerPartVisitor {

    @Override
    public void visit(Computer computer) {
        System.out.println("Displaying Computer.");
    }

    @Override
    public void visit(Mouse mouse) {
        System.out.println("Displaying Mouse.");
    }

    @Override
    public void visit(Keyboard keyboard) {
        System.out.println("Displaying Keyboard.");
    }

    @Override
    public void visit(Monitor monitor) {
        System.out.println("Displaying Monitor.");
    }
}

// 步骤五、使用ComputerPartDisplayVisitor 来显示 Computer 的组成部分。

public class VisitorPatternDemo {
    public static void main(String[] args) {

        ComputerPart computer = new Computer();
        computer.accept(new ComputerPartDisplayVisitor());
    }
}
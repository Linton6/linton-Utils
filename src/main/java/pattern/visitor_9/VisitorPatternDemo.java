package pattern.visitor_9;

/**
 * @Date 2019/12/5 15:36
 * @ ������ģʽ-��Ϊ��ģʽ
 * ���ã���Ҫ�����ݽṹ�����ݲ������롣��Ҫ����ȶ������ݽṹ���ױ�Ĳ����������
 * �ؼ����룺�����ݻ�����������һ���������ܷ����ߣ�����ComputerPart�е�accept�����������������ô�������ߣ�ComputerPartVisitor�ӿ�����ComputerPart�ӿڵ�ʵ�������������������ã�
 * �ο���ַ��https://www.runoob.com/design-pattern/visitor-pattern.html
 * ʹ�ó�����
 *      1.����ṹ�ж����Ӧ������ٸı䣬��������Ҫ�ڴ˶���ṹ�϶����µĲ�����
 *      2.��Ҫ��һ������ṹ�еĶ�����кܶ಻ͬ�Ĳ��Ҳ���صĲ���������Ҫ��������Щ����"��Ⱦ"��Щ������࣬Ҳ��ϣ���������²���ʱ�޸���Щ�ࡣ
 *
 * ���������
 * 1. ����һ��������ܲ����� ComputerPart �ӿڡ�Keyboard��Mouse��Monitor �� Computer ��ʵ���� ComputerPart �ӿڵ�ʵ���ࡣ
 * 2. ������һ���ӿ� ComputerPartVisitor���������˷�������Ĳ�����
 * 3. Computer ʹ��ʵ���������ִ����Ӧ�Ķ�����
 *
 * һ�仰������������ģʽ����Ҫ�ǽ����ݽṹ�����ݲ������롣
 *
 */


// ============����һ�������ʾԪ�صĽӿ�============
interface ComputerPart {
    public void accept(ComputerPartVisitor computerPartVisitor); // ���accept�������Ƕ����ṩ�Ӵ������ߵĽӿ�
}

// ============�������������չ�����ӿڵ�ʵ����============
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

class Computer implements ComputerPart {  // �ĸ����ֶ�ʵ����ComputerPart������Computer������������

    ComputerPart[] parts;

    public Computer(){
        parts = new ComputerPart[] {new Mouse(), new Keyboard(), new Monitor()};
    }


    @Override
    public void accept(ComputerPartVisitor computerPartVisitor) { // ���ڲ�ͬ�ĵط�
        for (int i = 0; i < parts.length; i++) {
            parts[i].accept(computerPartVisitor);
        }
        computerPartVisitor.visit(this);
    }
}

// ============������������һ����ʾ�����ߵĽӿ�============
interface ComputerPartVisitor {
    public void visit(Computer computer);
    public void visit(Mouse mouse);
    public void visit(Keyboard keyboard);
    public void visit(Monitor monitor);
}

// �����ġ�����ʵ�������������߽ӿڵ�ʵ������ߣ������������ʵ��Ը�����ɲ���
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

// �����塢ʹ��ComputerPartDisplayVisitor ����ʾ Computer ����ɲ��֡�

public class VisitorPatternDemo {
    public static void main(String[] args) {

        ComputerPart computer = new Computer();
        computer.accept(new ComputerPartDisplayVisitor());
    }
}
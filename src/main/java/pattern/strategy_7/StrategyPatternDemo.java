package pattern.strategy_7;

/**
 * @Date 2019/11/30 11:15
 * @ ����ģʽ-��Ϊ��ģʽ��
 * �ڲ���ģʽ�У�ʵ�����봴����ʾ���ֲ��ԵĶ����һ����Ϊ���Ų��Զ���ı���ı��context���󡣲��Զ���ı�context�����ִ���㷨��
 * Ŀ�ĺ��ֶΣ������Ƶ��㷨��װ���࣬ʹ���ǿ���������滻�������ʹ��if...else��������ѡ������������ĸ��Ӻ�����ά����
 * �ؼ����룺���з�װ������㷨��ʵ��ͬһ���ӿڡ�
 * ע��������һ��ϵͳ�Ĳ��Զ����ĸ�������Ҫ����ʹ�û��ģʽ��������������͵����⡣
 * �ο����ӣ�https://www.runoob.com/design-pattern/strategy-pattern.html
 *
 * ���������
 * 1. ���ȣ�Ҫ��ȷ�����Է�����ʵ��Ϊ�˴������ѡ�����ĸ����ͣ�����װ����ģʽ�Ǽ̳е�һ�����ģʽ������˵�ġ������������ָ�����ϵ����������ָ�̳���ʵ�ֵĹ��ܣ��ò���ģʽ��ʵ�ָ��������ţ���
 * 2. ʵ�������߼����������ص�����Context���е�Strategyʵ�������ǧ�������ǣ���Ϊ�������ʵ�����������ø������Զ�����Ըı�context����Ϊ����ִ��context��executeStrategy()����ʱ���ڳ�ʼ��contextʱ������Ĳ�ͬ�������Ͳ�����������executeStrategy()����ʵ�ʵ��õĴ���λ�á�
 * 3. UML��ͼ���£�
 */

// ����1���������Խӿ�
interface Strategy {
    public int doOperation(int num1, int num2);
}

// ����2��ʵ�ֲ��Խӿڵ���
class OperationAdd implements Strategy {
    @Override
    public int doOperation(int num1, int num2) {
        return num1 + num2;
    }
}

class OperationSubstract implements Strategy {
    @Override
    public int doOperation(int num1, int num2) {
        return num1 - num2;
    }
}

class OperationMultiply implements Strategy {
    @Override
    public int doOperation(int num1, int num2) {
        return num1 * num2;
    }
}

// �ص㣡 ����3������Context�࣬���Ų��Զ���Ĳ�ͬ���ı���Ϊ��������Ҫ��Strategy��Ϊ����ʵ������
class Context {
    private Strategy strategy; // �ؼ���

    public Context(Strategy strategy){
        this.strategy = strategy;
    }

    public int executeStrategy(int num1, int num2){
        return strategy.doOperation(num1, num2);
    }
}

// ����4������
public class StrategyPatternDemo {
    public static void main(String[] args) {
        Context context = new Context(new OperationAdd());
        System.out.println("10 + 5 = " + context.executeStrategy(10, 5));

        context = new Context(new OperationSubstract());
        System.out.println("10 - 5 = " + context.executeStrategy(10, 5));

        context = new Context(new OperationMultiply());
        System.out.println("10 * 5 = " + context.executeStrategy(10, 5));
    }
}
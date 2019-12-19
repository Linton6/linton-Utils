package pattern.strategy_7;

/**
 * @Date 2019/11/30 11:15
 * @ 策略模式-行为型模式。
 * 在策略模式中，实例代码创建表示各种策略的对象和一个行为随着策略对象改变而改变的context对象。策略对象改变context对象的执行算法。
 * 目的和手段：将相似的算法封装成类，使他们可以任意的替换，来解决使用if...else多重条件选择语句所带来的复杂和难以维护。
 * 关键代码：所有封装成类的算法，实现同一个接口。
 * 注意事项：如果一个系统的策略多于四个，就需要考虑使用混合模式，解决策略类膨胀的问题。
 * 参考链接：https://www.runoob.com/design-pattern/strategy-pattern.html
 *
 * 代码分析：
 * 1. 首先，要明确，策略分析其实是为了代替多重选择语句的复杂型，就像装饰器模式是继承的一种替代模式（这里说的“替代”，不是指技术上的替代，而是指继承能实现的功能，用策略模式来实现更简洁更优雅）。
 * 2. 实例代码逻辑很清晰，重点在于Context类中的Strategy实例，这个千万不能忘记，因为有了这个实例变量，才让各个策略对象可以改变context的行为。在执行context的executeStrategy()方法时，在初始化context时，传入的不同策略类型参数，决定了executeStrategy()方法实际调用的代码位置。
 * 3. UML类图如下：
 */

// 步骤1、创建策略接口
interface Strategy {
    public int doOperation(int num1, int num2);
}

// 步骤2、实现策略接口的类
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

// 重点！ 步骤3、创建Context类，随着策略对象的不同而改变行为，所以需要把Strategy作为它的实例变量
class Context {
    private Strategy strategy; // 关键点

    public Context(Strategy strategy){
        this.strategy = strategy;
    }

    public int executeStrategy(int num1, int num2){
        return strategy.doOperation(num1, num2);
    }
}

// 步骤4、测试
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
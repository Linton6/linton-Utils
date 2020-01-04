package pattern.command_3;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date 2019/12/4 18:37
 * @ 命令模式：数据驱动的设计模式，行为型模式
 * 核心理解：将请求封装成一个对象，传给调用者，调用者基于请求的类型确定哪个对象执行哪个命令。
 * 主要解决：将行为请求者和行为实现者解耦
 * 参考网址：https://www.runoob.com/design-pattern/command-pattern.html  讲的不好，不够清晰
 *
 * 一般来说，命令模式首先需要一个只有单一方法的接口，然后从该接口实现具有各自不同的行为的多个子类。接下来，我们就可以构造命令对象对象，并在需要的时候使用它们。
 *
 * 代码分析：
 * 1.首先创建命令的接口Order，然后创建作为请求的Stock类，实体命令类BuyStock和SellStock，实现Order接口，将执行实际命令的处理。
 * 2.创建作为调用对象的类Broker，Broker对象使用命令模式，基于命令的类型确定哪个对象执行哪个命令
 *
 *
 *
 *
 */

//===============================步骤一、创建一个命令接口============================
interface Order {
    void execute();
}

//===============================步骤二、 创建一个请求类============================
class Stock {

    private String name = "ABC";
    private int quantity = 10;

    Stock (String name) {
        this.name = name;
    }

    public void buy(){
        System.out.println("Stock [ Name: "+name+ ", Quantity: " + quantity +" ] bought");
    }
    public void sell(){
        System.out.println("Stock [ Name: "+name+",  Quantity: " + quantity +" ] sold");
    }
}


//===============================步骤三、 创建实现Order接口的实体类，即实体命令类BuyStock和SellStock，执行实际的命令处理============================
class BuyStock implements Order {
    private Stock abcStock;

    public BuyStock(Stock abcStock){ // constructor  实体命令类初始化参数是请求类
        this.abcStock = abcStock;
    }

    public void execute() {
        abcStock.buy();
    }
}

class SellStock implements Order {
    private Stock abcStock;   // 请求类的引用

    public SellStock(Stock abcStock){ // constructor
        this.abcStock = abcStock;
    }

    public void execute() {
        abcStock.sell();
    }
}

//===============================步骤四、创建命令的调用类============================
class Broker {
    private List<Order> orderList = new ArrayList<Order>(); // 在orderList放入命令，然后挨个执行

    public void takeOrder(Order order){
        orderList.add(order);
    }

    public void placeOrders(){
        for (Order order : orderList) {
            order.execute();
        }
        orderList.clear();
    }
}

//===============================步骤五、使用Broker类来接受并执行命令============================
// Broker对象使用命令模式，基于命令的类型确定哪个对象执行哪个命令
public class CommandPatternDemo {
    public static void main(String[] args) {
        Stock abcStock = new Stock("abcStock");  // 创建一个请求类
        Stock ABCStock = new Stock("ABCStock");

        BuyStock buyStockOrder = new BuyStock(abcStock); // 1. 将请求封装成一个对象，在需要的时候使用它们
        SellStock sellStockOrder = new SellStock(ABCStock);

        Broker broker = new Broker(); // 调用命令对象


        broker.takeOrder(buyStockOrder);
        broker.takeOrder(sellStockOrder); // 2. 调用者基于请求对象确定哪个Stock对象执行哪个Order命令

        broker.placeOrders();
    }
}

package pattern.command_3;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date 2019/12/4 18:37
 * @ ����ģʽ���������������ģʽ����Ϊ��ģʽ
 * ������⣺�������װ��һ�����󣬴��������ߣ������߻������������ȷ���ĸ�����ִ���ĸ����
 * ��Ҫ���������Ϊ�����ߺ���Ϊʵ���߽���
 * �ο���ַ��https://www.runoob.com/design-pattern/command-pattern.html  ���Ĳ��ã���������
 *
 * һ����˵������ģʽ������Ҫһ��ֻ�е�һ�����Ľӿڣ�Ȼ��Ӹýӿ�ʵ�־��и��Բ�ͬ����Ϊ�Ķ�����ࡣ�����������ǾͿ��Թ������������󣬲�����Ҫ��ʱ��ʹ�����ǡ�
 *
 * ���������
 * 1.���ȴ�������Ľӿ�Order��Ȼ�󴴽���Ϊ�����Stock�࣬ʵ��������BuyStock��SellStock��ʵ��Order�ӿڣ���ִ��ʵ������Ĵ���
 * 2.������Ϊ���ö������Broker��Broker����ʹ������ģʽ���������������ȷ���ĸ�����ִ���ĸ�����
 *
 *
 *
 *
 */

//===============================����һ������һ������ӿ�============================
interface Order {
    void execute();
}

//===============================������� ����һ��������============================
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


//===============================�������� ����ʵ��Order�ӿڵ�ʵ���࣬��ʵ��������BuyStock��SellStock��ִ��ʵ�ʵ������============================
class BuyStock implements Order {
    private Stock abcStock;

    public BuyStock(Stock abcStock){ // constructor  ʵ���������ʼ��������������
        this.abcStock = abcStock;
    }

    public void execute() {
        abcStock.buy();
    }
}

class SellStock implements Order {
    private Stock abcStock;   // �����������

    public SellStock(Stock abcStock){ // constructor
        this.abcStock = abcStock;
    }

    public void execute() {
        abcStock.sell();
    }
}

//===============================�����ġ���������ĵ�����============================
class Broker {
    private List<Order> orderList = new ArrayList<Order>(); // ��orderList�������Ȼ�󰤸�ִ��

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

//===============================�����塢ʹ��Broker�������ܲ�ִ������============================
// Broker����ʹ������ģʽ���������������ȷ���ĸ�����ִ���ĸ�����
public class CommandPatternDemo {
    public static void main(String[] args) {
        Stock abcStock = new Stock("abcStock");  // ����һ��������
        Stock ABCStock = new Stock("ABCStock");

        BuyStock buyStockOrder = new BuyStock(abcStock); // 1. �������װ��һ����������Ҫ��ʱ��ʹ������
        SellStock sellStockOrder = new SellStock(ABCStock);

        Broker broker = new Broker(); // �����������


        broker.takeOrder(buyStockOrder);
        broker.takeOrder(sellStockOrder); // 2. �����߻����������ȷ���ĸ�Stock����ִ���ĸ�Order����

        broker.placeOrders();
    }
}

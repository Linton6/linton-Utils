package pattern.chain_of_responsibility_2;

/**
 * @Date 2019/11/30 12:26
 * @ `责任链模式`-行为型模式-日志分级打印示例
 * `意图`：避免请求发送者和接受者耦合在一起，让多个对象都有可能接受请求，将这些对象连接成一条链，并且沿着这条链传递请求，知道有对象处理它为止。
 * `关键代码`：拦截的类都实现统一接口，Handler 里面聚合它自己，在 HandlerRequest 里判断是否合适，如果没达到条件则向下传递，向谁传递之前 set 进去。
 * 应用实例：三国杀中有人使用锦囊时，系统提示其他用户是否使用无懈可击，运用的就是责任链模式
 * 使用场景： 1、有多个对象可以处理同一个请求，具体哪个对象处理该请求由运行时刻自动确定。 2、在不明确指定接收者的情况下，向多个对象中的一个提交一个请求。 3、可动态指定一组对象处理请求。
 * 参考链接：https://www.runoob.com/design-pattern/chain-of-responsibility-pattern.html
 *
 * 代码分析：责任链模式，中设置nextLogger时，在初始化时就指定。
 * 1. 实例代码中表示的是日志打印功能，通过责任链模式将不同级别的日志信息通过不同的方式打印出来。
 * 2. AbstractLogger类作为抽象的接受者类，就是上文提到的Handler类，ChainPatternDemo作为发送者（其实描述的不准确，具体应该说发送者其实就是其中的一句代码，这个实例中没有明确的发送者对象。接受者类中比较重要的方法就是setNextLogger()，通过这个方法确定了后续中责任链的顺序，有点像链表，而logMessage()方法作为这个判断接受对象以及对收到的请求做的处理，就是上文中提到的HandlerRequest。
 * 3. 总体来说，代码思路比较明确，通过实现接受者接口，创建几种可以处理请求的Handler类，每个Handler类至少包含两个方法一个setNextLogger()[为了与其他handler对象构成责任链]，一个HandlerRequest()[对可接受对象或请求做的合法操作]
 */
//步骤1、创建抽象的接受者类，这里是记录器类
abstract class AbstractLogger {
    public static int INFO = 1;
    public static int DEBUG = 2;
    public static int ERROR = 3;

    protected int level;

    //责任链中的下一个元素
    protected AbstractLogger nextLogger;

    public void setNextLogger(AbstractLogger nextLogger){ // 设置下一个记录器
        this.nextLogger = nextLogger;
    }

    public void logMessage(int level, String message){
        if(this.level <= level){
            write(message);
        }
        if(nextLogger !=null){
            nextLogger.logMessage(level, message); // 转向下一个记录器的logMessage()方法
        }
    }

    abstract protected void write(String message);

}

//步骤2、创建扩展该记录器类的实体类
class ConsoleLogger extends AbstractLogger {

    public ConsoleLogger(int level){
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("Standard Console::Logger: " + message);
    }
}

class ErrorLogger extends AbstractLogger {

    public ErrorLogger(int level){
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("Error Console::Logger: " + message);
    }
}

class FileLogger extends AbstractLogger {

    public FileLogger(int level){
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("File::Logger: " + message);
    }
}

//步骤3、创建不同类型的记录器,赋予他们不同的错误级别,并在每个记录器中设置下一个记录器.每一个记录器中下一个记录器代表的是链的一部分.
public class ChainPatternDemo {

    private static AbstractLogger getChainOfLoggers(){ // 创建责任链

        AbstractLogger errorLogger = new ErrorLogger(AbstractLogger.ERROR);
        AbstractLogger fileLogger = new FileLogger(AbstractLogger.DEBUG);
        AbstractLogger consoleLogger = new ConsoleLogger(AbstractLogger.INFO);

        errorLogger.setNextLogger(fileLogger);
        fileLogger.setNextLogger(consoleLogger);

        return errorLogger;
    }

    public static void main(String[] args) {
        AbstractLogger loggerChain = getChainOfLoggers();

        loggerChain.logMessage(AbstractLogger.INFO, "This is an information.");

        loggerChain.logMessage(AbstractLogger.DEBUG,
                "This is a debug level information.");

        loggerChain.logMessage(AbstractLogger.ERROR,
                "This is an error information.");
    }
}

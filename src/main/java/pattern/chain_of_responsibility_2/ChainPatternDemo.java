package pattern.chain_of_responsibility_2;

/**
 * @Date 2019/11/30 12:26
 * @ `������ģʽ`-��Ϊ��ģʽ-��־�ּ���ӡʾ��
 * `��ͼ`�������������ߺͽ����������һ���ö�������п��ܽ������󣬽���Щ�������ӳ�һ����������������������������֪���ж�������Ϊֹ��
 * `�ؼ�����`�����ص��඼ʵ��ͳһ�ӿڣ�Handler ����ۺ����Լ����� HandlerRequest ���ж��Ƿ���ʣ����û�ﵽ���������´��ݣ���˭����֮ǰ set ��ȥ��
 * Ӧ��ʵ��������ɱ������ʹ�ý���ʱ��ϵͳ��ʾ�����û��Ƿ�ʹ����и�ɻ������õľ���������ģʽ
 * ʹ�ó����� 1���ж��������Դ���ͬһ�����󣬾����ĸ������������������ʱ���Զ�ȷ���� 2���ڲ���ȷָ�������ߵ�����£����������е�һ���ύһ������ 3���ɶ�ָ̬��һ�����������
 * �ο����ӣ�https://www.runoob.com/design-pattern/chain-of-responsibility-pattern.html
 *
 * ���������������ģʽ��������nextLoggerʱ���ڳ�ʼ��ʱ��ָ����
 * 1. ʵ�������б�ʾ������־��ӡ���ܣ�ͨ��������ģʽ����ͬ�������־��Ϣͨ����ͬ�ķ�ʽ��ӡ������
 * 2. AbstractLogger����Ϊ����Ľ������࣬���������ᵽ��Handler�࣬ChainPatternDemo��Ϊ�����ߣ���ʵ�����Ĳ�׼ȷ������Ӧ��˵��������ʵ�������е�һ����룬���ʵ����û����ȷ�ķ����߶��󡣽��������бȽ���Ҫ�ķ�������setNextLogger()��ͨ���������ȷ���˺�������������˳���е���������logMessage()������Ϊ����жϽ��ܶ����Լ����յ����������Ĵ��������������ᵽ��HandlerRequest��
 * 3. ������˵������˼·�Ƚ���ȷ��ͨ��ʵ�ֽ����߽ӿڣ��������ֿ��Դ��������Handler�࣬ÿ��Handler�����ٰ�����������һ��setNextLogger()[Ϊ��������handler���󹹳�������]��һ��HandlerRequest()[�Կɽ��ܶ�����������ĺϷ�����]
 */
//����1����������Ľ������࣬�����Ǽ�¼����
abstract class AbstractLogger {
    public static int INFO = 1;
    public static int DEBUG = 2;
    public static int ERROR = 3;

    protected int level;

    //�������е���һ��Ԫ��
    protected AbstractLogger nextLogger;

    public void setNextLogger(AbstractLogger nextLogger){ // ������һ����¼��
        this.nextLogger = nextLogger;
    }

    public void logMessage(int level, String message){
        if(this.level <= level){
            write(message);
        }
        if(nextLogger !=null){
            nextLogger.logMessage(level, message); // ת����һ����¼����logMessage()����
        }
    }

    abstract protected void write(String message);

}

//����2��������չ�ü�¼�����ʵ����
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

//����3��������ͬ���͵ļ�¼��,�������ǲ�ͬ�Ĵ��󼶱�,����ÿ����¼����������һ����¼��.ÿһ����¼������һ����¼�������������һ����.
public class ChainPatternDemo {

    private static AbstractLogger getChainOfLoggers(){ // ����������

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

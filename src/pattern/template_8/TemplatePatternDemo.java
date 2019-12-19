package pattern.template_8;

/**
 * @Date 2019/12/5 15:17
 * @ ģ��ģʽ-��Ϊ��ģʽ
 * ���ã�����һ�������е��㷨�Ǽܣ�����һЩ�����ӳٵ������С�ģ�巽����ע�⣡����˵���Ƿ�����ʹ��������Բ��ı�һ���㷨�Ľṹ���ɳ嶨����㷨��ĳЩ�ض����衣
 * �ؼ����룺ͨ���㷨�ڳ�����ʵ�֣��������岽����������ʵ�֡�
 * ע�����Ϊ��ֹ���������һ��ģ�巽��������final�ؼ���
 * �ο���ַ��https://www.runoob.com/design-pattern/template-pattern.html
 *
 * �������:
 * 1. ����һ�����������Game�����࣬���У�ģ�巽������Ϊfinal���������Ͳ��ᱻ��д��
 * 2. Cricket �� Football ����չ�� Game ��ʵ���࣬������д�˳�����ķ�����
 *
 * UML��ͼ
 *
 *
 */

// ����һ������һ�������࣬����ģ�巽��������Ϊ final
abstract class Game {
    abstract void initialize();
    abstract void startPlay();
    abstract void endPlay();

    //ģ��
    public final void play(){

        //��ʼ����Ϸ
        initialize();

        //��ʼ��Ϸ
        startPlay();

        //������Ϸ
        endPlay();
    }
}

// �������������չ���������ʵ����
class Cricket extends Game {

    @Override
    void endPlay() {
        System.out.println("Cricket Game Finished!");
    }

    @Override
    void initialize() {
        System.out.println("Cricket Game Initialized! Start playing.");
    }

    @Override
    void startPlay() {
        System.out.println("Cricket Game Started. Enjoy the game!");
    }
}

class Football extends Game {

    @Override
    void endPlay() {
        System.out.println("Football Game Finished!");
    }

    @Override
    void initialize() {
        System.out.println("Football Game Initialized! Start playing.");
    }

    @Override
    void startPlay() {
        System.out.println("Football Game Started. Enjoy the game!");
    }
}

// ��������ʹ�� Game ��ģ�巽�� play() ����ʾ��Ϸ�Ķ��巽ʽ

public class TemplatePatternDemo {
    public static void main(String[] args) {

        Game game = new Cricket();
        game.play();
        System.out.println();
        game = new Football();
        game.play();
    }
}

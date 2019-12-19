package pattern.template_8;

/**
 * @Date 2019/12/5 15:17
 * @ 模板模式-行为型模式
 * 作用：定义一个操作中的算法骨架，而降一些步骤延迟到子类中。模板方法（注意！这里说的是方法）使得子类可以不改变一个算法的结构即可冲定义该算法的某些特定步骤。
 * 关键代码：通用算法在抽象类实现，其他具体步骤在子类中实现。
 * 注意事项：为防止恶意操作，一般模板方法都加上final关键词
 * 参考网址：https://www.runoob.com/design-pattern/template-pattern.html
 *
 * 代码分析:
 * 1. 创建一个定义操作的Game抽象类，其中，模板方法设置为final，这样它就不会被重写。
 * 2. Cricket 和 Football 是扩展了 Game 的实体类，它们重写了抽象类的方法。
 *
 * UML类图
 *
 *
 */

// 步骤一、创建一个抽象类，它的模板方法被设置为 final
abstract class Game {
    abstract void initialize();
    abstract void startPlay();
    abstract void endPlay();

    //模板
    public final void play(){

        //初始化游戏
        initialize();

        //开始游戏
        startPlay();

        //结束游戏
        endPlay();
    }
}

// 步骤二、创建扩展了上述类的实体类
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

// 步骤三、使用 Game 的模板方法 play() 来演示游戏的定义方式

public class TemplatePatternDemo {
    public static void main(String[] args) {

        Game game = new Cricket();
        game.play();
        System.out.println();
        game = new Football();
        game.play();
    }
}

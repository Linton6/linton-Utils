package pattern.adapter_1;

/**
 * @Date 2019/11/30 8:59
 * @ 适配器模式 - 结构型模式
 * 关键代码：适配器继承或依赖已有的对象，实现想要的目标的接口
 * 意图：将一个类的接口转换成客户端希望的另一个接口。适配器模式使得原本由接口不兼容而不能一起工作的那些类可以一起工作
 * 注意事项：适配器不是在详细设计时添加的，而是解决正在服役的项目的问题
 * 参考网址 ：https://www.runoob.com/design-pattern/adapter-pattern.html
 *
 * 代码分析：
 * 1. MediaPlayer和AudioPlayer是原本存在的类，设计适配器模式时，不需要动MediaPlayer类，但是需要改动下AudioPlayer类。
 * 2. AdvancedMediaPlayer接口及其实现类VlcPlayer，Mp4Player，以及适配器类MediaAdapter是后续新增的，其中适配器类继承了MediaPlayer类，
 * 关联了AdvancedMediaPlayer接口。
 * 3. 适配器类中的逻辑处理是把需要适配（处理）的其他接口的内容放入进去（By关联的AdvancedMediaPlayer接口），因为继承了MediaPlayer类，所以在AudioPlayer类中可以使用继承下来的play方法（这里注意：从关联的AdvancedMediaPlayer接口实现的方法都放在了继承MediaPlayer类的方法里了，所以当在AudioPlayer类中调用play时，其实执行的逻辑是AdvancedMediaPlayer接口实现的方法）从而到达了接口适配。
 * 4. UML类图略
 */
// 步骤一、为媒体播放器和更高级的媒体播放器创建接口
interface MediaPlayer {
    public void play(String audioType, String filename);
}

interface AdvancedMediaPlayer {
    public void playVlc(String fileName);
    public void playMp4(String fileName);
}

// 步骤二、创建实现了 AdvancedMediaPlayer 接口的实体类。
class VlcPlayer implements AdvancedMediaPlayer {
    @Override
    public void playVlc(String fileName) {
        System.out.println("Playing vlc file. Name: "+ fileName);
    }

    @Override
    public void playMp4(String fileName) {
        //什么也不做
    }
}

class Mp4Player implements AdvancedMediaPlayer {

    @Override
    public void playVlc(String fileName) {
        //什么也不做
    }

    @Override
    public void playMp4(String fileName) {
        System.out.println("Playing mp4 file. Name: "+ fileName);
    }
}

// 步骤三、   创建实现了 MediaPlayer 接口的适配器类
class MediaAdapter implements MediaPlayer {

    AdvancedMediaPlayer advancedMusicPlayer;

    public MediaAdapter(String audioType){
        if(audioType.equalsIgnoreCase("vlc") ){
            advancedMusicPlayer = new VlcPlayer();
        } else if (audioType.equalsIgnoreCase("mp4")){
            advancedMusicPlayer = new Mp4Player();
        }
    }

    @Override
    public void play(String audioType, String fileName) {
        if(audioType.equalsIgnoreCase("vlc")){
            advancedMusicPlayer.playVlc(fileName);
        }else if(audioType.equalsIgnoreCase("mp4")){
            advancedMusicPlayer.playMp4(fileName);
        }
    }
}

//   步骤四、 创建实现了 MediaPlayer 接口的实体类。
class AudioPlayer implements MediaPlayer {
    MediaAdapter mediaAdapter;

    @Override
    public void play(String audioType, String fileName) {

        //播放 mp3 音乐文件的内置支持
        if(audioType.equalsIgnoreCase("mp3")){
            System.out.println("Playing mp3 file. Name: "+ fileName);
        }
        //mediaAdapter 提供了播放其他文件格式的支持
        else if(audioType.equalsIgnoreCase("vlc")
                || audioType.equalsIgnoreCase("mp4")){
            mediaAdapter = new MediaAdapter(audioType);
            mediaAdapter.play(audioType, fileName);
        }
        else{
            System.out.println("Invalid media. "+
                    audioType + " format not supported");
        }
    }
}


public class AdapterPatternDemo {
    public static void main(String[] args) {
        AudioPlayer audioPlayer = new AudioPlayer();

        audioPlayer.play("mp3", "beyond the horizon.mp3");
        audioPlayer.play("mp4", "alone.mp4");
        audioPlayer.play("vlc", "far far away.vlc");
        audioPlayer.play("avi", "mind me.avi");
    }
}


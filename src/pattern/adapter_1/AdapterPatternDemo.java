package pattern.adapter_1;

/**
 * @Date 2019/11/30 8:59
 * @ ������ģʽ - �ṹ��ģʽ
 * �ؼ����룺�������̳л��������еĶ���ʵ����Ҫ��Ŀ��Ľӿ�
 * ��ͼ����һ����Ľӿ�ת���ɿͻ���ϣ������һ���ӿڡ�������ģʽʹ��ԭ���ɽӿڲ����ݶ�����һ��������Щ�����һ����
 * ע�������������������ϸ���ʱ��ӵģ����ǽ�����ڷ��۵���Ŀ������
 * �ο���ַ ��https://www.runoob.com/design-pattern/adapter-pattern.html
 *
 * ���������
 * 1. MediaPlayer��AudioPlayer��ԭ�����ڵ��࣬���������ģʽʱ������Ҫ��MediaPlayer�࣬������Ҫ�Ķ���AudioPlayer�ࡣ
 * 2. AdvancedMediaPlayer�ӿڼ���ʵ����VlcPlayer��Mp4Player���Լ���������MediaAdapter�Ǻ��������ģ�������������̳���MediaPlayer�࣬
 * ������AdvancedMediaPlayer�ӿڡ�
 * 3. ���������е��߼������ǰ���Ҫ���䣨�����������ӿڵ����ݷ����ȥ��By������AdvancedMediaPlayer�ӿڣ�����Ϊ�̳���MediaPlayer�࣬������AudioPlayer���п���ʹ�ü̳�������play����������ע�⣺�ӹ�����AdvancedMediaPlayer�ӿ�ʵ�ֵķ����������˼̳�MediaPlayer��ķ������ˣ����Ե���AudioPlayer���е���playʱ����ʵִ�е��߼���AdvancedMediaPlayer�ӿ�ʵ�ֵķ������Ӷ������˽ӿ����䡣
 * 4. UML��ͼ��
 */
// ����һ��Ϊý�岥�����͸��߼���ý�岥���������ӿ�
interface MediaPlayer {
    public void play(String audioType, String filename);
}

interface AdvancedMediaPlayer {
    public void playVlc(String fileName);
    public void playMp4(String fileName);
}

// �����������ʵ���� AdvancedMediaPlayer �ӿڵ�ʵ���ࡣ
class VlcPlayer implements AdvancedMediaPlayer {
    @Override
    public void playVlc(String fileName) {
        System.out.println("Playing vlc file. Name: "+ fileName);
    }

    @Override
    public void playMp4(String fileName) {
        //ʲôҲ����
    }
}

class Mp4Player implements AdvancedMediaPlayer {

    @Override
    public void playVlc(String fileName) {
        //ʲôҲ����
    }

    @Override
    public void playMp4(String fileName) {
        System.out.println("Playing mp4 file. Name: "+ fileName);
    }
}

// ��������   ����ʵ���� MediaPlayer �ӿڵ���������
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

//   �����ġ� ����ʵ���� MediaPlayer �ӿڵ�ʵ���ࡣ
class AudioPlayer implements MediaPlayer {
    MediaAdapter mediaAdapter;

    @Override
    public void play(String audioType, String fileName) {

        //���� mp3 �����ļ�������֧��
        if(audioType.equalsIgnoreCase("mp3")){
            System.out.println("Playing mp3 file. Name: "+ fileName);
        }
        //mediaAdapter �ṩ�˲��������ļ���ʽ��֧��
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


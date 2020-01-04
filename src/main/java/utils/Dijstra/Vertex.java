package utils.Dijstra;


import lombok.Setter;

/**
 * @Date 2019/12/30 20:49
 * @ ������Ķ���
 */
@Setter
public class Vertex implements Comparable<Vertex>{

    /**
     * �ڵ�����(A,B,C,D)
     */
    private String name;

    /**
     * ���·������
     */
    private int path;

    /**
     * �ڵ��Ƿ��Ѿ�����(�Ƿ��Ѿ��������)
     */
    private boolean isMarked;

    public Vertex(String name){
        this.name = name;
        this.path = Integer.MAX_VALUE; //��ʼ����Ϊ�����
        this.setMarked(false);
    }



    public Vertex(String name, int path){
        this.name = name;
        this.path = path;
        this.setMarked(false);
    }

    public int compareTo(Vertex o) {
        return o.path > path?-1:1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPath() {
        return path;
    }

    public void setPath(int path) {
        this.path = path;
    }

    public boolean isMarked() {
        return isMarked;
    }
    public void setMarked(boolean b) {
        isMarked = b;
    }
}


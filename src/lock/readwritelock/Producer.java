package lock.readwritelock;

public class Producer extends Thread {
    private int neednum;                //������Ʒ������
    private Godown godown;            //�ֿ�

    Producer(int neednum, Godown godown) {
            this.neednum = neednum;
            this.godown = godown;
    }

    public void run() {
            //����ָ�������Ĳ�Ʒ
            godown.produce(neednum);
    }

}

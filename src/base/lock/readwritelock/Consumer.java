package base.lock.readwritelock;

public class Consumer extends Thread {
    private int neednum;                //������Ʒ������
    private Godown godown;            //�ֿ�

    Consumer(int neednum, Godown godown) {
            this.neednum = neednum;
            this.godown = godown;
    }

    public void run() {
            //����ָ�������Ĳ�Ʒ
            godown.consume(neednum);
    }
}
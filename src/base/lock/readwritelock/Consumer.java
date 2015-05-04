package base.lock.readwritelock;

public class Consumer extends Thread {
    private int neednum;                //生产产品的数量
    private Godown godown;            //仓库

    Consumer(int neednum, Godown godown) {
            this.neednum = neednum;
            this.godown = godown;
    }

    public void run() {
            //消费指定数量的产品
            godown.consume(neednum);
    }
}
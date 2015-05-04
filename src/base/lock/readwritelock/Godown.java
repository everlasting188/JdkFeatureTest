package base.lock.readwritelock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;

public class Godown {
    public static final int max_size = 100; // �������
    public Lock lock = new ReentrantLock();
    Condition full = lock.newCondition();
    Condition empty = lock.newCondition();

    public Object o = new Object();
    public int curnum; // ��ǰ�����

    Godown() {
    }

    Godown(int curnum) {
        this.curnum = curnum;

    }

    /**
     * ����ָ�������Ĳ�Ʒ
     * 
     * @param neednum
     */
    public void produce(int neednum) {
        lock.lock();
        try {
            // �����Ƿ���Ҫ����
            while (neednum + curnum > max_size) {
                System.out.println("Ҫ�����Ĳ�Ʒ����" + neednum + "����ʣ������"
                        + (max_size - curnum) + "����ʱ����ִ����������!");
                try {
                    // ��ǰ�������̵߳ȴ�
                    full.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // �����������������������������򵥵ĸ��ĵ�ǰ�����
            curnum += neednum;
            System.out.println("�Ѿ�������" + neednum + "����Ʒ���ֲִ���Ϊ" + curnum);
            // �����ڴ˶���������ϵȴ��������߳�
            empty.signalAll();
        } finally {
            lock.unlock();
        }
    }
    /**
     * ����ָ�������Ĳ�Ʒ
     * 
     * @param neednum
     */
    public synchronized void consume(int neednum) {
        lock.lock();
        try {
            // �����Ƿ������
            while (curnum < neednum) {
                try {
                    // ��ǰ�������̵߳ȴ�
                    empty.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // ����������������������ѣ�����򵥵ĸ��ĵ�ǰ�����
            curnum -= neednum;
            System.out.println("�Ѿ�������" + neednum + "����Ʒ���ֲִ���Ϊ" + curnum);
            // �����ڴ˶���������ϵȴ��������߳�
            full.signalAll();
        } finally {
            lock.unlock();
        }
    }
}

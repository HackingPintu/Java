package threads;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//Alternative Locking Technique other than synchronized keyword.
/*
Limitations of synchronized
1-No control over lock (if it tries to get the lock and can't acquire it then
it will move to the blocked queue and will be in the BLOCKED state.)
2-No timeout we can't stop the thread for a limited time.
3-No fairness (starvation)
one thread may be in the blocked queue for a long time, because another
threads get the resource.


Types of the locks
1-ReentrantLock-> same thread can acquire thread multiple times.
2-ReadWriteLock
3-StampedLock
4-Semaphore
*/
public class ThreadSeven {

    public static void main(String[] args) {
        Resource resource=new Resource();

        Thread t1=new Thread(resource::f1,"Worker-1");
        Thread t2=new Thread(resource::f1,"Worker-2");
        Thread t3=new Thread(resource::f1,"Worker-3");

        t1.start();
        t2.start();
        t3.start();

    }

}

class Resource {

    Lock lock = new ReentrantLock();

    void f1() {
        lock.lock();


        try {
            System.out.println(Thread.currentThread().getName() + " entered...");
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + " exited...");
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " interrupted...");
        } finally {
            lock.unlock();
        }


    }
}

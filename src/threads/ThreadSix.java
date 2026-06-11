package threads;


//Inter-Thread communication.
public class ThreadSix {

    public static void main(String[] args) {
        //Producer-Consumer Problem.

        Box box = new Box();

        Thread t1 = new Thread(() -> {
            for (int i = 1; i <= 20; i++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
                box.producer(i);
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 1; i <= 20; i++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
                box.consumer();
            }
        });

        t1.start();
        t2.start();
    }
}

/*
Scenario 1-Consumer tries to consume null value
Scenario 2-Producer produces a value without consumer not consuming it.

wait method
1-pause
2-release monitor lock
3-goes to the WAITING state (in the WAITING QUEUE)
4-stays there until another thread wakes it up.(notify or notifyAll)

Busy Waiting.

notify method
1-picks one random thread from the waiting queue.
2-then that thread moves to the blocked state.
3-checks for the lock and if acquires then move to RUNNING state.

notifyAll method
1-picks all the threads from the waiting queue.
2-then all of them try to acquire the lock.
3-only one will get the lock and the remaining goes to the BLOCKED queue.


Spurious Wakeup
The thread automatically move from the WAITING state to BLOCKED state.
(waiting queue->blocked queue).
*/
class Box {

    volatile Integer item;
    volatile Boolean flag = false;

    synchronized void producer(int value) {

        while (flag == true) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        item = value;
        flag = true;
        System.out.println("Producer produces " + item);
        notify();
    }

    synchronized void consumer() {
        while (flag == false) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Consumer consumes " + item);
        item = null;
        flag = false;
        notify();
    }

}

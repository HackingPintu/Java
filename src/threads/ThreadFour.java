package threads;

// TO demonstrate the problems in multi-threading
public class ThreadFour {

    static volatile boolean flag=false;

    public static void main(String[] args) throws InterruptedException {

        /* Non-Atomic variable and non-atomic operations. i.e. RACE conditions.

            Counter c1 = new Counter();

            Thread t1 = new Thread(() -> {
                for (int i = 0; i <= 10000; i++) {
                    c1.increment();
                }
            });
            Thread t2 = new Thread(() -> {
                for (int i = 0; i <= 10000; i++) {
                    c1.increment();
                }
            });

            t1.start();
            t2.start();

            t1.join();
            t2.join();

            //Count doesn't become exactly to the iteration count.
            System.out.println(c1.count);

         */

        //Visibility Problems in threads.

        Thread t3 = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            flag=true;
        });

        Thread t4 = new Thread(() -> {
            while (!flag) {
                // System.out.println(Thread.currentThread().getName()+" is running...");
                // Do nothing
            }
        },"Worker-Thread");

        t3.start();
        t4.start();

    }

}

class Counter {
    //SHARED RESOURCE
    public int count = 0;

    //CRITICAL SECTION
    void increment() {
        //NON-ATOMIC OPERATION check and then act.
        count++;
    }
}
package threads;


//To demonstrate all the ways to create a reference variable of the Thread class.
public class ThreadOne {

    public static void main(String[] args) {
        Thread t1 = new MyThread();
        CustomThread ct = new CustomThread();
        Thread t2 = new Thread(ct);


        //Thread implementation using anonymous inner class.
        Thread t3= new Thread(){
            @Override
            public void run(){
                System.out.println(Thread.currentThread().getName()+" is running using anonymous inner class");
            }
        };

        //Thread implementation using lambda expression
        Thread t4 = new Thread(()->System.out.println(Thread.currentThread().getName()+" is running using lambda expression"));

        t1.start();
        t2.start();
        t3.start();
        t4.start();

    }
}

// Thread implementation extends the thread class.
class MyThread extends Thread {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " is running extending thread class");
    }
}

// Thread implementation implementing runnable interface.
class CustomThread implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " is running implementing runnable interface");
    }
}

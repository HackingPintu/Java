package threads;

public class ThreadFive {

    public static void main(String[] args) throws InterruptedException {

        CustomCounter c1= new CustomCounter();

        Thread t1 = new Thread(() ->{
            for(int i=0;i<10000;i++) {
                c1.increment();
            }
        });
        Thread t2 = new Thread(() ->{
            for(int i=0;i<10000;i++) {
                c1.increment();
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(c1.counter);

        CustomCounter c2= new CustomCounter();
        Thread t3 = new Thread(c2::m1);

        CustomCounter c3= new CustomCounter();
        Thread t4 = new Thread(c3::m2);

        // Parallel execution as the lock are acquired on different threads.
        t3.start();
        t4.start();

        CustomCounter c4= new CustomCounter();
        Thread t5 = new Thread(c4::m2,"Worker-1");
        Thread t6= new Thread(c4::m3,"Worker-2");

        // Parallel execution one using object-level lock and another using class-level lock.
        t5.start();
        t6.start();

    }


}

class CustomCounter{

    int counter = 0;

    /* Synchronized Method
     * It requires an object to acquire the lock by default it takes the object which calls it.
       public synchronized void increment(){
            counter++;
        }

     */

    public void increment(){
        // This takes the lock of the object calling this method.
        synchronized (this){
            counter++;
        }
    }

    public void m1(){
        synchronized (this){
            try{
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName()+" entered...");
            }catch (InterruptedException e){
                System.out.println(Thread.currentThread().getName()+" interrupted...");
            }
            System.out.println(Thread.currentThread().getName()+" exited...");
        }
    }
    public void m2(){
        synchronized (this){
            try{
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName()+" entered...");
            }catch (InterruptedException e){
                System.out.println(Thread.currentThread().getName()+" interrupted...");
            }
            System.out.println(Thread.currentThread().getName()+" exited...");
        }
    }

    public void m3(){
        synchronized (CustomCounter.class){
            try{
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName()+" entered method synchronized using class level lock");
            }catch (InterruptedException e){
                System.out.println(Thread.currentThread().getName()+" interrupted...");
            }
        }
    }

}


package threads;

//To demonstrate the important methods of the threads.
public class ThreadThree {

    public static void main(String[] args) throws InterruptedException {
        Thread mainThread = Thread.currentThread();
        mainThread.setName("mainThread");

        /*
        * join method demo normal vs overloaded method.
            Thread t1= new Thread(()->{
                System.out.println(Thread.currentThread().getName()+" started...");
                System.out.println(mainThread.getName()+" is at "+mainThread.getState()+" state.");
                //WAITING for t1 to join. TIMED WAITING if the join() is overloaded with the time interval.
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName()+" interrupted...");
                }
                System.out.println(mainThread.getName()+" is at "+mainThread.getState()+" state.");//TERMINATED.
            });
            t1.start();
            t1.join(1000);// let t1 thread completes it's execution and wait for it. Puts the main thread in waiting state.
            System.out.println(mainThread.getName()+" is at "+mainThread.getState()+" state.");//RUNNABLE-> main thread

         */

        /*
         * Yield method is at the mercy of the OS. It only acts as a suggestion to the OS.

            Thread t1 = new Thread(()->{
                for(int i=0;i<10;i++){
                    System.out.println(Thread.currentThread().getName()+" : "+i);
                    Thread.yield();
                }
            });
            Thread t2 = new Thread(()->{
                for(int i=0;i<10;i++){
                    System.out.println(Thread.currentThread().getName()+" : "+i);
                }
            });
            t1.start();
            t2.start();

         */
        /* interrupt()-> interrupts the thread's execution. i.e. set the interrupt flag to true.
         * isInterrupted()-> checks whether a thread's interrupt flag is true/false.
         * interrupted()-> checks and sets it to be false.(not recommended)

            Thread t1 = new Thread(()->{
                while(!Thread.currentThread().isInterrupted()){
                    System.out.println(Thread.currentThread().getName()+" is running");
                }
            });
            t1.start();
            Thread.sleep(1000);
            t1.interrupt();
        */

        /* setPriority is also at the mercy of the OS.
         * JVM can't set it, it depends upon the OS, but it acts like a suggestion.
            Thread t1 = new Thread(()->{
                for(int i=0;i<10;i++){
                    System.out.println(Thread.currentThread().getName()+" : "+i);
                }
            });

            Thread t2 = new Thread(()->{
                for(int i=0;i<10;i++){
                    System.out.println(Thread.currentThread().getName()+" : "+i);
                }
            });

            t1.start();
            t2.start();
            t1.setPriority(10);

         */

        /*
         isAlive method


            System.out.println(mainThread.getName()+ " is alive "+mainThread.isAlive());
            Thread t1 = new Thread(
                    () -> {
                        try {
                            Thread.sleep(2000);
                            System.out.println(mainThread.getName()+ " is alive "+mainThread.isAlive());
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
            );

            t1.start();
            System.out.println(t1.getName()+ " is alive "+t1.isAlive());


         */

        /*
        Daemon thread only runs as a background that is dependent and runs only the other threads are working.
         */
        Thread t1= new Thread(()->{
            while(true){
                System.out.println(Thread.currentThread().getName()+" is running");
            }
        });

        t1.setDaemon(true);

        t1.start();

        Thread.sleep(1000);


    }
}

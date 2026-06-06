package threads;


//To demonstrate the states of threads
public class ThreadTwo {

    public static void main(String[] args) {

        Thread mainThread = Thread.currentThread();
        mainThread.setName("mainThread");
        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " is running");
            System.out.println(mainThread.getName() + " is at "+mainThread.getState()+" state."); //TIMED_WAITING-> main thread
        });

        System.out.println(mainThread.getName() + " is at " + mainThread.getState()+" state.");//RUNNABLE-> main thread
        System.out.println(t1.getName() + " is at " + t1.getState()+" state.");//NEW-> t1 thread
        t1.start();
        System.out.println(t1.getName() + " is at " + t1.getState()+" state.");//RUNNABLE-> t1 thread


        try{
            Thread.sleep(1500);
        }catch (InterruptedException e){
            System.out.println(e.getMessage());
        }
        System.out.println(t1.getName() + " is at " + t1.getState()+" state."); //TERMINATED -> t1 thread
    }


}

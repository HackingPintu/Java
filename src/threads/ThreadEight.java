package threads;


import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

//Lock-Free Concurrency
public class ThreadEight {
    public static void main(String[] args) {

        AtomicCounter atomicCounter = new AtomicCounter();
        Thread t1 = new Thread(() -> {
            for (int i = 1; i <= 10000; i++) {
                atomicCounter.increment();
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 1; i <= 10000; i++) {
                atomicCounter.increment();
            }
        });

        t1.start();
        t2.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Count: " + atomicCounter.count);

        SeatBooking sb = new SeatBooking();

        Thread t3 = new Thread(() -> {
            boolean value = sb.bookSeat("John");
            if (value) {
                System.out.println("Seat booked for John!");
            }
        });
        Thread t4 = new Thread(() -> {
            boolean value = sb.bookSeat("Alice");
            if (value) {
                System.out.println("Seat booked for Alice!");
            }
        });

        t4.start();
        t3.start();

        System.out.println("Simple Seat booked by : " + sb.simpleSeat);

        Thread t5 = new Thread(() -> {
            boolean value = sb.bookAtomicSeat("Sabre");
            if (value) {
                System.out.println("Atomic Seat booked for Sabre!");
            }
        });
        Thread t6 = new Thread(() -> {
            if (sb.bookAtomicSeat("Delta")) {
                System.out.println("Atomic Seat booked for Delta!");
            }

        });

        t5.start();
        t6.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Atomic Seat booked by : " + sb.seat.get());
    }
}

class AtomicCounter {

    //Atomic Variable in which only the atomic operations can occur.
    AtomicInteger count = new AtomicInteger(0);

    public void increment() {
        count.getAndIncrement();
    }


}

class SeatBooking {

    AtomicReference<String> seat = new AtomicReference<>("EMPTY");

    String simpleSeat = "EMPTY";

    boolean bookAtomicSeat(String name) {

        String currentValue = seat.get();

        if (!currentValue.equals("EMPTY")) {
            return false;
        }

        return seat.compareAndSet("EMPTY", name);
    }

    boolean bookSeat(String name) {

        if (simpleSeat.equals("EMPTY")) {
            simpleSeat = name;
            return true;
        }

        return false;
    }

}

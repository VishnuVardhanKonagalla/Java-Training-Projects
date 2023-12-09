package MultiThreading;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class NPrimeNumbers_SingleThreaded {

    public static void main(String[] args) throws InterruptedException {

        LocalDateTime startTime = LocalDateTime.now();

        DemoThread demoThread = new DemoThread();
        demoThread.start();
        demoThread.join();

        LocalDateTime endTime = LocalDateTime.now();
        Duration duration = Duration.between(startTime, endTime);
        System.out.println("Time Taken is : " + duration.getSeconds());
    }

    static class DemoThread extends Thread {
        private int input;

        @Override
        public void run() {

            List<Integer> numbers = Arrays.asList(120000, 60000, 90000, 80000, 700000, 50000, 200000, 3000000,700000);
//            List<Integer> numbers = Arrays.asList(20);
            System.out.println(Thread.currentThread());
            IntStream.range(0, numbers.size()).forEach(input ->
            {
                List<Integer> primeNumbers = nPrimeNumbers(numbers.get(input));
//                System.out.println(numbers.get(input) + " prime numbers are : " + primeNumbers);
            });
            System.out.println("Completed execution..");
        }
    }

    static List<Integer> nPrimeNumbers(int input) {
        List<Integer> result = new ArrayList<>();
        int prime = 1;
        int count = 1;
        while (count <= input) {
            if (PrimeNumberChecker.isPrime(prime)) {
                result.add(prime);
                count++;
            }
            prime++;
        }
        return result;
    }

}

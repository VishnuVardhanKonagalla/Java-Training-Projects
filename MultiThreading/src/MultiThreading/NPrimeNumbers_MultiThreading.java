package MultiThreading;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class NPrimeNumbers_MultiThreading {

    public static void main(String[] args) {

        LocalDateTime startTime = LocalDateTime.now();
        List<Integer> numbers = Arrays.asList(120000, 60000, 90000, 80000, 700000, 50000, 200000, 3000000,700000);
//            List<Integer> numbers = Arrays.asList(20);

        DemoThread[] threads = new DemoThread[numbers.size()];

        IntStream.range(0, numbers.size()).forEach(input -> {
            threads[input] = new DemoThread(numbers.get(input));
            threads[input].start();
        });

        IntStream.range(0, numbers.size()).forEach(input -> {
            try {
                System.out.println(threads[input]+" joined..");
                threads[input].join();
                System.out.println(Thread.currentThread()+" : "+numbers.get(input)+" prime numbers checked");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        LocalDateTime endTime = LocalDateTime.now();
        Duration duration = Duration.between(startTime, endTime);
        System.out.println("Time Taken is : " + duration.getSeconds());
    }

    static class DemoThread extends Thread {
        private int input;
        List<Integer> primeNumbers;

        DemoThread() {

        }

        DemoThread(int input) {
            this.input = input;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread());
            primeNumbers = nPrimeNumbers(input);
//                System.out.println(numbers.get(input) + " prime numbers are : " + primeNumbers);
//            System.out.println("Completed execution for " + input);
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

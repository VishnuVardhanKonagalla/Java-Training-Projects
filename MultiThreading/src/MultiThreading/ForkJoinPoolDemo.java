package MultiThreading;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

public class ForkJoinPoolDemo {

    public static void main(String[] args) {

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        List<Integer> numbers = Arrays.asList(120000, 60000, 90000, 80000, 700000, 50000, 200000, 3000000, 700000);
//        List<Integer> numbers = Arrays.asList(10, 20);

        List<Future<List<Integer>>> futures = new ArrayList<>();

        /**
         * Submitting threads to find prime numbers
         */
        numbers.stream().forEach(input ->
        {
            futures.add(forkJoinPool.submit(() -> NPrimeNumbers.nPrimeNumbers(input)));
            System.out.println(Thread.currentThread() + " values are checked..");
        });

        /**
         * Blocking call to wait until threads
         */
        futures.stream()
                .forEach(input -> {
                    try {
                        System.out.println(Thread.currentThread() + " daemon status is : " + Thread.currentThread().isDaemon());
                        List<Integer> result= input.get();
                        System.out.println("Result status is : "+result.size());
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    } catch (ExecutionException e) {
                        throw new RuntimeException(e);
                    }
                });

        /**
         * Shutting down process manually
         */
        forkJoinPool.shutdown();
    }
}

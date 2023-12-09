package MultiThreading;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorServiceDemo {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() - 1);

//        List<Integer> numbers = Arrays.asList(120000, 60000, 90000, 80000, 700000, 50000, 200000, 3000000, 700000);
        List<Integer> numbers = Arrays.asList(10, 20);

        List<Future<List<Integer>>> futures = new ArrayList<>();

        /**
         * Submitting of Executor Service to process the threads
         */
        submitTaskUsingExecutorService(executorService, numbers, futures);

        /**
         * Blocking call for executor service to not allow main thread to execute other statements without completion of pool threads
         * Here get() is blocking call and will not execute next statement without computing its thread result
         */
        blockingCallUsingGet(futures);

        /**
         * Another way of blocking call for execution of pool threads
         * Disadvantage is we don't know the exact wait time to provide here
         */
//        executorService.awaitTermination(10, TimeUnit.SECONDS);

        /**
         * for shutting down the executor service pool threads
         * Without this statement, threads will not be destroyed and program will never terminate
         */
        executorService.shutdown();
        System.out.println(Thread.currentThread());
    }

    private static void blockingCallUsingGet(List<Future<List<Integer>>> futures) {
        futures.stream().forEach(future -> {
            try {
                List<Integer> result = future.get();
                System.out.println(Thread.currentThread() + " prime numbers checked for - " + result.size());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private static void submitTaskUsingExecutorService(java.util.concurrent.ExecutorService executorService, List<Integer> numbers, List<Future<List<Integer>>> futures) {
        numbers.stream().forEach(input -> {
            futures.add(executorService.submit(() -> NPrimeNumbers.nPrimeNumbers(input)));
        });
    }
}

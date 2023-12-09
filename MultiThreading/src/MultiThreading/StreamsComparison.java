package MultiThreading;

import java.util.Arrays;
import java.util.List;

/**
 * Types of Streams -> Sequential and Parallel
 * Streams are lazy and requires terminal operation (Ex: findFirst, reduce, collect, limit etc.,)
 * .parallel() or .sequential() can be used for streams before terminal operation which decides the type of stream
 * <p>
 * 1. Sequential streams are non daemon threads and perform in a sequential manner
 * As per below example, Flow starts from filter --> map --> reduce and same will repeat
 * <p>
 * 2. Parallel streams are daemon threads and performs in parallel manner
 * As per below example, First all filter methods will be executed and then goes to Map and then to reduce operation
 */


/**
 * Based on the computations, Speed of streams varies
 * If it is easy as finding first odd number, then sequential would outperform parallel in terms of time and speed
 * If it is heavy computed expensive as finding n prime numbers, then parallel would outperform sequential in terms of time and speed
 */

public class StreamsComparison {
    public static void main(String[] args) {
//        List<Integer> numbers = Arrays.asList(120000, 60000, 90000, 80000, 700000, 50000, 200000, 3000000, 700000);
        List<Integer> numbers = Arrays.asList(10, 20);


        numbers.stream()
                .filter(input ->
                {
                    System.out.println("Filter method of " + input + " and daemon status is " + Thread.currentThread().isDaemon());
                    return input % 2 == 0;
                })
                .map(input ->
                {
                    System.out.println("Map method of " + input + " and daemon status is " + Thread.currentThread().isDaemon());
                    return input * input;
                })
                .reduce(0, (x, y) -> x + y);


        System.out.println("Parallel :");
        numbers.parallelStream()
                .filter(input ->
                {
                    System.out.println(Thread.currentThread() + "Filter method of " + input + " and daemon status is " + Thread.currentThread().isDaemon());
                    return input % 2 == 0;
                })
                .map(input ->
                {
                    System.out.println(Thread.currentThread() + "Map method of " + input + " and daemon status is " + Thread.currentThread().isDaemon());
                    return input * input;
                })
                .reduce(0, (x, y) -> x + y);

        /**
         * In below example, We have used first .sequential() to make it sequential stream
         * Later we used .parallel() to make it parallel stream
         *
         * But streams always decide based on the terminal operation
         * Here it will consider as .sequential() as it is declared before terminal operation..
         * .sequential()
         * .reduce(0, (x, y) -> x + y);
         */
        numbers.parallelStream()
                .sequential()
                .filter(input ->
                {
                    System.out.println(Thread.currentThread() + "Filter method of " + input + " and daemon status is " + Thread.currentThread().isDaemon());
                    return input % 2 == 0;
                })
                .parallel()
                .map(input ->
                {
                    System.out.println(Thread.currentThread() + "Map method of " + input + " and daemon status is " + Thread.currentThread().isDaemon());
                    return input * input;
                })
                .sequential()
                .reduce(0, (x, y) -> x + y);
    }
}

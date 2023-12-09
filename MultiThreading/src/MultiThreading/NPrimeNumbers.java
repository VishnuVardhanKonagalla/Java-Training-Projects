package MultiThreading;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class NPrimeNumbers {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(120000, 300000, 400000, 60000, 90000, 80000, 700000, 50000, 200000, 300000, 4000000);
//        List<Integer> numbers = Arrays.asList(20);

        LocalDateTime startTime = LocalDateTime.now();

        IntStream.range(0, numbers.size()).forEach(input ->
        {
            System.out.println(Thread.currentThread());
            List<Integer> primeNumbers = nPrimeNumbers(numbers.get(input));
            System.out.println(numbers.get(input)+" prime numbers are : "+ primeNumbers);
        });

        LocalDateTime endTime = LocalDateTime.now();
        Duration duration = Duration.between(startTime, endTime);
        System.out.println("Time Taken is : " + duration.getSeconds());
    }

    static List<Integer> nPrimeNumbers(int input) {
        List<Integer> result = new ArrayList<>();
        int prime = 1;
        int count = 1;
        while (count <= input) {
            if (PrimeNumberChecker.isPrime(prime)) {
//                System.out.print(prime + " ");
                result.add(prime);
                count++;
            }
            prime++;
        }
//        System.out.println(result.size());
        return result;
    }
}

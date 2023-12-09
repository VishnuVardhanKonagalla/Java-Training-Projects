package MultiThreading;

//  Here we are taking input integer and checking whether it is prime or not

public class PrimeNumberChecker {

    public static void main(String[] args) {
        for (int i = 1; i <= 50; i++)
            if (isPrime(i))
                System.out.print(i + " ");

        System.out.println();
    }

    static boolean isPrime(int input) {
        if (input == 1)
            return false;
        else if (input == 2)
            return true;
        else if (input % 2 == 0)
            return false;

        /**
         *
         * Here we are iterating through square root of number to reduce the load
         * For example, Lets take 25 as a number and 5 is the square root..
         * When i becomes 5, It will evaluate modulus expression and returns false as it results 0
         * Hence for no-prime numbers, Modulus operation will be 0 and returns false
         */
        for (int i = 3; i <= Math.sqrt(input); i += 2) {
            if (input % i == 0)
                return false;
        }
        return true;

//        int count = 0;
//        for (int i = 1; i <= input; i++) {
//            if (input % i == 0)
//                count++;
//        }
//        if (count == 2)
//            return true;
//        return false;

    }
}
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class Main {

    
    static double[] targets = new double [] {0.5, 0.8, 0.3};
    static final double PRECISION = 0.0000001;
    static double result = 0;

    public static void main(String[] args) {

        //call generateNumber here...
        // Runnable runnable = () -> result = generateNumber();
        //Callable<Double> callable = () -> generateNumber(); //if your task returns something use callable

        ExecutorService executor = Executors.newFixedThreadPool(3);
        Future<Double> future = executor.submit(() -> generateNumber(0));
        Future<Double> future2 = executor.submit(() -> generateNumber(1));
        Future<Double> future3 = executor.submit(() -> generateNumber(2));

        // //threads aren't designed to return results can only be created out of a runnable
        // //future task implements Runnable interface
        // FutureTask<Double> future = new FutureTask<>(() -> generateNumber(0));
        // //so future task can behave as a runnable
        // Thread thread2 = new Thread(future);

        // FutureTask<Double> future2 = new FutureTask<>(() -> generateNumber(1));
        // Thread thread3 = new Thread(future);

        // FutureTask<Double> future3 = new FutureTask<>(() -> generateNumber(2));
        // Thread thread4 = new Thread(future);


        // thread2.start();
        // thread3.start();
        // thread4.start();
       
        

        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter a name to generate a number: ");
        scan.nextLine();
        scan.close();
        // double precision = difference(result);
        try {
            future.get();
            future2.get();
            future3.get();
            //tasks don't shutdown 
            executor.shutdown();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("finished running background operations");
        // System.out.println("The value was generated to a precision of : " + precision);
    }

    /**
     * Function name: generateNumber
     * @return double
     * 
     * Inside the function:
     *   1. Generates a number close to the TARGET to a precision of PRECISION.
     * 
     */
    public static double generateNumber(int index){
        double number = Math.random();
        double difference = difference(number, index);
        while (difference > PRECISION) {
            number = Math.random();
            difference = difference(number, index);
        }
        return number;

    }

    public static double difference(double number, int index) {
        return Math.abs(targets[index] - number);
    }

}

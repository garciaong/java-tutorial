package com.tutorial;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MultiThreads {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        executor.execute(new Task());
        executor.execute(new Task());
        executor.execute(new Task());
        
        Future<String> myTask = executor.submit(new MyCallable("Task1"));
        Future<String> myTask2 = executor.submit(new MyCallable("Task2"));
        try {
            System.out.println(myTask.get());
            System.out.println(myTask2.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        executor.shutdown();
    }
}

class Task implements Runnable {

    @Override
    public void run() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Running thread "+Thread.currentThread().getName());    
    }

}

class MyCallable implements Callable<String>{
    private String task;

    public MyCallable(String task) {
        this.task = task;
    }

    @Override
    public String call() throws Exception {
        System.out.println(task + " thread is " + Thread.currentThread().getName());
        return "Executing task " + task;
    }

}

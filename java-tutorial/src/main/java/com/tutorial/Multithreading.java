package com.tutorial;

public class Multithreading {
    public static void main(String[] args) {
        for(int i=0; i<3; i++) {
            Processor process = new Processor(i+1);
            Thread thread = new Thread(process);
            thread.setName("Process"+(i+1));
            thread.start();
        }
    }
}

class Processor implements Runnable{
    int processNumber;
    
    public Processor(int number) {
        this.processNumber = number;
    }

    @Override
    public void run() {
        if(processNumber == 3){
            throw new RuntimeException(String.format("Process number %d is not permitted", processNumber));
        }
        for(int i=1; i<=5; i++){
            System.out.println(String.format("Process number %d of work %d", 
                processNumber, i));
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.err.println("Thread sleep error");
        }
    }
}

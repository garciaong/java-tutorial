package com.tutorial;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureSample {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletableFuture<Process[]> future = CompletableFuture
            .supplyAsync(() -> {
                Process[] processes = new Process[2];
                try {
                    Thread.sleep(1000); // Simulate a long-running operation
                    for(int i=1; i<=2; i++) {
                        Process process = new Process();
                        process.processId = "P00"+i;
                        processes[i-1] = process;
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                return processes;
            });
        // returns a new CompletableFuture containing the transformed value.
        CompletableFuture<String[]> processIdsFuture = future.thenApply(processes -> {
            String [] processIds = new String[processes.length];
            for(int i=0; i<processes.length; i++) {
                processIds[i] = processes[i].processId;
            }
            return processIds;
        });
        System.out.println(String.format("Process Ids are %s", Arrays.toString(processIdsFuture.get())));
        
        CompletableFuture<Process[]> processFuture = future.thenApply(processes -> {
            for(Process process:processes) {
                process.state = "Processing";
            }
            return processes;
        });
        processFuture.thenAccept(processes -> {
            for(Process process:processes) {
                System.out.println(String.format("Process Id %s is in state %s", process.processId, process.state));
            }
        });
        CompletableFuture<Process[]> finalFuture = processFuture.thenApply(processes -> {
            for(Process process:processes) {
                process.state = "Completed";
            }
            return processes;
        });
        finalFuture.thenAccept(processes -> {
            for(Process process:processes) {
                System.out.println(String.format("Process Id %s is in state %s", process.processId, process.state));
            }
        });
        finalFuture.thenRun(() -> {System.out.println("Task(s) completed");});
    }
}

class Process {
    public String processId;
    public String state = "Pending";
}

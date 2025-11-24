package com.tutorial;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class StreamAPI {
    public static void main(String[] args) {
        List<Integer> nums = new ArrayList<>();
         for(int i=0; i<10; i++){
            nums.add(i+1);
         }

        boolean hasNumber8 = nums.stream().anyMatch(num -> num == 8);
        System.out.println("Has number 8 "+hasNumber8);
        boolean allNumber8 = nums.stream().allMatch(num -> num == 8);
        System.out.println("All number 8 "+allNumber8);
        long numberOfItems = nums.stream().count();
        System.out.println("Number of items "+numberOfItems);
        Set<Integer> evenNums = nums.stream()
        .filter(num -> num % 2 == 0).collect(Collectors.toSet());
        System.out.println("Even set contains 5? "+evenNums.contains(5));
        List<Integer> limitedList = nums.stream().limit(3).collect(Collectors.toList());
        System.out.println("Limited list is "+limitedList.toString());
        List<Integer> mappedList = nums.stream().map(n -> n * 10).collect(Collectors.toList());
        System.out.println("Mapped list is "+mappedList.toString());
        List<String> convertedList = nums.stream().limit(3)
        .map(num -> {return "Task"+num;}).collect(Collectors.toList());
        System.out.println("Converted mapped list is "+convertedList.toString());
        

        List<MyProcess> processes = new ArrayList<>();
        processes.add(new MyProcess("P1"));
        processes.add(new MyProcess("P2"));
        processes.add(new MyProcess("P3"));

        System.out.println("Start stream...");
        boolean hasProcessP3 = processes.stream().anyMatch(process -> process.getName().equals("P3"));
        System.out.println("Has process P3? "+hasProcessP3);
        System.out.println("Start parallel stream...");
        hasProcessP3 = processes.parallelStream().anyMatch(process -> process.getName().equals("P3"));
        System.out.println("Has process P3? "+hasProcessP3);
    }
}

class MyProcess {
    private String name;

    MyProcess(String name) {
        this.name = name;
    }

    String getName(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return name;
    }
}

package com.tutorial;

public class CharacterArray {
    public static void main(String[] args) {
        char[] chars = {'a', 'b', 'c'};

        System.out.println(String.format("'a' is %s", chars['a'-'a']));
        System.out.println(String.format("'b' is %s", chars['b'-'a']));
        System.out.println(String.format("'c' is %s", chars['c'-'a']));
        System.out.println('c'-'a');
    }
}

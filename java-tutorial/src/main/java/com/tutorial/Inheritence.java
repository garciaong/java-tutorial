package com.tutorial;

public class Inheritence {
    public static void main(String[] args) {
        Base base = new Base();
        base.inititate();
        base.execute();
        Interface1 base1 = new Base();
        Interface2 base2 = new Base();
        base1.inititate();
        base2.execute();
    }
}

class Base implements Interface1, Interface2 {

    @Override
    public void execute() {
        System.out.println("Executing...");    
    }

    @Override
    public void inititate() {
        System.out.println("Initializing...");
    }

}

interface Interface1 {
    void inititate();
}

interface Interface2 {
    void execute();
}
package com.example.demo.basic;

import java.util.Scanner;

class Box<T> {
    private T item;

    public T getitem() {
        return item;
    }

    public void setitem(T item) {
        this.item = item;
    }
}

public class study {
    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int a = sc.nextInt();
//        int b = sc.nextInt();
//
//        int sum = a + b;
//        System.out.println(sum);
        Box<Integer> integerBox = new Box<Integer>();
        Box<String> stringBox = new Box<String>();
        Box<Double> doubleBox = new Box<Double>();
        integerBox.setitem(123);
        stringBox.setitem("hello, Generic!");
        doubleBox.setitem(45.46);
        System.out.println("String Box: "+stringBox.getitem());
        System.out.println("Integer Box: "+integerBox.getitem());
        System.out.println("Double Box: "+doubleBox.getitem());
    }

}











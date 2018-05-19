package com.apress.bgn.ch4.lambda;

public class OperationDemo {
    public static void main(String... args) {
        Addition addition = new Addition();
        float result = addition.execute(2, 5);
        System.out.println("Result is " + result);


        Operation addition2 = (a, b) -> a + b;
        float result2 = addition2.execute(2, 5);
        System.out.println("Lambda Result is " + result2);
    }
}

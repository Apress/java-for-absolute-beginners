package com.apress.bgn.ch5;

public class ConvertProcess {
    /* other fields and methods */

    private boolean done;

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public static void main(String... args) {
        ConvertProcess cp = new ConvertProcess();

        System.out.println("Default value = " + cp.isDone());
    }
}

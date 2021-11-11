package com.company;

public class Main {

    public static int testa(int n) {
        if (n == 0) {
            return 0;
        }
        return 1+testa(n-1);
    }

    public static int testb(int n, int acc) {
        if (n == 0) {
            return acc;
        }
        return testb(n-1, acc+1);
    }

    public static void main(String[] args) {
        //testa(100000);  // crashes - tail recursion optimisation not possible
        testb(100000, 0);  // crashes - tail recursion optimisation possible; not performed
    }
}
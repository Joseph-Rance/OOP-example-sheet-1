package com.company;

public class Main {

    public static int lowestCommon(long n1, long n2) {
        int ctr = 0;
        while (n1 % 2 != n2 % 2) {

            if (ctr == 70) {  // since >> is used any number > 63 works here
                ctr = -1;
                break;  // could return directly here if no break requirement
            }

            ctr++;
            n1 >>= 1;
            n2 >>= 1;
        }
        return ctr;
    }

    public static void main(String[] args) {
        System.out.println(lowestCommon(0L, -1L));
        System.out.println(lowestCommon(14L, 25L));
        System.out.println(lowestCommon(1L, 2L));
        System.out.println(lowestCommon(20L, 20L));
    }
}

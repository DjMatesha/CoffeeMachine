package com.company;
import java.lang.reflect.Array;
import java.util.*;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) throws Throwable {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        char []a = s.toCharArray();
        for(char t: a ) System.out.print(t+" ");
        System.out.println();
        for (int i=1; i<a.length-1;++i){
            System.out.print(a[i]+" ");
            for (int j=1; j<a.length-1;++j)
                System.out.print("0 ");
            System.out.println(a[a.length-i-1]+" ");
        }
        for(int i=a.length-1;i>=0; --i ) System.out.print(a[i]+" ");
    }
}

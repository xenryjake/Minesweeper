package com.xenry.minesweeper;

import java.util.Scanner;

/**
 * Minesweeper created by Henry Jake on February 01, 2016.
 * Copyright 2016 Henry Jake.
 * All content in this file may not be used without written consent of Henry Jake.
 */
public class U {

    public static void p(Object text){
        System.out.println(text);
    }

    public static void p(){
        p("");
    }

    public static String get(String query){
        System.out.print(query);
        return new Scanner(System.in).nextLine();
    }

}

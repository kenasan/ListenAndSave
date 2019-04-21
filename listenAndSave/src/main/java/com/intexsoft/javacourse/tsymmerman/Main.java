package com.intexsoft.javacourse.tsymmerman;


import java.util.Scanner;

public class Main {
    public static final String EXCHANGE_NAME = "exchange";
    public static void main(String[] argv) throws Exception{

        System.out.print("Set name of your queue : ");
        Scanner scanner = new Scanner(System.in);
        String nameQueue = scanner.nextLine();
        new MessageGenerator(nameQueue);


    }
}



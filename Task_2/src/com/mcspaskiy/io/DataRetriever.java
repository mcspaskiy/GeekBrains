package com.mcspaskiy.io;

import java.util.Scanner;

public class DataRetriever {
    public String readData() {
        Scanner input = new Scanner(System.in);
        System.out.print("Input data:");
        String inputValue = input.nextLine();
        return inputValue;
    }
}

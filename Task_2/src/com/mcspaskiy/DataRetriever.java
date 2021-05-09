package com.mcspaskiy;

import java.util.Scanner;

public class DataRetriever {
    public String readData() {
        Scanner input = new Scanner(System.in);
        System.out.print("Input data:");
        String inputValue = input.nextLine();
        input.close();
        return inputValue;
        //Mock input string
        //return "29/ (3 + 2) * 51 - 5 + 2 * 3 - 4 * (3 - 1)";
    }
}

package com.mcspaskiy;

import java.util.List;

public class DataViewer {
    public void show(List<String> values) {
        for (String element : values) {
            System.out.print(element + " ");
        }
    }

    public void show(Object value){
        System.out.println();
        System.out.println(value);
    }
}

package com.nuite;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RedisTest {

    @Test
    public void contextLoads() throws IOException {
        FileReader fileReader = new FileReader("C:\\Users\\Administrator\\Desktop\\my.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        List<String> myList = new ArrayList<>();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            if (!line.trim().isEmpty()) {
                myList.add(line.trim());
            }
        }
        bufferedReader.close();
        fileReader.close();

        List<String> myNotList = new ArrayList<>();
        fileReader = new FileReader("C:\\Users\\Administrator\\Desktop\\yb.txt");
        bufferedReader = new BufferedReader(fileReader);
        while ((line = bufferedReader.readLine()) != null) {
            if (!myList.contains(line.trim())) {
                myNotList.add(line.trim());
            }
        }
        bufferedReader.close();
        fileReader.close();
        System.out.println(myNotList.size());
        System.out.println(myNotList);
        for (String lin : myNotList) {
            System.out.println(lin);
        }
    }

    @Test
    public void test() {
        List<String> dataList = new ArrayList<>();
        for (int i = 0; i < 23; i++) {
            dataList.add(i + "");
        }

        int size = dataList.size();
        for (int i = 0; i < size; i = i + 10) {
            List<String> subList;
            if (i + 10 < size) {
                subList = dataList.subList(i, i + 10);
            } else {
                subList = dataList.subList(i, size);
            }
            System.out.println(subList);
        }
    }

}

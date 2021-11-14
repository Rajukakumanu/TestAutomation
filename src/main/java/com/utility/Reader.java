package com.utility;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Reader {

    private String readTextFile(String path) throws IOException {
        File file = new File(path);
        FileReader reader = new FileReader(file);
        BufferedReader read = new BufferedReader(reader);
        String line = "";
        String sum = "";
        while ((line = read.readLine()) != null) {
            sum = sum + line;
        }
        return sum;
    }

    private ArrayList<String> getCarOutPutList() throws IOException {
        ArrayList<String> list = new ArrayList<>();
        File file = new File(ConfigReader.carOutput);
        FileReader reader = new FileReader(file);
        BufferedReader read = new BufferedReader(reader);
        String line = "";
        while ((line = read.readLine()) != null) {
            list.add(line);
        }
        return list;
    }

    private Matcher getMatcher(String text) {
        Pattern pr = Pattern.compile("([A-Z]+[0-9]+\\s[A-Z]*)|([A-Z]+[0-9]+[A-Z]*)");
        Matcher mt = pr.matcher(text);
        return mt;
    }

    private ArrayList<String> getList(String text) {
        ArrayList<String> list = new ArrayList<>();
        Matcher mt= getMatcher(text);
        while (mt.find()) {
            list.add(mt.group());
        }

        return list;
    }


    public ArrayList<String> getCarInputDetails() throws IOException {
        String text = readTextFile(ConfigReader.carInput);
        return getList(text);
    }

    public ArrayList<String> getCarOutPutDetails(String registrationNum) throws IOException {
        ArrayList<String> list = getCarOutPutList();
        ArrayList<String> list1 = new ArrayList<>();
        for (String str : list) {
            String[] details = str.split(",");
            for (int i = 0; i < details.length; i++) {
                if (details[0].equals(registrationNum)) {
                    list1.add(details[i]);
                }
            }
        }

        return list1;
    }
}


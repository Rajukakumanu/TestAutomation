package com.utility;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    public static Properties pros;
    public static String getCurrentWorkingDirectory = System.getProperty("user.dir");
    private final static String path = getCurrentWorkingDirectory + "\\src\\test\\resources\\config\\configuration.properties";
    public final static String carInput = getCurrentWorkingDirectory + "\\src\\test\\testData\\car_input.txt";
    public final static String carOutput = getCurrentWorkingDirectory + "\\src\\test\\testData\\car_output.txt";

    public static void ConfigFileReader() throws IOException {

        File file = new File(path);
        FileReader fileReader = new FileReader(file);
        try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            pros = new Properties();
            pros.load(bufferedReader);
        }
    }
}

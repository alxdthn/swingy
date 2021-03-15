package com.nalexand.swingy.utils;

import java.util.Random;

public class Utils {

    private final static Random random = new Random();

    private Utils() {}

    public static void println(String line) {
        System.out.println(line);
    }

    public static int printFormat(String format, Object... args) {
        String formatted = String.format(format, args);
        System.out.print(formatted);
        return formatted.length();
    }

    public static boolean randomByPercent(int percentage) {
        return random.nextInt(100) < percentage;
    }

    public static int randomBetween(int low, int high) {
        return random.nextInt(high-low) + low;
    }
}

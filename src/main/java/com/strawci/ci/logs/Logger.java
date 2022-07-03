package com.strawci.ci.logs;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Logger {
    private static void print(String prefix, String message) {
        String currentThread = Thread.currentThread().getName();
        Date now = new Date();
        String time = new SimpleDateFormat("HH:mm:ss.SSS", Locale.ENGLISH).format(now);
        System.out.println("(" + time + ") [" + currentThread + "] " + prefix + ": " + message);
    }

    public static void info(String message) {
        Logger.print("INFO", message);
    }

    public static void critical(String message) {
        Logger.print("CRIT", message);
    }

    public static void warning(String message) {
        Logger.print("WARN", message);
    }
}

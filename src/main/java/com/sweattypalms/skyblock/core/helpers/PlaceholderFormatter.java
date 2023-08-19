package com.sweattypalms.skyblock.core.helpers;

import java.util.ArrayList;
import java.util.List;

public class PlaceholderFormatter {

    /**
     *  Formats all the placeholders in a string.
     *  ex. "$cHello" -> "§cHello"
     * @param s string to format
     * @return formatted string
     */
    public static String format(String s) {
        return s.replace("$", "§");
    }
    public static List<String> format(List<String> s) {
        s = new ArrayList<>(s); // Create mutable copy
        s.replaceAll(PlaceholderFormatter::format);
        return s;
    }
    public static String capitalize(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
    }

    public static String formatDouble(double v1) {
        return formatDouble(v1, 1);
    }
    public static String formatDouble(double v1, int decimalPlaces) {
         String format = "%." + decimalPlaces + "f";
        return String.format(format, v1).replace(".0", "");
    }
}

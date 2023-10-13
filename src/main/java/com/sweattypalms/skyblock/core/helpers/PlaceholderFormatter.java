package com.sweattypalms.skyblock.core.helpers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class PlaceholderFormatter {
    private static final DecimalFormat decimalFormat = new DecimalFormat("#,###");

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

    /**
     * Formats a double to a string with 1 decimal place.
     * ex) 1.97349873 -> "1.9
     * @param v1 double to format
     * @return formatted string
     */
    public static String formatDouble(double v1) {
        return formatDouble(v1, 1);
    }

    /**
     * Formats a double to a string with a specified number of decimal places.
     * ex) 1.97349873, 3 -> "1.973"
     * @param v1 double to format
     * @param decimalPlaces number of decimal places
     * @return formatted string
     */
    public static String formatDouble(double v1, int decimalPlaces) {
         String format = "%." + decimalPlaces + "f";
        return String.format(format, v1).replace(".0", "");
    }

    public static String formatDecimalCSV(double v1) {
        return decimalFormat.format(v1);
    }

    public static String compactNumber(int var1) {
        String[] units = {"", "K", "M", "B"};
        int unitIndex = 0;

        double value = var1;
        while (value >= 1000 && unitIndex < units.length - 1) {
            value /= 1000;
            unitIndex++;
        }

        BigDecimal numberBigDecimal = new BigDecimal(value);
        numberBigDecimal = numberBigDecimal.setScale(1, RoundingMode.HALF_EVEN);
        String formattedValue = numberBigDecimal.stripTrailingZeros().toPlainString();

        return formattedValue + units[unitIndex];
    }

    /**
     * Formats a time in seconds to a string in the format "mm:ss"
     * ex) xxx -> "02:03"
     * @param time time in seconds
     * @return formatted time string
     */
    public static String formatTime(long time) {
        long minutes = time / 60;
        long seconds = time % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

    public static String toRomanNumeral(int num) {
        if (num <= 0 || num > 3999) {
            throw new IllegalArgumentException("Number out of range for Roman numerals");
        }

        String[] M = {"", "M", "MM", "MMM"};
        String[] C = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String[] X = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] I = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};

        return M[num / 1000] + C[(num % 1000) / 100] + X[(num % 100) / 10] + I[num % 10];
    }

}

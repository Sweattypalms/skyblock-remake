package com.sweattypalms.skyblock.core.helpers;

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
}

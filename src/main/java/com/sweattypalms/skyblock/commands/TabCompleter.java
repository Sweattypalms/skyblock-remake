package com.sweattypalms.skyblock.commands;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface TabCompleter {
    String command();
}

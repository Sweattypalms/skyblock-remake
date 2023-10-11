package com.sweattypalms.skyblock.commands;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Command {
    String name();
    String[] aliases() default {};
    String description() default "";
    String usage() default "";
    boolean op() default false;
    // TODO: add rank system
}

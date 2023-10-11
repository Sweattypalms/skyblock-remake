package com.sweattypalms.skyblock.api;

import lombok.Getter;

@Getter
public class Point {
    int x;
    int z;

    public Point(int x, int z) {
        this.x = x;
        this.z = z;
    }
}
package com.sweattypalms.skyblock.core.mobs.builder.dragons;

import com.sweattypalms.skyblock.core.helpers.MathHelper;
import org.bukkit.util.Vector;

import java.util.List;

public class DragonStage {

    final List<Vector> path;
    final double speed;

    public DragonStage(List<Vector> path, double speed) {
        if(path.size() != 4 && path.size() != 2) throw new IllegalArgumentException("Path must be 2 or 4 points");
        this.path = path;
        this.speed = speed;
    }

    public Vector getPoint(double t) {

        if (path.size() == 2){
            return MathHelper.linearInterpolation(path.get(0), path.get(1), t);
        }

        double u = 1 - t;
        double tt = t * t;
        double uu = u * u;
        double uuu = uu * u;
        double ttt = tt * t;

        Vector p = path.get(0).clone().multiply(uuu);
        p.add(path.get(1).clone().multiply(3 * uu * t));
        p.add(path.get(2).clone().multiply(3 * u * tt));
        p.add(path.get(3).clone().multiply(ttt));

        return p;
    }
}
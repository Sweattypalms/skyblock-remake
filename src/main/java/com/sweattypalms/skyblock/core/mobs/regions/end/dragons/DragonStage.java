package com.sweattypalms.skyblock.core.mobs.regions.end.dragons;

import org.bukkit.util.Vector;

import java.util.List;

class DragonStage {

    final List<Vector> path;
    final double speed;
    final Runnable onEnter;

    public DragonStage(List<Vector> path, double speed, Runnable onEnter) {
        this.path = path;
        this.speed = speed;
        this.onEnter = onEnter;
    }

    public Vector getPointOnBezierCurve(double t) {
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

    public void onEnter(EnderDragon dragon) {
        onEnter.run();
    }
}
package com.sweattypalms.skyblock.core.mobs.builder.dragons;

import com.sweattypalms.skyblock.core.mobs.builder.dragons.DragonStage;
import com.sweattypalms.skyblock.core.mobs.builder.dragons.DragonType;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public interface IEndDragon {
    DragonType getDragonType();
    String getDragonName();
    double getMaxDragonHealth();
    double getDragonDamage();
    default List<DragonStage> getDragonStages(){
        List<DragonStage> stages = new ArrayList<>();
        stages.add(new DragonStage(
                List.of(
                        new Vector(0, 75, 0),      // Start at middle
                        new Vector(-116, 80, 97),
                        new Vector(-57, 90, -62),
                        new Vector(50, 60, -60)
                ),
                0.005
        ));
        stages.add(new DragonStage(
                List.of(
                        new Vector(50, 60, -60),
                        new Vector(63.5, 60, -68.25),
                        new Vector(59.25, 60, 53.25),
                        new Vector(-32.75, 60, 24.75)
                ),
                0.005
        ));
        stages.add(new DragonStage(
                List.of(
                        new Vector(-32.75, 60, 24.75),
                        new Vector(-45, 70, 40),
                        new Vector(10, 85, 50),
                        new Vector(40, 80, 20)
                ),
                0.005
        ));

        stages.add(new DragonStage(
                List.of(
                        new Vector(40, 80, 20),
                        new Vector(30, 75, 0),
                        new Vector(-10, 80, -20),
                        new Vector(-40, 70, -40)
                ),
                0.005
        ));

        stages.add(new DragonStage(
                List.of(
                        new Vector(-40, 70, -40),
                        new Vector(0, 65, -60),
                        new Vector(30, 70, -20),
                        new Vector(10, 80, 30)
                ),
                0.005
        ));

        stages.add(new DragonStage(
                List.of(
                        new Vector(10, 80, 30),
                        new Vector(-20, 90, 40),
                        new Vector(-60, 75, 10),
                        new Vector(0, 75, 0)      // End at the middle
                ),
                0.005
        ));
        return stages;
    }

/*
    TODO: Add drops
 */
}

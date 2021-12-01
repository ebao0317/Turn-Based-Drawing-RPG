package com.example.basic_tbb;

import java.util.Random;

public class AncientOne extends Monster{
    public AncientOne() {
        super("Ina the Ancient One", 300, 17, 70, 130, 0.5, 0.35, 0.3, 30, 60);
    }

    @Override
    public void specialSkill(DungeonCharacter enemy) {
        Random random = new Random();
        double randomDouble = random.nextDouble();
        int damage;
        if(0.3 > randomDouble) {
            damage  = random.nextInt(250 - 100) + 100;
            System.out.println(name + " used TENTACLE CRUSHER");
            enemy.damageLastTaken = damage;
        }
        else {
            System.out.println("TENTACLE CRUSHER has failed to hit...");
        }
    }
}


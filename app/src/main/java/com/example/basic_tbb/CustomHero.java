package com.example.basic_tbb;

import android.net.Uri;

import java.io.Serializable;
import java.util.Random;

public class CustomHero extends Hero implements Serializable {
    public CustomHero()
    {
        super("Custom Character",175, 10, 50, 90, 0.8, 0.4);
    }

    @Override
    public void specialSkill(DungeonCharacter enemy) {
        //TODO maybe allow the user to create their own special ability
        Random random = new Random();
        double randomDouble = random.nextDouble();
        int damage;
        System.out.println(name + "used Special Ability");
        System.out.println("Success!");
        damage  = random.nextInt(100 - 60) + 60;
        enemy.damageLastTaken = damage;
        minDmgRange = 60;
        maxDmgRange = 100;
    }

    @Override
    public void magic(DungeonCharacter enemy) {
        //TODO maybe allow the user to create their own magic
        System.out.println(name + " Magic");
        System.out.println(name + " permanently increased their damage range by 15");
        minDmgRange += 15;
        maxDmgRange += 15;
    }
}

package com.example.basic_tbb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Archer extends Hero implements Serializable {

    public Archer() {
        super("Mumei the Archer",175, 10, 50, 90, 0.8, 0.4);
    }


    @Override
    public String specialSkill(DungeonCharacter enemy) {
        String nameOfAbility = "True Shot";
        Random random = new Random();
        double randomDouble = random.nextDouble();
        int damage;
        damage = random.nextInt(100 - 60) + 60;
        enemy.damageLastTaken = damage;
        minDmgRange = 60;
        maxDmgRange = 100;

        return nameOfAbility;
    }

    @Override
    public List<String> magic(DungeonCharacter character) {
        List<String> magicDescriptions= new ArrayList<>();
        minDmgRange += 15;
        maxDmgRange += 15;

        magicDescriptions.add(" casted Civilization's Blessing");
        magicDescriptions.add(" increased damage range by 15");

        return magicDescriptions;
    }
}


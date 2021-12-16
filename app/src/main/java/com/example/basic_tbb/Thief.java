package com.example.basic_tbb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Thief extends Hero implements Serializable {

    public Thief() {
        super("Pekora the Thief", 150, 10, 40, 80, 0.9, 0.5);
    }

    @Override
    public String specialSkill(DungeonCharacter enemy) {
        String abilityDescription = "Double Strike";
        Random random = new Random();
        double randomDouble = random.nextDouble();
        int damage;
        if(0.7 > randomDouble) {
            damage  = random.nextInt(100 - 50) + 50;
            enemy.damageLastTaken = damage;
            minDmgRange = 50;
            maxDmgRange = 80;
        }
        return abilityDescription;
    }

    @Override
    public List<String> magic(DungeonCharacter character) {
        List<String> magicDescriptions= new ArrayList<>();
        blockChance = 1;

        magicDescriptions.add(" casted Cloak");
        magicDescriptions.add(" will evade everything this turn");
        return magicDescriptions;
    }
}


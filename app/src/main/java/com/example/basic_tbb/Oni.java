package com.example.basic_tbb;

import java.util.Random;

public class Oni extends Monster{
    public Oni() {
        super("Ayame the Oni", 125, 10, 40, 110, 0.8, 0.3, 0.1, 10, 70);
    }

    @Override
    public void specialSkill(DungeonCharacter enemy) {
        System.out.println(name + " used Monstrous Strength");
        System.out.println(name + " healed for 10 HP, permanently raised defense by 1, increased their hit chance by 1%, and permanently increased damage range by 10.");
        HP += 10;
        hitChance += 0.01;
        defense += 1;
        minDmgRange += 10;
        maxDmgRange += 10;
    }
}


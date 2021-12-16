// Demon.java
// This is the class file for the Demon enemy
// CPSC 312-02, Fall 2021
// Project
//
//
// Created by Ethan Bao on 10/16/2021
package com.example.basic_tbb;

import java.util.Random;

public class Demon extends Monster{
    public Demon() {
        super("Towa the Devil", 150, 13, 40, 70, 0.7, 0.25, 0.25, 40, 70);
    }

    @Override
    public void specialSkill(DungeonCharacter enemy) {
        Random random = new Random();
        double randomDouble = random.nextDouble();
        int damage;
        if(0.7 > randomDouble) {
            damage  = random.nextInt(100 - 50) + 50;
            System.out.println(name + " used Life Drain");
            enemy.damageLastTaken = damage;
            HP += damage;
            lastHeal = damage;
            System.out.println(name + " healed herself for " + damage);
        }
        else {
            System.out.println("Life Drain has failed to hit...");
        }
    }
}


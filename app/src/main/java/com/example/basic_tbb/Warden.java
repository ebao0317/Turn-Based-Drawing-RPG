// Warden.java
// This is the class file for the Warden enemy
// CPSC 312-02, Fall 2021
// Project
//
//
// Created by Ethan Bao on 10/14/2021
package com.example.basic_tbb;

import java.util.Random;

public class Warden extends Monster {
    public Warden() {
        super("Kronii the Time Warden", 200, 15, 70, 120, 0.75, 0.3, 0.3, 30, 60);
    }

    @Override
    public void specialSkill(DungeonCharacter enemy) {
        Random random = new Random();
        double randomDouble = random.nextDouble();
        int damage;
        if(0.8 > randomDouble) {
            damage  = random.nextInt(150 - 100) + 100;
            System.out.println(name + " used Time's Judgement");
            enemy.damageLastTaken = damage;
        }
        else {
            System.out.println("Time's Judgement has failed to hit...");
        }
    }
}


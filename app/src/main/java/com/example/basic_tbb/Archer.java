// Archer.java
// This is the class file for the Archer hero class
// CPSC 312-02, Fall 2021
// Project
//
//
// Created by Ethan Bao on 10/16/2021
package com.example.basic_tbb;

import java.io.Serializable;
import java.util.Random;
import java.util.Scanner;

public class Archer extends Hero implements Serializable {

    public Archer() {
        super("Mumei the Archer",175, 10, 50, 90, 0.8, 0.4);
    }
//
//    @Override
//    public void attack(DungeonCharacter enemy) {
//        Scanner kb = new Scanner(System.in);
//        String playerChoice;
//        hitChance = 0.8;
//        minDmgRange = 50;
//        maxDmgRange = 80;
//        System.out.println(name + " please choose your action from the following menu:");
//        System.out.println("1) Attack");
//        System.out.println("2) Skill: True Shot");
//        System.out.println("3) Magic: Civilization's Blessing");
//        System.out.println("4) Guard");
//        playerChoice = kb.nextLine();
//        int i = Integer.parseInt(playerChoice);
//        switch(i) {
//            case 1:
//                super.attack(enemy);
//                break;
//            case 2:
//                specialSkill(enemy);
//                break;
//            case 3:
//                magic(enemy);
//                break;
//            case 4:
//                enemy.damageLastTaken = -1; //flag to say that hero is guarding
//                break;
//        }
//    }

    @Override
    public void specialSkill(DungeonCharacter enemy) {
        Random random = new Random();
        double randomDouble = random.nextDouble();
        int damage;
        System.out.println(name + " used True Shot");
        System.out.println("Your aim never fails you!");
        damage  = random.nextInt(100 - 60) + 60;
        enemy.damageLastTaken = damage;
        minDmgRange = 60;
        maxDmgRange = 100;
    }

    @Override
    public void magic(DungeonCharacter character) {
        System.out.println(name + " used Civilization's Blessing");
        System.out.println(name + " permanently increased their damage range by 15");
        minDmgRange += 15;
        maxDmgRange += 15;
    }
}


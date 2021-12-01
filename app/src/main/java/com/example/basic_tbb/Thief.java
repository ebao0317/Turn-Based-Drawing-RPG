package com.example.basic_tbb;

import java.io.Serializable;
import java.util.Random;
import java.util.Scanner;

public class Thief extends Hero implements Serializable {

    public Thief() {
        super("Pekora the Thief", 150, 10, 40, 80, 0.9, 0.5);
    }

//    @Override
//    public void attack(DungeonCharacter enemy) {
//        Scanner kb = new Scanner(System.in);
//        String playerChoice;
//        blockChance = 0.3;
//        minDmgRange = 50;
//        maxDmgRange = 80;
//        System.out.println(name + " please choose your action from the following menu:");
//        System.out.println("1) Attack");
//        System.out.println("2) Skill: Double Strike");
//        System.out.println("3) Magic: Cloak");
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
        if(0.7 > randomDouble) {
            damage  = random.nextInt(100 - 50) + 50;
            System.out.println(name + " used Double Strike");
            enemy.damageLastTaken = damage;
            minDmgRange = 50;
            maxDmgRange = 80;
        }
        else {
            System.out.println("Double Strike has failed to hit...");
        }
    }

    @Override
    public void magic(DungeonCharacter character) {
        System.out.println(name + " casted Cloak");
        System.out.println("You evade all attacks for this turn.");
        blockChance = 1;
    }
}


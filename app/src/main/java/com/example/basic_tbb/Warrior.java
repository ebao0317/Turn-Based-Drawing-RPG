package com.example.basic_tbb;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Warrior extends Hero implements Serializable {

    public Warrior() {
        super("Noel the Knight", 250, 20, 60, 110, 0.7, 0.4);
    }

//    @Override
//    public void attack(DungeonCharacter enemy) {
//        Scanner kb = new Scanner(System.in);
//        String playerChoice;
//        minDmgRange = 50;
//        maxDmgRange = 80;
//        System.out.println(name + " please choose your action from the following menu:");
//        System.out.println("1) Attack");
//        System.out.println("2) Skill: CRUSHING BLOW");
//        System.out.println("3) Magic: Defense Boost");
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
    public String specialSkill(DungeonCharacter enemy) {
        String nameOfAbility = "CRUSHING BLOW";
        Random random = new Random();
        double randomDouble = random.nextDouble();
        int damage;
        if(0.3 > randomDouble) {
            damage = random.nextInt(250 - 150) + 150;
            enemy.damageLastTaken = damage;
            minDmgRange = 150;
            maxDmgRange = 250;
        }
        return nameOfAbility;
    }

    @Override
    public List<String> magic(DungeonCharacter enemy) {
        defense += 5;
        List<String> magicDescriptions= new ArrayList<>();
        magicDescriptions.add(" casted Barrier");
        magicDescriptions.add(" raised her defense by 5");

        return magicDescriptions;
    }
}


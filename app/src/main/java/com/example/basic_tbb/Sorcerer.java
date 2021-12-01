package com.example.basic_tbb;

import java.io.Serializable;
import java.util.Random;
import java.util.Scanner;

public class Sorcerer extends Hero implements Serializable {

    public Sorcerer() {
        super("Suisei the Sorceress", 200, 15, 70, 130, 0.9, 0.25);
    }

//    @Override
//    public void attack(DungeonCharacter enemy) {
//        Scanner kb = new Scanner(System.in);
//        String playerChoice;
//        minDmgRange = 70;
//        maxDmgRange = 130;
//        System.out.println(name + " please choose your action from the following menu:");
//        System.out.println("1) Attack");
//        System.out.println("2) Skill: Heal");
//        System.out.println("3) Magic: Suisei's Comet");
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
    public void magic(DungeonCharacter enemy) {
        Random randNum = new Random();
        double randomDouble = randNum.nextDouble();
        int heal;
        if(HP == 200) { //if it is dead, cannot heal. if it took no damage, should not heal
            System.out.println(name + " tried healing but you cannot heal past your max HP");
            return;
        }
        heal = randNum.nextInt(70 - 40) + 40;

        if(HP + heal > 200) {
            heal = 200 - HP;
        }
        HP += heal;
        System.out.println(name + " successfully healed for " + heal + " HP.");
        System.out.println(name + " now has " + HP + " HP.");
    }

    @Override
    public void specialSkill(DungeonCharacter enemy) {
        Random random = new Random();
        double randomDouble = random.nextDouble();
        int damage;
        if(0.75 > randomDouble) {
            damage  = random.nextInt(180 - 100) + 100;
            System.out.println(name + " used Suisei's Comet");
            enemy.damageLastTaken = damage;
            minDmgRange = 100;
            maxDmgRange = 180;
        }
        else {
            System.out.println("Suisei's Comet has failed to hit...");
        }
    }
}


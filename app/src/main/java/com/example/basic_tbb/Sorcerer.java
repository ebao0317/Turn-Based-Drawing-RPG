package com.example.basic_tbb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
    public List<String> magic(DungeonCharacter enemy) {
        List<String> magicDescriptions= new ArrayList<>();
        Random randNum = new Random();
        double randomDouble = randNum.nextDouble();
        int heal;
        if(HP == 200) { //if it is dead, cannot heal. if it took no damage, should not heal
            magicDescriptions.add("can't heal past full health!");
            magicDescriptions.add("healed 0 HP");
            return magicDescriptions;
        }
        heal = randNum.nextInt(70 - 40) + 40;

        if(HP + heal > 200) {
            heal = 200 - HP;
        }
        HP += heal;

        magicDescriptions.add(" healed herself");
        magicDescriptions.add(" healed "+String.valueOf(heal) + " HP");
        return magicDescriptions;
    }

    @Override
    public String specialSkill(DungeonCharacter enemy) {
        String nameOfAbility = "Suisei's Comet";
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
        return nameOfAbility;
    }
}


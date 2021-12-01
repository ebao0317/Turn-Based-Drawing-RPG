package com.example.basic_tbb;
import java.io.Serializable;
import java.util.Scanner;

abstract public class Hero extends DungeonCharacter implements Serializable {
    public Hero(String name, int HP, int defense, int minDmgRange, int maxDmgRange, double hitChance, double blockChance) {
        super(name, HP, defense, minDmgRange, maxDmgRange, hitChance, blockChance);
    }

    abstract public void specialSkill(DungeonCharacter enemy);
    abstract public void magic(DungeonCharacter enemy);

    public void getHeroName() {
        Scanner kb = new Scanner(System.in);
        System.out.println("Dear Hero, what is your name?");
        name = kb.nextLine();
    }

}


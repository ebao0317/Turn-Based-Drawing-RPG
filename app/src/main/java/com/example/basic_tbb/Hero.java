package com.example.basic_tbb;
import java.io.Serializable;
import java.util.List;
import java.util.Scanner;

abstract public class Hero extends DungeonCharacter implements Serializable {
    public Hero(String name, int HP, int defense, int minDmgRange, int maxDmgRange, double hitChance, double blockChance) {
        super(name, HP, defense, minDmgRange, maxDmgRange, hitChance, blockChance);
    }

    abstract public String specialSkill(DungeonCharacter enemy);
    abstract public List<String> magic(DungeonCharacter enemy);

}


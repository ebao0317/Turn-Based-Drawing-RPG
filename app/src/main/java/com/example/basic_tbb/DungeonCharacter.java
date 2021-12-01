package com.example.basic_tbb;

import java.io.Serializable;
import java.util.Random;

abstract public class DungeonCharacter implements Serializable {
    protected String name;
    protected int HP;
    protected int defense;
    protected int minDmgRange;
    protected int maxDmgRange;
    protected int damageLastTaken;
    protected double hitChance;
    protected double blockChance;

    //DVC
    public DungeonCharacter() {
        HP = 0;
        defense = 0;
        name = "NAME";
        minDmgRange = 0;
        maxDmgRange = 0;
        damageLastTaken = 0;
        hitChance = 0;
        blockChance = 0;
    }

    public DungeonCharacter(String name, int HP, int defense, int minDmgRange, int maxDmgRange, double hitChance, double blockChance) {
        this.name = name;
        this.HP = HP;
        this.defense = defense;
        this.minDmgRange = minDmgRange;
        this.maxDmgRange = maxDmgRange;
        this.damageLastTaken = 0;
        this.hitChance = hitChance;
        this.blockChance = blockChance;
    }

    public void attack(DungeonCharacter enemy) {
        Random random = new Random();
        double randNum = random.nextDouble();
        int damage;
        if(hitChance > randNum) {
            damage = random.nextInt(maxDmgRange - minDmgRange) + minDmgRange;
            damage -= enemy.defense * 1.5;
            if(damage < 0) {
                damage = 0;
            }
            enemy.damageLastTaken = damage;
        }
        else {
            System.out.println(name + "'s attack missed...");
            enemy.damageLastTaken = 0;
        }
    }

    public void guard(DungeonCharacter enemy) {
        Random random = new Random();
        if(damageLastTaken != 0) {
            damageLastTaken = random.nextInt(enemy.minDmgRange - 5) + 5;
        }
    }

    public boolean checkBlock() {
        Random randNum = new Random();
        if(damageLastTaken != 0){ //if they took no damage, no point in checking if they blocked
            double randomDouble = randNum.nextDouble();
            if (blockChance > randomDouble) {
                damageLastTaken = 0; //negates the attack so when going to apply damage stage, no damage will be applied
                return true;
            }
        }
        return false;
    }

    public boolean ifDamageTaken() {
        if(damageLastTaken != 0) {
            return true;
        }
        return false;
    }

    public void takeDamage() {
        if(damageLastTaken == 0) {
            System.out.println(name + " took no damage.");
            return;
        }
        System.out.println(name + " took " + damageLastTaken + " damage.");
        HP -= damageLastTaken;
        //damageLastTaken = 0;
    }
}


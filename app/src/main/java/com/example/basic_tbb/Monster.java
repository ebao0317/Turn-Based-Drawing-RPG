package com.example.basic_tbb;

import java.io.Serializable;
import java.util.Random;

abstract public class Monster extends DungeonCharacter{
    protected double chanceToHeal;
    protected int minHeal;
    protected int maxHeal;
    protected int lastHeal;

    public Monster(String name, int HP, int defense, int minDmgRange, int maxDmgRange,
                   double hitChance, double blockChance, double chanceToHeal, int minHeal, int maxHeal) {
        super(name, HP, defense, minDmgRange, maxDmgRange, hitChance, blockChance);
        this.chanceToHeal = chanceToHeal;
        this.minHeal = minHeal;
        this.maxHeal = maxHeal;
        this.lastHeal = 0;
    }

    public boolean heal() {
        Random randNum = new Random();
        double randomDouble = randNum.nextDouble();
        int heal;
        if(HP <= 0 || damageLastTaken == 0) { //if it is dead, cannot heal. if it took no damage, should not heal
            return false;
        }
        if(chanceToHeal > randomDouble) {
            heal = randNum.nextInt(maxHeal - minHeal) + minHeal;
            HP += heal;
            System.out.println(name + " successfully healed for " + heal + " HP.");
            System.out.println(name + " now has " + HP + " HP.");
            lastHeal = heal;
            return true;
        }
        else {
            System.out.println(name + " failed to heal.");
            lastHeal = 0;
            return false;
        }
    }

    abstract public void specialSkill(DungeonCharacter enemy);
}


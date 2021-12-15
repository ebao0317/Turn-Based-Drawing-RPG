package com.example.basic_tbb;

import android.net.Uri;

import java.io.Serializable;
import java.util.Random;

public class CustomHero extends Hero implements Serializable {

    //Hero attributes
    private Uri heroImage;

    //strings needed to output to console what the users abilities are
    private String magicName = "Magic";
    private String specialSkillEffect = "Special Skill";

    //default values
    private MagicType magicAbility = MagicType.SUPPORTMAGIC;
    private SpecialSkillType specialAbility = SpecialSkillType.ACCURATEATTACK;

    public CustomHero()
    {
        super("Custom Hero",200, 15, 60, 100, 0.8, 0.4);
    }

    @Override
    public void specialSkill(DungeonCharacter enemy) {
        //TODO maybe allow the user to create their own special ability
        Random random = new Random();
        double randomDouble = random.nextDouble();
        int damage;
        //System.out.println(name + "used Special Ability");
        //System.out.println("Success!");
        //damage  = random.nextInt(100 - 60) + 60;
        //enemy.damageLastTaken = damage;
        //minDmgRange = 60;
        //maxDmgRange = 100;

        switch(this.specialAbility)
        {
            case RUSHATTACK:
                return;
            case HEAVYATTACK:
                return;
            case ACCURATEATTACK:
                return;
            default:
                return;
        }
    }

    @Override
    public void magic(DungeonCharacter enemy) {
        //TODO maybe allow the user to create their own magic
        System.out.println(name + "used " + magicName);
        System.out.println(name + " permanently increased their damage range by 15");
        minDmgRange += 15;
        maxDmgRange += 15;

        switch(this.magicAbility)
        {
            case DEFENSEMAGIC:
                System.out.println(name + "used " + magicName);
                return;
            case OFFENSEMAGIC:
                System.out.println(name + "used " + magicName);
                return;
            case SUPPORTMAGIC:
                System.out.println(name + "used " + magicName);
                return;
        }
    }


    public String getHeroName() {return this.name;}
    public String getMagicName(){return this.magicName;}
    public String getSpecialSkillName(){return this.specialSkillEffect;}
    public Uri getHeroImage() {return heroImage;}

    public void setMagicName(String magicName){this.magicName = magicName;}
    public void setSpecialSkillEffect(String specialSkillEffect){this.specialSkillEffect = specialSkillEffect;}
    public void setHeroName(String heroName){this.name = heroName;}

    //takes strings resources from the magic spinner
    public void setMagicType(String magicType)
    {
        switch(magicType)
        {
            case "Support Magic":
                this.magicAbility = MagicType.SUPPORTMAGIC;
                break;
            case "Offense Magic":
                this.magicAbility = MagicType.OFFENSEMAGIC;
                break;
            case "Defense Magic":
                this.magicAbility = MagicType.DEFENSEMAGIC;
                break;
        }
    }
    //takes strings resources from the skill spinner
    public void setSpecialSkillType(String skillType)
    {
        switch(skillType)
        {
            case "Heavy Attack":
                this.specialAbility = SpecialSkillType.HEAVYATTACK;
                break;
            case "Rush Attack":
                this.specialAbility = SpecialSkillType.RUSHATTACK;
                break;
            case "Accurate Attack":
                this.specialAbility = SpecialSkillType.ACCURATEATTACK;
                break;
        }
    }
    public void setHeroImage(Uri heroImage) {this.heroImage = heroImage;}

    private enum MagicType
    {
        SUPPORTMAGIC,
        OFFENSEMAGIC,
        DEFENSEMAGIC
    }

    private enum SpecialSkillType
    {
        HEAVYATTACK,
        RUSHATTACK,
        ACCURATEATTACK
    }
}

package com.example.basic_tbb;

import android.net.Uri;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
        super("Custom Hero",200, 15, 60, 100, 0.75, 0.4);
    }

    @Override
    public String specialSkill(DungeonCharacter enemy) {
        String nameOfAbility = "";
        Random random = new Random();
        double randomDouble = random.nextDouble();

        switch(this.specialAbility)
        {
            case RUSHATTACK:
                //Ability does considerably more damage at the cost of the hero's own health and accuracy
                nameOfAbility = "Rush Attack";
                if(0.5 > randomDouble)
                {
                    HP -= 15;
                    enemy.damageLastTaken = 190;
                }
                return nameOfAbility;

            case HEAVYATTACK:
                //Ability does more damage than a regular attack at the cost of some accuracy
                nameOfAbility = "Heavy Attack";
                if(0.5 > randomDouble)
                    enemy.damageLastTaken = 130;

                return nameOfAbility;

            case ACCURATEATTACK:
                //Ability always hits
                enemy.damageLastTaken = 60;
                nameOfAbility = "Accurate Attack";
                return nameOfAbility;
        }
        return null;
    }

    @Override
    public List<String> magic(DungeonCharacter enemy) {
        List<String> magicDescriptions= new ArrayList<>();

        switch(this.magicAbility)
        {
            case DEFENSEMAGIC:
                //Buff defense
                defense += 5;
                magicDescriptions.add("casted Barrier");
                magicDescriptions.add(" increased it's defense by 5");
                return magicDescriptions;
            case OFFENSEMAGIC:
                //Shoot fireball
                enemy.damageLastTaken = 60;
                magicDescriptions.add(" casted a Fireball");
                magicDescriptions.add(" dealt 60 damage to the enemy");
                return magicDescriptions;
            case SUPPORTMAGIC:
                //Heal yourself
                HP += 85;
                magicDescriptions.add(" casted a healing spell");
                magicDescriptions.add(" healed 85 HP");
                return magicDescriptions;
        }
        return null;
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

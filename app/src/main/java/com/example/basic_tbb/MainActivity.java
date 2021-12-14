package com.example.basic_tbb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    static final String TAG = "mainActivity: ";
    Monster monster = null;
    Hero hero = null;
    Random random = new Random();
    boolean heroBlock = false;
    boolean monsterBlock = false;
    TextView monsterName;
    TextView monsterHP;
    TextView heroName;
    TextView heroHP;
    TextView actionInfo;
    Button attackButton;
    Button specialButton;
    Button magicButton;
    Button guardButton;
    ImageView monsterImage;
    ImageView heroImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        monsterName = findViewById(R.id.monsterName);
        monsterHP = findViewById(R.id.monsterHP);
        heroName = findViewById(R.id.heroName);
        heroHP = findViewById(R.id.heroHP);
        actionInfo = findViewById(R.id.actionInfo);
        specialButton = findViewById(R.id.specialButton);
        attackButton = findViewById(R.id.attackButton);
        magicButton = findViewById(R.id.magicButton);
        guardButton = findViewById(R.id.guardButton);
        monsterImage = findViewById(R.id.imageView);
        heroImage = findViewById(R.id.imageView2);

        Intent intent = getIntent();

        if(intent != null) {
            if(intent.hasExtra("custom character"))
            {
                hero = (Hero) intent.getSerializableExtra("custom character");
                Uri image = Uri.parse(intent.getStringExtra("image"));
                heroImage.setImageURI(image);
            }
            else {
                hero = (Hero) intent.getSerializableExtra("hero");
                Log.d(TAG, hero.name);
            }
        }

        heroName.setText(hero.name);
        heroHP.setText(String.valueOf(hero.HP) + " HP");
        chooseMonster();
        monsterName.setText(monster.name);
        monsterHP.setText(String.valueOf(monster.HP) + " HP");

        attackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionInfo.setText("Actions this Round");
                actionInfo.append("\n" + hero.name + " attacks");
                hero.attack(monster);
                if (monster.damageLastTaken == 0) {
                    actionInfo.append("\n" + hero.name + "'s attack missed...");
                }
                monsterTurn();
                if (monster.ifDamageTaken()) {
                    if (monsterBlock) {
                        monster.guard(hero);
                    }
                    if (monster.checkBlock()) {
                        actionInfo.append("\n" + monster.name + " successfully blocked/evaded the attack");
                        System.out.println(monster.name + " successfully blocked/evaded the attack");
                    }
                    else {
                        monster.takeDamage();
                        actionInfo.append("\n" + monster.name + " took " + monster.damageLastTaken + " damage");
                        if(monster.heal()) {
                            actionInfo.append("\n" + monster.name + " healed herself for " + monster.lastHeal + " HP");
                        }
                        else {
                            actionInfo.append("\n" + monster.name + " failed to heal herself");
                        }
                        monsterHP.setText(String.valueOf(monster.HP) + " HP");
                    }
                }
                if(hero.ifDamageTaken()) {
                    if(heroBlock) {
                        hero.guard(monster);
                    }
                    if(hero.checkBlock()) {
                        actionInfo.append("\n" + hero.name + " successfully blocked/evaded the attack");
                        System.out.println(hero.name + " successfully blocked/evaded the attack");
                    }
                    else {
                        hero.takeDamage();
                        actionInfo.append("\n" + hero.name + " took " + hero.damageLastTaken + " damage");
                    }
                    heroHP.setText(String.valueOf(hero.HP) + " HP");
                    hero.damageLastTaken = 0;
                    monster.damageLastTaken = 0;
                }
                gameOverCheck();
            }

        });
        specialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionInfo.setText("Actions this Round");
                hero.specialSkill(monster);
                if(hero instanceof Warrior) {
                    if(monster.damageLastTaken == 0) {
                        actionInfo.append("\n" + hero.name + " failed to hit CRUSHING BLOW");
                    }
                    else {
                        actionInfo.append("\n" + hero.name + " successfully landed CRUSHING BLOW");
                    }
                }
                if(hero instanceof Sorcerer) {
                    if(monster.damageLastTaken == 0) {
                        actionInfo.append("\n" + hero.name + " failed to hit Suisei's Comet");
                    }
                    else {
                        actionInfo.append("\n" + hero.name + " successfully landed Suisei's Comet");
                    }
                }
                if(hero instanceof Thief) {
                    if(monster.damageLastTaken == 0) {
                        actionInfo.append("\n" + hero.name + " failed to hit Double Strike");
                    }
                    else {
                        actionInfo.append("\n" + hero.name + " successfully landed Double Strike");
                    }
                }
                if(hero instanceof Archer) {
                    actionInfo.append("\n" + hero.name + " used True Shot");
                    actionInfo.append("\n Your aim never fails you!");
                }
                if (monster.damageLastTaken == 0) {
                    actionInfo.append("\n" + hero.name + "'s attack missed...");
                }
                if(hero instanceof CustomHero) {
                    if(monster.damageLastTaken == 0) {
                        actionInfo.append("\n" + hero.name + " failed to land Special Attack");
                    }
                    else {
                        actionInfo.append("\n" + hero.name + " successfully landed Special Attack");
                    }
                }
                monsterTurn();
                if (monster.ifDamageTaken()) {
                    if (monsterBlock) {
                        monster.guard(hero);
                    }
                    if (monster.checkBlock()) {
                        actionInfo.append("\n" + monster.name + " successfully blocked/evaded the attack");
                        System.out.println(monster.name + " successfully blocked/evaded the attack");
                    }
                    else {
                        monster.takeDamage();
                        actionInfo.append("\n" + monster.name + " took " + monster.damageLastTaken + " damage");
                        if(monster.heal()) {
                            actionInfo.append("\n" + monster.name + " healed herself for " + monster.lastHeal + " HP");
                        }
                        else {
                            actionInfo.append("\n" + monster.name + " failed to heal herself");
                        }
                        monsterHP.setText(String.valueOf(monster.HP) + " HP");
                    }
                }
                if(hero.ifDamageTaken()) {
                    if(heroBlock) {
                        hero.guard(monster);
                    }
                    if(hero.checkBlock()) {
                        actionInfo.append("\n" + hero.name + " successfully blocked/evaded the attack");
                        System.out.println(hero.name + " successfully blocked/evaded the attack");
                    }
                    else {
                        hero.takeDamage();
                        actionInfo.append("\n" + hero.name + " took " + hero.damageLastTaken + " damage");
                    }
                    heroHP.setText(String.valueOf(hero.HP) + " HP");
                    hero.damageLastTaken = 0;
                    monster.damageLastTaken = 0;
                }
                gameOverCheck();
            }
        });
        magicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionInfo.setText("Actions this Round");
                hero.magic(monster);
                if(hero instanceof Warrior) {
                    actionInfo.append("\n" + hero.name + " casted Barrier");
                    actionInfo.append("\n" + hero.name + " raised her defense by 5");
                }
                if(hero instanceof Sorcerer) {
                    actionInfo.append("\n" + hero.name + " healed herself");
                }
                if(hero instanceof Thief) {
                    actionInfo.append("\n" + hero.name + " casted Cloak");
                    actionInfo.append("\n" + hero.name + " will evade everything this turn");
                }
                if(hero instanceof Archer) {
                    actionInfo.append("\n" + hero.name + " casted Civilization's Blessing");
                    actionInfo.append("\n" + hero.name + " increased damage range by 15");
                }
                if(hero instanceof CustomHero) {
                    actionInfo.append("\n" + hero.name + " casted their True Magic");
                    actionInfo.append("\n" + hero.name + " increased damage range by 15");
                }
                monsterTurn();
                    if (hero.ifDamageTaken()) {
                        if (heroBlock) {
                            hero.guard(monster);
                        }
                        if (hero.checkBlock()) {
                            actionInfo.append("\n" + hero.name + " successfully blocked/evaded the attack");
                            System.out.println(hero.name + " successfully blocked/evaded the attack");
                        } else {
                            hero.takeDamage();
                            actionInfo.append("\n" + hero.name + " took " + hero.damageLastTaken + " damage");
                        }
                        hero.damageLastTaken = 0;
                        monster.damageLastTaken = 0;
                    }
                heroHP.setText(String.valueOf(hero.HP) + " HP");
                if(hero instanceof Thief) {
                    hero.blockChance = 0.5;
                }
                gameOverCheck();
            }
        });
        guardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionInfo.setText("Actions this Round");
                actionInfo.append("\n" + hero.name + " is guarding");
                heroBlock = true;
                monsterTurn();
                if(hero.ifDamageTaken()) {
                    if(heroBlock) {
                        hero.guard(monster);
                    }
                    if(hero.checkBlock()) {
                        actionInfo.append("\n" + hero.name + " successfully blocked/evaded the attack");
                        System.out.println(hero.name + " successfully blocked/evaded the attack");
                    }
                    else {
                        hero.takeDamage();
                        actionInfo.append("\n" + hero.name + " took " + hero.damageLastTaken + " damage");
                    }
                    heroHP.setText(String.valueOf(hero.HP) + " HP");
                    hero.damageLastTaken = 0;
                    monster.damageLastTaken = 0;
                }
                gameOverCheck();
            }
        });

    }

    public void monsterTurn() {
        int monsterChoice = random.nextInt(4 - 1) + 1;
        switch (monsterChoice) {
            case 1:
                actionInfo.append("\n" + monster.name + " attacks");
                System.out.println(monster.name + " attacks");
                monster.attack(hero);
                if (hero.damageLastTaken == 0) {
                    actionInfo.append("\n" + monster.name + " attack missed...");
                }
                break;
            case 2:
                monsterBlock = true;
                actionInfo.append("\n" + monster.name + " is guarding");
                System.out.println(monster.name + " is guarding");
                break;
            case 3:
                monster.specialSkill(hero);
                if(monster instanceof AncientOne) {
                    if(hero.damageLastTaken == 0) {
                        actionInfo.append("\n" + monster.name + " failed to hit TENTACLE CRUSHER");
                    }
                    else {
                        actionInfo.append("\n" + monster.name + " used TENTACLE CRUSHER");
                    }
                }
                if(monster instanceof Warden) {
                    if(hero.damageLastTaken == 0) {
                        actionInfo.append("\n" + monster.name + " failed to hit Time's Judgement");
                    }
                    else {
                        actionInfo.append("\n" + monster.name + " used Time's Judgement");
                    }
                }
                if(monster instanceof Demon) {
                    if(hero.damageLastTaken == 0) {
                        actionInfo.append("\n" + monster.name + " failed to hit Life Drain");
                    }
                    else {
                        actionInfo.append("\n" + monster.name + " used Life Drain");
                        actionInfo.append("\n" + monster.name + " healed herself for " + monster.lastHeal);
                    }
                }
                if(monster instanceof Oni) {
                    actionInfo.append("\n" + monster.name + " used Monstrous Strength");
                    actionInfo.append("\n" + monster.name + " buffed herself immensely");
                }

                break;
        }
    }
    public void gameOverCheck() {
        String winner;
        if(monster.HP <= 0 || hero.HP <= 0) {
            if(monster.HP <= 0) {
                winner = hero.name;
            }
            else {
                winner = monster.name;
            }
            attackButton.setEnabled(false);
            specialButton.setEnabled(false);
            magicButton.setEnabled(false);
            guardButton.setEnabled(false);
            actionInfo.append("\n" + winner + " has won this time!");
        }
    }
    public void chooseMonster() {
        int monsterPicker = random.nextInt(4) + 1;
        if(monsterPicker == 1) {
            monster = new AncientOne();
        }
        else if(monsterPicker == 2) {
            monster = new Demon();
        }
        else if(monsterPicker == 3) {
            monster = new Oni();
        }
        else if(monsterPicker == 4) {
            monster = new Warden();
        }
    }
}
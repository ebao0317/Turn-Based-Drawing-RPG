package com.example.basic_tbb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

//This is a super simple activity that shows the user a description of the game's 'How To Play'
public class HowToPlayActivity extends AppCompatActivity {
    TextView description;
    Button returnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_play);

        returnButton = findViewById(R.id.return_button);
        description = findViewById(R.id.description);

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        showDescription();
    }

    private void showDescription()
    {
        String text = "Welcome to our Basic Turn Based Battle game. \n" +
                "In this game you have the option to choose from " +
                "4 preset classes that all have their unique stats, skills and magic. " +
                "You also have the option to customize your own hero and draw a unique " +
                "portrait to be displayed. Once you decide your class, your hero will then " +
                "face off against one of 4 monsters, each with their own unique stats. " +
                "Each turn you have the option to attack, use a special skill, cast a spell " +
                "or guard. Attacking is quite simple, based on the hero's stats you will attempt " +
                "to attack the monster. However, be aware that the monster can dodge or even block " +
                "your attack. Special skills are unique to each hero and allow you to take a" +
                "chance to do a cool effect that will give you a massive advantage within the game. " +
                "Unlike special skills, magic always has works and has no accuracy matrix, they too " +
                "also give you an advantage but usually don't deal any damage. Guarding gives you " +
                "the option to block a portion of the enemies attack. " +
                "The game ends when either the monster or the hero runs out of health points. " +
                "It's important to note that the custom hero has 3 optional special skills and 3 " +
                "optional magic abilities. Try them out and see which one you like best!";

        description.setText(text);
    }
}
package com.example.basic_tbb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ChooseCharacterScreen extends AppCompatActivity {
    static final String TAG = "selection";
    Hero hero = null;
    TextView askView;
    Button warriorButton;
    Button sorcererButton;
    Button thiefButton;
    Button archerButton;
    Button customCharacter;

    Uri selectedImage;
    private static final int PICK_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_character_screen);
        askView = findViewById(R.id.askClass);
        warriorButton = findViewById(R.id.warriorButton);
        sorcererButton = findViewById(R.id.sorcererButton);
        thiefButton = findViewById(R.id.thiefButton);
        archerButton = findViewById(R.id.archerButton);


        warriorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChooseCharacterScreen.this, MainActivity.class);
                hero = new Warrior();
                intent.putExtra("hero", hero);
                Log.d(TAG, hero.name);
                startActivity(intent);
            }
        });

        sorcererButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChooseCharacterScreen.this, MainActivity.class);
                hero = new Sorcerer();
                intent.putExtra("hero", hero);
                intent.putExtra("hero2", hero);
                Log.d(TAG, hero.name);
                startActivity(intent);
            }
        });

        thiefButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChooseCharacterScreen.this, MainActivity.class);
                hero = new Thief();
                intent.putExtra("hero", hero);
                Log.d(TAG, hero.name);
                startActivity(intent);
            }
        });

        archerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChooseCharacterScreen.this, MainActivity.class);
                hero = new Archer();
                intent.putExtra("hero", hero);
                Log.d(TAG, hero.name);
                startActivity(intent);
            }
        });
        customCharacter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChooseCharacterScreen.this, CreateCharacterActivity.class);
                startActivity(intent);
            }
        });
    }
}

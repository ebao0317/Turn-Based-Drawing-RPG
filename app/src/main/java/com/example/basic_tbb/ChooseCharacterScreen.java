package com.example.basic_tbb;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ChooseCharacterScreen extends AppCompatActivity {
    static final String TAG = "selection";
    Hero hero = null;
    TextView askView;
    Button warriorButton;
    Button sorcererButton;
    Button thiefButton;
    Button archerButton;

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
    }
}
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
    Button toPaintButton;
    Button loadCharacter;

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
        toPaintButton = findViewById(R.id.to_PaintActivity);
        loadCharacter = findViewById(R.id.load_character);

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
        toPaintButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChooseCharacterScreen.this, PaintActivity.class);
                startActivity(intent);
            }
        });

        loadCharacter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //create an intent for going to the gallery
                Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(gallery, 3);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode,resultCode,data);

        if(resultCode == RESULT_OK && data != null)
        {
            selectedImage = data.getData();

            //create an intent for going to MainActivity
            Intent intent =  new Intent(ChooseCharacterScreen.this, MainActivity.class);
            CustomHero customHero = new CustomHero();
            intent.putExtra("image", selectedImage.toString());
            intent.putExtra("custom character", customHero);

            startActivity(intent);
        }
        else
            outputToast("Please Select a character before continuing");
    }

    // Outputs a toast with message passed in as pram
    private void outputToast(String message)
    {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, message, duration);
        toast.show();
    }
}

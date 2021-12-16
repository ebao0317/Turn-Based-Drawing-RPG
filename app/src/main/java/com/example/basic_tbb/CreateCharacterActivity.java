package com.example.basic_tbb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class CreateCharacterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    ImageView heroImage;
    Button drawImage;
    Button loadImage;
    EditText heroName;
    Spinner magicSpinner;
    Spinner skillSpinner;
    EditText magicName;
    EditText skillName;
    Button beginGame;

    CustomHero hero;
    Uri image; //this is need to pass the users drawn image to MainActivity because the game crashes if you try and send Uris through serialized classes

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_character);

        heroImage = findViewById(R.id.hero_image);
        drawImage = findViewById(R.id.draw_image);
        loadImage = findViewById(R.id.load_image);
        heroName = findViewById(R.id.hero_name);
        magicSpinner = findViewById(R.id.magic_spinner);
        skillSpinner = findViewById(R.id.skill_spinner);
        magicName = findViewById(R.id.enter_magic_name);
        skillName = findViewById(R.id.enter_skill_name);
        beginGame = findViewById(R.id.begin_game);

        hero = new CustomHero();
        image = Uri.parse("android.resource://com.example.basic_tbb//drawable/warrior"); //set default image so you don't pass a null image through an intent

        drawImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateCharacterActivity.this, PaintActivity.class);
                startActivity(intent);
            }
        });

        loadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //create an intent for going to the gallery
                Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(gallery, 3);
            }
        });

        heroName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //not used
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                hero.setHeroName(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                //not used
            }
        });

        //both spinners use the same overridden method 'onItemSelected()'
        ArrayAdapter<CharSequence> magicAdapter = ArrayAdapter.createFromResource(this, R.array.magicTypes, android.R.layout.simple_spinner_item);
        magicAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        magicSpinner.setAdapter(magicAdapter);
        magicSpinner.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> skillAdapter = ArrayAdapter.createFromResource(this, R.array.skillTypes, android.R.layout.simple_spinner_item);
        skillAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        skillSpinner.setAdapter(skillAdapter);
        skillSpinner.setOnItemSelectedListener(this);

        magicName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //not used
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                hero.setMagicName(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                //not used
            }
        });

        skillName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //not used
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                hero.setSpecialSkillEffect(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {


            }
        });

        beginGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateCharacterActivity.this, MainActivity.class);
                intent.putExtra("custom character", hero);
                intent.putExtra("image", image.toString());

                startActivity(intent);
            }
        });
        }

    //this method gets called after the user exits the gallery
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode,resultCode,data);

        if(resultCode == RESULT_OK && data != null)
        {
            Uri selectedImage = data.getData();
            setHeroImage(selectedImage);

        }
        else
            outputToast("Please Select a character before continuing");
    }

    private void setHeroImage(Uri image)
    {
        heroImage.setImageURI(image);
        this.image = image;
    }

    // Outputs a toast with message passed in as pram
    private void outputToast(String message)
    {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, message, duration);
        toast.show();
    }

    //method called when the user is interacting with a spinner
    //collects user's selection and changes hero accordingly
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String selectedItem = parent.getItemAtPosition(position).toString();
        switch(parent.getId())
        {
            case R.id.magic_spinner:
                hero.setMagicType(selectedItem);
                break;
            case R.id.skill_spinner:
                hero.setSpecialSkillType(selectedItem);
                break;
        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        //not used
    }
}
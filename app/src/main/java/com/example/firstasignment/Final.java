package com.example.firstasignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Final extends AppCompatActivity {

    ImageView play;
    ImageView no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        play = findViewById(R.id.imageView5);
        no = findViewById(R.id.imageView6);
    }
    public void redrum(View view){
        Intent redrumGame = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.roblox.com/games/7661862735/R3DRUM"));
        startActivity(redrumGame);

    }
    public void Main(View v){
        Intent main =new Intent(Final.this,MainActivity.class);
        startActivity(main);
    }
}
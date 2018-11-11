package com.example.bygra.reproductordemusica;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.util.ArrayList;

import static android.os.Environment.getExternalStorageDirectory;

public class MainActivity extends AppCompatActivity {

    Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        
        File carpetaMusica = new File(getExternalStorageDirectory(), "musica");
        carpetaMusica.mkdirs();

        bt = findViewById(R.id.bt);

    }

    public void goTo (View v){
        Intent i = new Intent(this,Reproductor.class);
        startActivity(i);;
    }


}

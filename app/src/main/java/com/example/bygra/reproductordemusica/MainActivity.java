package com.example.bygra.reproductordemusica;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.File;
import java.util.ArrayList;

import static android.os.Environment.getExternalStorageDirectory;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mpCancion;
    ArrayList<MediaPlayer> mpList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        
        File carpetaMusica = new File(getExternalStorageDirectory(), "musica");
        carpetaMusica.mkdirs();

        mpList.add(MediaPlayer.create(getApplicationContext(), R.raw.evilmortytheme));


    }
}

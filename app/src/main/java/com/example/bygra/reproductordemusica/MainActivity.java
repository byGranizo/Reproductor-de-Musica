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

        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.la_raiz_suya_es_mi_guerra.mp3);


    }
}

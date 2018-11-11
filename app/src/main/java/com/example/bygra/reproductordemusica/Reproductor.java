package com.example.bygra.reproductordemusica;

import android.content.ContentUris;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;

import java.io.File;
import java.util.ArrayList;

import static android.os.Environment.getExternalStorageDirectory;

public class Reproductor extends AppCompatActivity {

    File carpetaMusica = new File(getExternalStorageDirectory(), "musica");

    Button btPlayPause;
    ImageView ivCaratula;

    SeekBar sbCancion;

    MediaPlayer mpCancion;
    ArrayList<MediaPlayer> mpList = new ArrayList<>();
    int nCancion = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reproductor);

        btPlayPause = findViewById(R.id.btPlay);
        ivCaratula = findViewById(R.id.ivCaratula);
        sbCancion = findViewById(R.id.sbCancion);

        //Se rellena la lista de reproduccion con las canciones

        mpList.add(MediaPlayer.create(getApplicationContext(), R.raw.kortatu_sarri_sarri));
        for(File f : carpetaMusica.listFiles()){
            if(f.isFile()){
                mpList.add(MediaPlayer.create(this,Uri.fromFile(f)));
            }
        }
    }

    //Se ejecuta al dar al boton de play o pause
    public void playPause (View v){
        if(mpList.get(nCancion).isPlaying()){
            mpList.get(nCancion).pause();
            btPlayPause.setBackgroundResource(R.drawable.pausa);
        }else{
            mpList.get(nCancion).start();
            btPlayPause.setBackgroundResource(R.drawable.play);
        }
    }

    public void nextSong (View v){
        mpList.get(nCancion).stop();

        if(nCancion < mpList.size()){
            nCancion++;
        }else{
            nCancion = 0;
        }
        mpList.get(nCancion);
    }

    public void prevSong (View v){
        mpList.get(nCancion).stop();

        if(mpList.size() == 0){
            nCancion = mpList.size();
        }else{
            nCancion--;
        }
        mpList.get(nCancion);
    }

    public Uri getAlbumArtUri() {
        return ContentUris.withAppendedId(Uri.fromFile(carpetaMusica), nCancion);
    }
}

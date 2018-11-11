package com.example.bygra.reproductordemusica;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;

import java.io.File;
import java.util.ArrayList;

import static android.os.Environment.getExternalStorageDirectory;

public class MainActivity extends AppCompatActivity {

    File carpetaMusica = new File(getExternalStorageDirectory(), "musica");

    Button btPlayPause;

    SeekBar sbCancion;

    MediaPlayer mpCancion;
    ArrayList<MediaPlayer> mpList = new ArrayList<>();
    int nCancion = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        File carpetaMusica = new File(getExternalStorageDirectory(), "musica");
        carpetaMusica.mkdirs();

        btPlayPause = findViewById(R.id.btPlay);
        sbCancion = findViewById(R.id.sbCancion);

        //Se rellena la lista de reproduccion con las canciones

        mpList.add(MediaPlayer.create(getApplicationContext(), R.raw.evil_morty_theme));
        mpList.add(MediaPlayer.create(getApplicationContext(), R.raw.kortatu_sarri_sarri));
        mpList.add(MediaPlayer.create(getApplicationContext(), R.raw.la_polla_records_no_somos_nada));
        mpList.add(MediaPlayer.create(getApplicationContext(), R.raw.la_raiz_suya_mi_guerra));
        mpList.add(MediaPlayer.create(getApplicationContext(), R.raw.riot_propaganda_el_miedo_va_a_cambiar_de_bando_propaganda));

        /*
        for(File f : carpetaMusica.listFiles()){
            if(f.isFile()){
                mpList.add(MediaPlayer.create(this,Uri.fromFile(f)));
            }
        }*/

        btPlayPause.setBackgroundResource(R.drawable.pausa);
        mpList.get(nCancion).start();

    }

    //Se ejecuta al dar al boton de play o pause
    public void playPause (View v){
        if(mpList.get(nCancion).isPlaying()){
            mpList.get(nCancion).pause();
            btPlayPause.setBackgroundResource(R.drawable.play);
        }else{
            mpList.get(nCancion).start();
            btPlayPause.setBackgroundResource(R.drawable.pausa);
        }
    }

    public void nextSong (View v){
        mpList.get(nCancion).stop();

        if(nCancion < mpList.size()){
            nCancion++;
        }else{
            nCancion = 0;
        }
        mpList.get(nCancion).start();
    }

    public void prevSong (View v){
        mpList.get(nCancion).stop();

        if(mpList.size() == 0){
            nCancion = mpList.size();
        }else{
            nCancion--;
        }
        mpList.get(nCancion).start();
    }


}

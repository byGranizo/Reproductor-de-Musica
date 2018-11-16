package com.example.bygra.reproductordemusica;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    Button btPlayPause;

    SeekBar sbCancion;
    Runnable runnable;
    Handler handler = new Handler();;

    MediaPlayer mpCancion;
    ArrayList<MediaPlayer> mpList = new ArrayList<>();
    ArrayList<String> nombreList = new ArrayList<>();

    RecyclerView rvCanciones;

    int nCancion = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        File musicSD = new File("/sdcard/Music");

        btPlayPause = findViewById(R.id.btPlay);
        sbCancion = findViewById(R.id.sbCancion);

        //Se rellena la lista de reproduccion con las canciones

        Log.d("Errores loco", "1");

        Uri u1 = Uri.parse("android.resource://com.example.bygra.reproductordemusica/" + R.raw.evil_morty_theme);
        Uri u2 = Uri.parse("android.resource://com.example.bygra.reproductordemusica/" + R.raw.kortatu_sarri_sarri);
        Uri u3 = Uri.parse("android.resource://com.example.bygra.reproductordemusica/" + R.raw.la_polla_records_no_somos_nada);
        Uri u4 = Uri.parse("android.resource://com.example.bygra.reproductordemusica/" + R.raw.la_raiz_suya_mi_guerra);
        Uri u5 = Uri.parse("android.resource://com.example.bygra.reproductordemusica/" + R.raw.riot_propaganda_el_miedo_va_a_cambiar_de_bando_propaganda);

        mpList.add(MediaPlayer.create(this,R.raw.evil_morty_theme));
        mpList.add(MediaPlayer.create(this,R.raw.kortatu_sarri_sarri));
        mpList.add(MediaPlayer.create(this,R.raw.la_polla_records_no_somos_nada));
        mpList.add(MediaPlayer.create(this,R.raw.la_raiz_suya_mi_guerra));
        mpList.add(MediaPlayer.create(this,R.raw.riot_propaganda_el_miedo_va_a_cambiar_de_bando_propaganda));

        Log.d("Error", "2");
/*
        gestionFicheros.copiarArchivo(new File(u1.getPath()),musicSD);
        gestionFicheros.copiarArchivo(new File(u2.getPath()),musicSD);
        gestionFicheros.copiarArchivo(new File(u3.getPath()),musicSD);
        gestionFicheros.copiarArchivo(new File(u4.getPath()),musicSD);
        gestionFicheros.copiarArchivo(new File(u5.getPath()),musicSD);*/

        Log.d("Error", "3");
/*
        for(File f : musicSD.listFiles()){
            if(f.isFile()){
                mpList.add(MediaPlayer.create(this,Uri.fromFile(f)));
            }
        }
*/

        nombreList.add("1");
        nombreList.add("2");
        nombreList.add("3");
        nombreList.add("4");
        nombreList.add("5");
        nombreList.add("6");
        nombreList.add("7");


        recycler();

        btPlayPause.setBackgroundResource(R.drawable.pausa);
        //mpList.get(nCancion).start();

        seekbar ();
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
        sbCancion.setProgress(0);
        btPlayPause.setBackgroundResource(R.drawable.pausa);
        mpList.get(nCancion).start();
        playCycle();
    }

    public void prevSong (View v){
        mpList.get(nCancion).stop();

        if(mpList.size() == 0){
            nCancion = mpList.size();
        }else{
            nCancion--;
        }
        sbCancion.setProgress(0);
        btPlayPause.setBackgroundResource(R.drawable.pausa);
        mpList.get(nCancion).start();
    }

    public void seekbar (){
        mpList.get(nCancion).setAudioStreamType(AudioManager.STREAM_MUSIC);


        mpList.get(nCancion).setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                sbCancion.setMax(mpList.get(nCancion).getDuration());
                mpList.get(nCancion).start();
                playCycle();

            }
        });

        sbCancion.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser){
                    mpList.get(nCancion).seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void playCycle(){

        sbCancion.setProgress(mpList.get(nCancion).getCurrentPosition());

        if(mpList.get(nCancion).isPlaying()){
            runnable = new Runnable() {
                @Override
                public void run() {
                   playCycle();
                }
            };
            handler.postDelayed(runnable,500);
        }
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        mpList.get(nCancion).release();
        handler.removeCallbacks(runnable);
    }

    public void recycler(){
        rvCanciones = findViewById(R.id.rvCanciones);
        rvAdapter rva = new rvAdapter(this, mpList, nombreList);
        rvCanciones.setAdapter(rva);
        rvCanciones.setLayoutManager(new LinearLayoutManager(this));
    }

}

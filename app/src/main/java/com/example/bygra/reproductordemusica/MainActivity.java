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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static android.os.Environment.getExternalStorageDirectory;

public class MainActivity extends AppCompatActivity {


    Button btPlayPause;

    SeekBar sbCancion;
    Runnable runnable;
    Handler handler;

    ArrayList<MediaPlayer> mpList = new ArrayList<>();

    ListView lvCanciones;

    int nCancion = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        File musicSD = new File("/sdcard/Music");

        btPlayPause = findViewById(R.id.btPlay);
        sbCancion = findViewById(R.id.sbCancion);
        lvCanciones = findViewById(R.id.lvCanciones);



        //Se rellena la lista de reproduccion con las canciones
        Uri u1 = Uri.parse("android.resource://com.example.bygra.reproductordemusica/" + R.raw.evil_morty_theme);
        Uri u2 = Uri.parse("android.resource://com.example.bygra.reproductordemusica/" + R.raw.kortatu_sarri_sarri);
        Uri u3 = Uri.parse("android.resource://com.example.bygra.reproductordemusica/" + R.raw.la_polla_records_no_somos_nada);
        Uri u4 = Uri.parse("android.resource://com.example.bygra.reproductordemusica/" + R.raw.la_raiz_suya_mi_guerra);
        Uri u5 = Uri.parse("android.resource://com.example.bygra.reproductordemusica/" + R.raw.riot_propaganda_el_miedo_va_a_cambiar_de_bando_propaganda);
        Log.d("Pene","-2");
        mpList.add(MediaPlayer.create(this,R.raw.evil_morty_theme));
        Log.d("Pene","ja");
        mpList.add(MediaPlayer.create(this,R.raw.kortatu_sarri_sarri));
        mpList.add(MediaPlayer.create(this,R.raw.la_polla_records_no_somos_nada));
        mpList.add(MediaPlayer.create(this,R.raw.la_raiz_suya_mi_guerra));
        mpList.add(MediaPlayer.create(this,R.raw.riot_propaganda_el_miedo_va_a_cambiar_de_bando_propaganda));

/*
        gestionFicheros.copiarArchivo(new File(u1.getPath()),musicSD);
        gestionFicheros.copiarArchivo(new File(u2.getPath()),musicSD);
        gestionFicheros.copiarArchivo(new File(u3.getPath()),musicSD);
        gestionFicheros.copiarArchivo(new File(u4.getPath()),musicSD);
        gestionFicheros.copiarArchivo(new File(u5.getPath()),musicSD);

        for(File f : musicSD.listFiles()){
            if(f.isFile()){
                mpList.add(MediaPlayer.create(this,Uri.fromFile(f)));
            }
        }*/

        //Lista de nombres
        ArrayList<String> listaNombres = new ArrayList<>();
/*      for (MediaPlayer mp : mpList){
            //listaNombres.add(mp.())
        }
*/      Log.d("Pene","-1");
        listaNombres.add("1");
        listaNombres.add("1");
        listaNombres.add("1");
        listaNombres.add("1");
        listaNombres.add("1");
        listaNombres.add("1");
        Log.d("Pene","0");
        AdapterListView adapter = new AdapterListView(this, listaNombres, mpList);
        Log.d("Pene","1");
        lvCanciones.setAdapter(adapter);
        Log.d("Pene","2");
        lvCanciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                nCancion = position;

                sbCancion.setProgress(0);
                btPlayPause.setBackgroundResource(R.drawable.pausa);
                mpList.get(nCancion).start();
                playCycle();
            }
        });

        //Seekbar
        handler = new Handler();

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
        playCycle();
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

}

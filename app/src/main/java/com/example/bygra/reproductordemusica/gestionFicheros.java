package com.example.bygra.reproductordemusica;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class gestionFicheros {

    //Permite copiar archivos desde una localizacion a la carpeta musica dentro de la SD
    public static void copyFiletoExternalStorage(Context context, int resourceId, String resourceName){
        String pathSDCard = Environment.getExternalStorageDirectory() + "/sdcard/Music/" + resourceName;
        try{
            InputStream in = context.getResources().openRawResource(resourceId);

            FileOutputStream out = null;
            out = new FileOutputStream(pathSDCard);

            byte[] buff = new byte[1024];
            int read = 0;
            try {
                while ((read = in.read(buff)) > 0) {
                    out.write(buff, 0, read);
                }
            } finally {
                in.close();
                out.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //De la carpeta extrae una ArrayList de MediaPlayer con todas las canciones
    public ArrayList<MediaPlayer> getListaCanciones (ArrayList<MediaPlayer> mpList) throws IOException {

        MediaPlayer mp = new MediaPlayer();

        File ruta= new File(Environment.getExternalStorageDirectory() + "/sdcard/Music/");
        File[] files = ruta.listFiles();
        for(int i = 0; i < files.length; i++){
            mp.setDataSource(files[i].getAbsolutePath());
            mpList.add(mp);
        }
        return mpList;
    }

    //De la carpeta extrae una ArrayList de String con todos los nombres de las canciones
    public ArrayList<String> getListaNombres(ArrayList<String> nombresLista){
        File ruta= new File(Environment.getExternalStorageDirectory() + "/sdcard/Music/");
        File[] files = ruta.listFiles();
        for(int i = 0; i < files.length; i++){
            nombresLista.add(files[i].getName());
        }
        return nombresLista;
    }
}

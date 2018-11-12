package com.example.bygra.reproductordemusica;

import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class gestionFicheros {
    public static boolean copiarArchivo(File origen, File destino) {
        if (origen.exists()) {
            try {
                InputStream in = new FileInputStream(origen);
                OutputStream out = new FileOutputStream(destino);
                // We use a buffer for the copy (Usamos un buffer para la copia).
                byte[] buf = new byte[1024];
                Log.d("Error", "x");
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                in.close();
                out.close();
                return true;
            } catch (IOException ioe) {
                ioe.printStackTrace();
                return false;
            }
        } else {
            Log.d("Error", "false");
            return false;
        }
    }
}

package com.example.bygra.reproductordemusica;

import android.content.Context;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AdapterRecycler extends RecyclerView.Adapter<AdapterRecycler.ViewHolder> {

    Context context;
    ArrayList<String> listaNombres;
    List<MediaPlayer> mpList;
    private AdapterView.OnItemClickListener listener;

    public AdapterRecycler(Context context,ArrayList<String> listaNombres, List<MediaPlayer> mpList, AdapterView.OnItemClickListener listener){
        this.context = context;
        this.listaNombres = listaNombres;
        this.mpList = mpList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_recycler, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRecycler.ViewHolder viewHolder, int i) {
        ViewHolder vh = viewHolder;
        vh.tvNombre.setText(listaNombres.get(i));
        vh.tvDuracion.setText(mpList.get(i).getDuration());
    }

    @Override
    public int getItemCount() {
        return listaNombres.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombre;
        TextView tvDuracion;

        public ViewHolder(@NonNull View v){
            super(v);
            tvNombre = v.findViewById(R.id.tvLista);
            tvDuracion = v.findViewById(R.id.tvDuracion);
        }


    }
}

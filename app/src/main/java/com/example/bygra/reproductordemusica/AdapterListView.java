package com.example.bygra.reproductordemusica;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AdapterListView extends BaseAdapter {

    Context context;
    ArrayList<String> listaNombres;
    ArrayList<MediaPlayer> mpList;
    private AdapterView.OnItemClickListener listener;

    public AdapterListView(Context context,ArrayList<String> listaNombres, ArrayList<MediaPlayer> mpList){
        this.context = context;
        this.listaNombres = listaNombres;
        this.mpList = mpList;
    }

    @Override
    public int getCount() {
        return mpList.size();
    }

    @Override
    public Object getItem(int position) {
        return mpList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (convertView == null){
            LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = li.inflate(R.layout.activity_listview, null);
        }

        TextView tvNombre = v.findViewById(R.id.tvNombre);
        tvNombre.setText(listaNombres.get(position));

        TextView tvDuracion = v.findViewById(R.id.tvDuracion);
        tvDuracion.setText(mpList.get(position).getDuration());

        return v;
    }
}

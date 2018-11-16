package com.example.bygra.reproductordemusica;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class rvAdapter extends RecyclerView.Adapter<rvAdapter.ViewHolder>{

    private Context context;
    private ArrayList<MediaPlayer> mpList = new ArrayList<>();
    private ArrayList<String> nombreList = new ArrayList<>();

    public rvAdapter(Context context, ArrayList<MediaPlayer> mpList, ArrayList<String> nombreList) {
        this.context = context;
        this.mpList = mpList;
        this.nombreList = nombreList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_recycler_view, viewGroup,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        viewHolder.tvNombre.setText(nombreList.get(i));

        long minutes = TimeUnit.MILLISECONDS.toMinutes(mpList.get(i).getDuration());
        long seconds = TimeUnit.MILLISECONDS.toSeconds(mpList.get(i).getDuration());

        while (seconds >= 60){
            seconds -= 60;
        }

        viewHolder.tvDuracion.setText(Long.toString(minutes) + ":" + Long.toString(seconds));

        viewHolder.rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Reproduciendo " + viewHolder.tvNombre.getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mpList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvNombre;
        TextView tvDuracion;
        RelativeLayout rl;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvDuracion = itemView.findViewById(R.id.tvDuracion);
            rl = itemView.findViewById(R.id.lRecycler);
        }
    }
}

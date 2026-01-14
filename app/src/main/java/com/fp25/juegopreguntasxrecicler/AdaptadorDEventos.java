package com.fp25.juegopreguntasxrecicler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdaptadorDEventos extends RecyclerView.Adapter<AdaptadorDEventos.SostenedordeVistas> {

    Context contexto;

    ArrayList<EventModel> eventos;

    public AdaptadorDEventos(Context contexto, ArrayList<EventModel> eventos) {
        this.contexto = contexto;
        this.eventos = eventos;
    }


    @NonNull
    @Override
    public AdaptadorDEventos.SostenedordeVistas onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(contexto);
        View view =inflater.inflate(R.layout.cv_row,parent,false);
        return new AdaptadorDEventos.SostenedordeVistas(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorDEventos.SostenedordeVistas sostenedor, int position) {

        sostenedor.tvName.setText(eventos.get(position).getEventName());
        sostenedor.tvLocate.setText(eventos.get(position).getEventLocation());
    }

    @Override
    public int getItemCount() {
        return eventos.size();
    }


    public static class SostenedordeVistas extends RecyclerView.ViewHolder{

        TextView tvName,tvLocate;

        public SostenedordeVistas(@NonNull View itemView) {
            super(itemView);
            tvName=itemView.findViewById(R.id.tvEvent);
            tvLocate=itemView.findViewById(R.id.tvLocation);
        }
    }

}
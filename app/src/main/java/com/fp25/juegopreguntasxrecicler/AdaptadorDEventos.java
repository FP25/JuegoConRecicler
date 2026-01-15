package com.fp25.juegopreguntasxrecicler;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class AdaptadorDEventos extends RecyclerView.Adapter<AdaptadorDEventos.SostenedordeVistas> {

    Context contexto;

    ArrayList<EventModel> eventos;

    ArrayList<Integer> borrados=new ArrayList<>();



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
        sostenedor.tvDate.setText(eventos.get(position).getEventDate());

    }

    @Override
    public int getItemCount() {
        return eventos.size();
    }

    public class SostenedordeVistas extends RecyclerView.ViewHolder{

        TextView tvName,tvDate,tvLocate;
        CardView tarjeta;



        public SostenedordeVistas(@NonNull View itemView) {
            super(itemView);
            tvName=itemView.findViewById(R.id.tvEvent);
            tvLocate=itemView.findViewById(R.id.tvLocation);
            tvDate=itemView.findViewById(R.id.tvFecha);

            tarjeta=itemView.findViewById(R.id.tajetaDPregunta);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder alert = new AlertDialog.Builder(itemView.getContext());
                    LayoutInflater inflador= (LayoutInflater) itemView.getContext().getSystemService(itemView.getContext().LAYOUT_INFLATER_SERVICE); // me salia de sugerencia y probe y me gusto no se si este muy bn que digamos
                    View dialogCustom = inflador.inflate(R.layout.dialog_custom, null);
                    alert.setTitle(tvName.getText());
                    alert.setMessage("En que año surgio? ");
                    alert.setCancelable(false);
                    TextInputLayout respuestaLY = dialogCustom.findViewById(R.id.dialog_Txt);


                    alert.setPositiveButton(
                            "Verificar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String respuesta="";

                                    if (respuestaLY.getEditText() != null){
                                        respuesta = respuestaLY.getEditText().getText().toString();
                                    }

                                    if (tvDate.getText().equals(respuesta)) {
                                        Toast.makeText(itemView.getContext(), "la buena mano", Toast.LENGTH_SHORT).show();
                                        eventos.remove(getAbsoluteAdapterPosition());
                                        notifyItemRemoved(getAbsoluteAdapterPosition());
                                        notifyItemRangeChanged(getAbsoluteAdapterPosition(), getItemCount()-getAbsoluteAdapterPosition());
                                    }
                                    else {
                                        Toast.makeText(itemView.getContext(), "la mala mano", Toast.LENGTH_SHORT).show();
                                        tarjeta.setCardBackgroundColor(itemView.getContext().getResources().getColor(R.color.cafe_oscuro));
                                    }
                                }
                            });
                    alert.setView(dialogCustom);
                    AlertDialog alertDialog=alert.create();
                    alertDialog.show();
                }
            });
        }
    }

}
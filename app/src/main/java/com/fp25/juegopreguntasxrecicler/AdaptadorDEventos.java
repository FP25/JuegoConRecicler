package com.fp25.juegopreguntasxrecicler;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputLayout;
import java.util.ArrayList;

public class AdaptadorDEventos extends RecyclerView.Adapter<AdaptadorDEventos.SostenedordeVistas> {

    Context contexto;

    ArrayList<EventModel> eventos;

    ArrayList<Integer> pintaos=new ArrayList<>();



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


        Log.d("elementos; ",pintaos.toString());

        if (pintaos.contains(position)){
            sostenedor.tarjeta.setCardBackgroundColor(sostenedor.itemView.getContext().getResources().getColor(R.color.cafe_oscuro));
        }
        else {
            sostenedor.tarjeta.setCardBackgroundColor(sostenedor.itemView.getContext().getResources().getColor(R.color.naranja_cremoso));
        }

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
                    LayoutInflater inflador= (LayoutInflater) itemView.getContext().getSystemService(itemView.getContext().LAYOUT_INFLATER_SERVICE); // me salia de sugerencia y probe y me sirvio no se si este muy bn que digamos
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
                                        Toast.makeText(itemView.getContext(), "Buena esa", Toast.LENGTH_SHORT).show();
                                        Animation anim= AnimationUtils.loadAnimation(itemView.getContext(), android.R.anim.slide_out_right);
                                        anim.setDuration(500);
                                        itemView.startAnimation(anim);
                                        for (int i = 0; i <pintaos.size(); i++) {
                                            if(pintaos.get(i)==getAbsoluteAdapterPosition()){
                                                tarjeta.setCardBackgroundColor(itemView.getContext().getResources().getColor(R.color.naranja_cremoso));
                                                pintaos.remove(pintaos.get(i));
                                            }
                                        }
                                        eventos.remove(getAbsoluteAdapterPosition());
                                        notifyItemRemoved(getAbsoluteAdapterPosition());
                                        notifyItemRangeChanged(getAbsoluteAdapterPosition(), getItemCount()-getAbsoluteAdapterPosition());

                                    }
                                    else {
                                        Toast.makeText(itemView.getContext(), "Respuesta incorrecta", Toast.LENGTH_SHORT).show();
                                        tarjeta.setCardBackgroundColor(itemView.getContext().getResources().getColor(R.color.cafe_oscuro));
                                        pintaos.add(getAbsoluteAdapterPosition());
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
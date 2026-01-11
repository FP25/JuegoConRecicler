package com.fp25.juegopreguntasxrecicler;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<EventModel>eventosHistoricos=new ArrayList<>();
    String respuesta="";
    int cont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        RecyclerView rview=findViewById(R.id.eventsRecicler);

        AdaptadorDEventos adapter=new AdaptadorDEventos(this,eventosHistoricos);
        rview.setAdapter(adapter);
        rview.setLayoutManager(new LinearLayoutManager(this));

        juegoPreguntados();

    }

    public void juegoPreguntados() {
        String[] eventosNombres = getResources().getStringArray(R.array.historic_event_names);
        String[] eventosFechas = getResources().getStringArray(R.array.historic_event_dates);

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        for (int i = 0; i <eventosNombres.length; i++){
            cont =i;
            LayoutInflater inflador = getLayoutInflater();
            View dialogCustom = inflador.inflate(R.layout.dialog_custom, null);

            alert.setTitle(eventosNombres[0]);
            alert.setMessage("En que año surgio? ");
            alert.setCancelable(false);
            TextInputLayout respuestaLY = dialogCustom.findViewById(R.id.dialog_Txt);
            alert.setPositiveButton(
                    "Verificar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            respuesta = respuestaLY.getEditText().getText().toString();
                            if (respuesta.equals(eventosFechas[cont])){
                                Log.d("","coorrectogggggggg");
                                cont=eventosNombres.length-1;
                            }
                        }
                    });
            alert.setView(dialogCustom);
            alert.show();

        }
    }
}
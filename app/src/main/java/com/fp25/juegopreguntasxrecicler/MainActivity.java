package com.fp25.juegopreguntasxrecicler;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

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
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ArrayList<EventModel>eventosHistoricos=new ArrayList<>();

    String[] eventsFechas;
    String[] eventsNames;

    String respuesta="";

    boolean seguimo=true;


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

        RecyclerView rview = findViewById(R.id.eventsRecicler);
        llenaModelos();
        AdaptadorDEventos adapter = new AdaptadorDEventos(this, eventosHistoricos);
        rview.setAdapter(adapter);
        rview.setLayoutManager(new LinearLayoutManager(this));

        AlertDialog alerta;

        Random ran=new Random();

        int numRan=ran.nextInt(eventsFechas.length);
        alerta=juegoPreguntados(numRan);
        alerta.show();


    }

    private void llenaModelos(){
        String[] eventosNombres= getResources().getStringArray(R.array.historic_event_names);
        String[] eventosFechas= getResources().getStringArray(R.array.historic_event_dates);
        String[] eventosLocation= getResources().getStringArray(R.array.historic_event_locations);
        for (int i = 0; i <eventosNombres.length; i++) {
            if (i>0){
                eventosHistoricos.add(new EventModel(eventosNombres[i],eventosFechas[i],eventosLocation[i]));
            }
        }
        eventsNames=eventosNombres;
        eventsFechas=eventosFechas;
    }

    public AlertDialog juegoPreguntados(int i) {

        seguimo=true;

        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        AlertDialog alDialog;

        LayoutInflater inflador = getLayoutInflater();
        View dialogCustom = inflador.inflate(R.layout.dialog_custom, null);


        alert.setTitle(eventsNames[i]);
        alert.setMessage("En que año surgio? ");
        alert.setCancelable(false);
        TextInputLayout respuestaLY = dialogCustom.findViewById(R.id.dialog_Txt);
        alert.setPositiveButton(
                "Verificar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        assert respuestaLY.getEditText() != null;
                        respuesta = respuestaLY.getEditText().getText().toString();

                        if (eventsFechas[i].equals(respuesta)) {
                            Log.i("fdd", "Correcta respuesta " + respuesta);
                            seguimo=false;
                        }
                    }
                });
        alert.setView(dialogCustom);
        alDialog=alert.create();
        if (seguimo){
            Random ran=new Random();
            int numRan2=ran.nextInt(eventsFechas.length);
            alDialog=juegoPreguntados(numRan2);
            alDialog.show();
        }

        return alDialog;
    }
}
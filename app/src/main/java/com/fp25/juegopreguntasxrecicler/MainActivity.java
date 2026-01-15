package com.fp25.juegopreguntasxrecicler;

import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<EventModel>eventosHistoricos=new ArrayList<>();

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
    }
}
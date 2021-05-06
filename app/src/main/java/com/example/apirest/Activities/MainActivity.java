package com.example.apirest.Activities;

import android.content.Intent;
import android.os.Bundle;

import com.example.apirest.Model.Persona;
import com.example.apirest.Utils.PersonaAdapter;
import com.example.apirest.R;
import com.example.apirest.Http.Apis;
import com.example.apirest.Utils.PersonaService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    PersonaService personaService;
    List<Persona>listPersona=new ArrayList<>();
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        listView=(ListView)findViewById(R.id.listView);

        listPersons();

        FloatingActionButton fab = findViewById(R.id.fabe);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent=new Intent(MainActivity.this, AgregarPersona.class);
               intent.putExtra("ID","");
                intent.putExtra("NOMBRES","");
                intent.putExtra("APPATERNO","");
                intent.putExtra("APMATERNO","");
                intent.putExtra("CALLE","");
                intent.putExtra("COLONIA","");
                intent.putExtra("NUMERO","");
                intent.putExtra("CP","");
                intent.putExtra("TELEFONO","");
                intent.putExtra("RFC","");
                intent.putExtra("STATUSPROSPECTO","");
                intent.putExtra("OBSERVACIONES", "");
               startActivity(intent);
            }
        });

    }

    public void listPersons(){
        personaService= Apis.getPersonaService();
        Call<List<Persona>>call=personaService.getPersonas();
        call.enqueue(new Callback<List<Persona>>() {
            @Override
            public void onResponse(Call<List<Persona>> call, Response<List<Persona>> response) {
                if(response.isSuccessful()) {
                    listPersona = response.body();
                    listView.setAdapter(new PersonaAdapter(MainActivity.this,R.layout.content_main,listPersona));
                }
            }

            @Override
            public void onFailure(Call<List<Persona>> call, Throwable t) {
                Log.e("Error:",t.getMessage());
            }
        });
    }
}

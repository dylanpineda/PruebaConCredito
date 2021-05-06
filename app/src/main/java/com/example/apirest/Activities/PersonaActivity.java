package com.example.apirest.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.example.apirest.Model.Persona;
import com.example.apirest.R;
import com.example.apirest.Http.Apis;
import com.example.apirest.Utils.PersonaService;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersonaActivity extends AppCompatActivity {
    PersonaService service;
    EditText et;
    Button bt;
    int flag=0;
    String id, nom, apeP, apeM, strt, colo, num, codpost, telef, rfic, observ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.persona_layout);
        et=(EditText)findViewById(R.id.txtDocumentos);
        bt=(Button)findViewById(R.id.button_add);
        final Spinner spinner= findViewById(R.id.status);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.valuesEditarProspecto, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter2);


        EditText txtId=(EditText)findViewById(R.id.txtId);
        final EditText txtNombre=(EditText)findViewById(R.id.txtnombre);
        final EditText txtApPaterno=(EditText)findViewById(R.id.txtApPaterno);
        final EditText txtApMaterno=(EditText)findViewById(R.id.txtApMaterno);
        final EditText txtCalle=(EditText)findViewById(R.id.txtCalle);
        final EditText txtColonia=(EditText)findViewById(R.id.txtColonia);
        final EditText txtNumero=(EditText)findViewById(R.id.txtNumero);
        final EditText txtCp=(EditText)findViewById(R.id.txtCp);
        final EditText txtTelefono=(EditText)findViewById(R.id.txtTelefono);
        final EditText txtRfc=(EditText)findViewById(R.id.txtRfc);
        final EditText txtObservaciones=(EditText)findViewById(R.id.txtObservaciones);

        Bundle bundle=getIntent().getExtras();
        id = bundle.getString("ID");
        nom=bundle.getString("NOMBRE");
        apeP=bundle.getString("APPATERNO");
        apeM=bundle.getString("APMATERNO");
        strt=bundle.getString("CALLE");
        colo=bundle.getString("COLONIA");
        num=bundle.getString("NUMERO");
        codpost=bundle.getString("CP");
        telef=bundle.getString("TELEFONO");
        rfic=bundle.getString("RFC");
        observ=bundle.getString("OBSERVACIONES");


        Button btnSave=(Button)findViewById(R.id.btnSave);
        Button btnVolver=(Button)findViewById(R.id.btnVolver);
        Button btnEliminar=(Button)findViewById(R.id.btnEliminar);

        txtId.setText(id);
        txtNombre.setText(nom);
        txtApPaterno.setText(apeP);
        txtApMaterno.setText(apeM);
        txtCalle.setText(strt);
        txtColonia.setText(colo);
        txtNumero.setText(num);
        txtCp.setText(codpost);
        txtTelefono.setText(telef);
        txtRfc.setText(rfic);
        txtObservaciones.setText(observ);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Persona p=new Persona();
                p.setNombre(txtNombre.getText().toString());
                p.setApPaterno(txtApPaterno.getText().toString());
                p.setApMaterno(txtApMaterno.getText().toString());
                p.setCalle(txtCalle.getText().toString());
                p.setColonia(txtColonia.getText().toString());
                p.setNumero(txtNumero.getText().toString());
                p.setCp(txtCp.getText().toString());
                p.setTelefono(txtTelefono.getText().toString());
                p.setRfc(txtRfc.getText().toString());
                p.setStatusProspecto(spinner.getSelectedItem().toString());
                p.setObservaciones(txtObservaciones.getText().toString());
                updatePersona(p,Integer.valueOf(id));
            }
        });
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(PersonaActivity.this, VerDocumentos.class);
                intent.putExtra("ID",id );
                intent.putExtra("NOMBRE",nom);
                intent.putExtra("APPATERNO",apeP);
                intent.putExtra("APMATERNO",apeM);
                intent.putExtra("CALLE",strt);
                intent.putExtra("COLONIA",colo);
                intent.putExtra("NUMERO",num);
                intent.putExtra("CP",codpost);
                intent.putExtra("TELEFONO",telef);
                intent.putExtra("RFC",rfic);
                intent.putExtra("OBSERVACIONES", observ);
                startActivity(intent);

            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletePersona(Integer.valueOf(id));
                Intent intent =new Intent(PersonaActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("ALGO");
                new AlertDialog.Builder(PersonaActivity.this)
                        .setIcon(android.R.drawable.ic_delete)
                        .setTitle("¿Salir?")
                        .setMessage("Se borrarán los datos no guardados si regresa a la pantalla anterior")
                        .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent =new Intent(PersonaActivity.this,MainActivity.class);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
            }
        });

    }
    public void updatePersona(Persona p, int id){
        service= Apis.getPersonaService();
        Call<Persona>call=service.updatePersona(p,id);

        call.enqueue(new Callback<Persona>() {
            @Override
            public void onResponse(Call<Persona> call, Response<Persona> response) {
                if(response.isSuccessful()){
                    Toast.makeText(PersonaActivity.this,"Se Actualizó conéxito",Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<Persona> call, Throwable t) {
                Log.e("Error:",t.getMessage());
            }
        });
        Intent intent =new Intent(PersonaActivity.this,MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(PersonaActivity.this)
                .setIcon(android.R.drawable.ic_delete)
                .setTitle("¿Salir?")
                .setMessage("Se borrarán los datos si regresa a la pantalla anterior")
                .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent =new Intent(PersonaActivity.this,MainActivity.class);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    public void deletePersona(int id){
        service=Apis.getPersonaService();
        Call<String>call=service.deletePersona(id);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String json=response.body();
                try {
                    JSONObject obj = new JSONObject(json);
                    flag=obj.getInt("value");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (flag==0){
                    Toast.makeText(PersonaActivity.this,"No se elimino el prospecto, ERROR",Toast.LENGTH_LONG).show();
                }else if(flag==1){
                    Toast.makeText(PersonaActivity.this,"Se elimino con éxito",Toast.LENGTH_LONG).show();
                    Intent intent =new Intent(PersonaActivity.this,MainActivity.class);
                    startActivity(intent);
                }
                if(response.isSuccessful()){
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("Error:",t.getMessage());
            }
        });

    }
}

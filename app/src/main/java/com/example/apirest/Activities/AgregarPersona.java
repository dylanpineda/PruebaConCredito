package com.example.apirest.Activities;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.apirest.Http.Apis;
import com.example.apirest.Model.Documento;
import com.example.apirest.Model.Persona;
import com.example.apirest.R;
import com.example.apirest.Utils.PersonaService;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Result;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AgregarPersona extends AppCompatActivity {
    PersonaService service;

    EditText et;
    ListView lv;
    Button btnFile;
    int flag=0;
    Button btnFile3;
    int numeroclick=1;
    int REQUEST_CAMERA=1, SELECT_FILE=0;
    ArrayList<String> documentos;
    ArrayList<Documento> documentoList = new ArrayList<Documento>();
    byte[] byteArray;
    String documentData;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agregar_persona);
        final Spinner spinner= findViewById(R.id.status);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.valuesAgregarProspecto, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter2);

        try{
            service= Apis.getPersonaService();
            Call<String> call=service.getMax();
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
                }
                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Log.e("Error:",t.getMessage());
                }
            });
        }catch (Exception e){
            System.out.println(e.toString());
            flag=1;
        }

        et=(EditText)findViewById(R.id.txtDocumentos);
        lv=(ListView)findViewById(R.id.listViewId);
        btnFile=(Button)findViewById(R.id.button_add);
        btnFile3=(Button)findViewById(R.id.button_add3);
        documentos=new ArrayList<String>();
        adapter= new ArrayAdapter<String>(AgregarPersona.this,
                android.R.layout.simple_list_item_1,
                documentos);
        lv.setAdapter(adapter);
        btnFile3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
        btnFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ASIGNAR A MODELO DOCUMENTO EL NOMBRE DE LA LISTA Y LA FOTO
                Documento doc= new Documento();
                if(et.getText().toString().isEmpty()){
                    Toast.makeText(AgregarPersona.this,"Falta nombre documento",
                            Toast.LENGTH_LONG).show();
                }else{
                    doc.setNombre(et.getText().toString());
                    if(byteArray==null){
                        Toast.makeText(AgregarPersona.this,"Falta documento",
                                Toast.LENGTH_LONG).show();
                    }else{
                        doc.setDocumento(documentData);
                        //AGREGAR A LA LISTA
                        RelativeLayout.LayoutParams mParam = new RelativeLayout.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT, (150*numeroclick));
                        documentos.add(doc.getNombre());
                        adapter.notifyDataSetChanged();
                        lv.setLayoutParams(mParam);
                        numeroclick=numeroclick+1;

                        doc.setId(flag+1);
                        documentoList.add(doc);
                        byteArray=null;
                    }
                }
            }
        });
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view,
                                           final int position, long id) {
                new AlertDialog.Builder(AgregarPersona.this)
                        .setIcon(android.R.drawable.ic_delete)
                        .setTitle("Borrar documento?")
                        .setMessage("Se borrará el documento y no podrá recuperase")
                        .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                documentos.remove(position);
                                RelativeLayout.LayoutParams mParam = new RelativeLayout.LayoutParams(
                                        ViewGroup.LayoutParams.MATCH_PARENT, lv.getHeight()-150);
                                numeroclick=numeroclick-1;
                                lv.setLayoutParams(mParam);
                                adapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
                return true;
            }
        });


        final EditText txtNombre=(EditText)findViewById(R.id.txtnombre);
        final EditText txtApPaterno=(EditText)findViewById(R.id.txtApPaterno);
        final EditText txtApMaterno=(EditText)findViewById(R.id.txtApMaterno);
        final EditText txtCalle=(EditText)findViewById(R.id.txtCalle);
        final EditText txtColonia=(EditText)findViewById(R.id.txtColonia);
        final EditText txtNumero=(EditText)findViewById(R.id.txtNumero);
        final EditText txtCp=(EditText)findViewById(R.id.txtCp);
        final EditText txtTelefono=(EditText)findViewById(R.id.txtTelefono);
        final EditText txtRfc=(EditText)findViewById(R.id.txtRfc);



        Button btnSave=(Button)findViewById(R.id.btnSave);
        Button btnVolver=(Button)findViewById(R.id.btnVolver);



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
                Documento d= new Documento();
                d.setDocumento(documentData);
                d.setNombre(et.getText().toString());
                addPersona(p);
            }
        });

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(AgregarPersona.this)
                        .setIcon(android.R.drawable.ic_delete)
                        .setTitle("¿Salir?")
                        .setMessage("Se borrarán los datos no guardados si regresa a la pantalla anterior")
                        .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent =new Intent(AgregarPersona.this,MainActivity.class);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();

            }
        });

    }
    private void  selectImage(){
        final CharSequence[] items = {"CÁMARA", "GALERÍA", "CANCELAR"};
        AlertDialog.Builder builder= new AlertDialog.Builder(AgregarPersona.this);
        builder.setTitle("Agregar imagen");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(items[i].equals("CÁMARA")){
                    Intent intent= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, REQUEST_CAMERA);
                }else if(items[i].equals("GALERÍA")){
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                    startActivityForResult(intent.createChooser(intent,"Seleccionar imagen"), SELECT_FILE);
                }else if(items[i].equals("CANCELAR")){
                    dialogInterface.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode== Activity.RESULT_OK){
            if(requestCode==REQUEST_CAMERA){
                Bundle bundle = data.getExtras();
                final Bitmap bmp = (Bitmap) bundle.get("data");
                ByteArrayOutputStream bos= new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.PNG, 100, bos);
                byteArray= bos.toByteArray();
                documentData=Base64.encodeToString(byteArray, Base64.DEFAULT);
            }else if(requestCode==SELECT_FILE){
                Uri selectedImageUri = data.getData();
                try {
                    InputStream stream = getContentResolver().openInputStream(selectedImageUri);
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    int bufferSize = 1024;
                    byte[] buffer = new byte[bufferSize];
                    int len = 0;
                    while ((len = stream.read(buffer)) != -1) {
                        bos.write(buffer, 0, len);
                    }
                    byteArray = bos.toByteArray();
                    documentData= Base64.encodeToString(byteArray, Base64.DEFAULT);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(AgregarPersona.this)
                .setIcon(android.R.drawable.ic_delete)
                .setTitle("¿Salir?")
                .setMessage("Se borrarán los datos no guardados si regresa a la pantalla anterior")
                .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent =new Intent(AgregarPersona.this,MainActivity.class);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("No", null)
                .show();

    }
    public void addPersona(@NotNull Persona p){

        if(!p.getNombre().isEmpty()){
            if(!p.getApPaterno().isEmpty()){
                if(!p.getCalle().isEmpty()){
                    if(!p.getColonia().isEmpty()){
                        if(!p.getNumero().isEmpty()){
                            if(!p.getCp().isEmpty()){
                                if(!p.getTelefono().isEmpty()){
                                    if (!p.getRfc().isEmpty()) {
                                        service= Apis.getPersonaService();
                                        Call<String> call=service.addPersona(p);
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
                                                    Toast.makeText(AgregarPersona.this,"No se agrego, ERROR",Toast.LENGTH_LONG).show();
                                                }else if(flag==1){

                                                    for(int x=0; x<documentoList.size(); x++){
                                                        Documento documento;
                                                        documento=documentoList.get(x);
                                                        service = Apis.getPersonaService();
                                                        Call<String> call1=service.setDocumento(documento);
                                                        call1.enqueue(new Callback<String>() {
                                                            @Override
                                                            public void onResponse(Call<String> call, Response<String> response) {
//                                                                System.out.println(response.toString());
//                                                                String json=response.body();
//                                                                try {
//                                                                    JSONObject obj = new JSONObject(json);
//                                                                    flag=obj.getInt("value");
//                                                                } catch (JSONException e) {
//                                                                    e.printStackTrace();
//                                                                }
//                                                                if (flag==0){
//                                                                    Toast.makeText(AgregarPersona.this,"No se agregaron los documentos, ERROR",Toast.LENGTH_LONG).show();
//                                                                }else if(flag==1){
//                                                                    Toast.makeText(AgregarPersona.this,"Se agrego con éxito",Toast.LENGTH_LONG).show();
//                                                                }
                                                            }
                                                            @Override
                                                            public void onFailure(Call<String> call, Throwable t) {
                                                            }
                                                        });
                                                    }
                                                }
                                            }
                                            @Override
                                            public void onFailure(Call<String> call, Throwable t) {
                                                Log.e("Error:",t.getMessage());
                                            }
                                        });
                                    }
                                    else{
                                        Toast.makeText(AgregarPersona.this,"Falta RFC",Toast.LENGTH_LONG).show();
                                    }
                                }else{
                                    Toast.makeText(AgregarPersona.this,"Falta Telefono",Toast.LENGTH_LONG).show();
                                }
                            }else{
                                Toast.makeText(AgregarPersona.this,"Falta CP",Toast.LENGTH_LONG).show();
                            }
                        }else{
                            Toast.makeText(AgregarPersona.this,"Falta numero de casa",Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(AgregarPersona.this,"Falta Colonia",Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(AgregarPersona.this,"Falta calle",Toast.LENGTH_LONG).show();
                }
            }else{
                Toast.makeText(AgregarPersona.this,"Falta apelido paterno",Toast.LENGTH_LONG).show();
            }
        }else{

            Toast.makeText(AgregarPersona.this,"Falta nombre",Toast.LENGTH_LONG).show();
        }
    }
}

package com.example.apirest.Activities;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
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
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.apirest.Http.Apis;
import com.example.apirest.Model.Documento;
import com.example.apirest.Model.Persona;
import com.example.apirest.R;
import com.example.apirest.Utils.DocumentoAdapter;
import com.example.apirest.Utils.PersonaAdapter;
import com.example.apirest.Utils.PersonaService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerDocumentos extends AppCompatActivity {
    PersonaService personaService;
    List<Documento> listDocumentos=new ArrayList<>();
    ListView listView;
    Documento documento;
    String nomDocumento;
    int REQUEST_CAMERA=1, SELECT_FILE=0;
    byte[] byteArray;
    int idEliminar;
    String documentData;
    Button btnAgregarALista, selectDocumento;
    EditText nombreDocumento;
    String id, nom, apeP, apeM, strt, colo, num, codpost, telef, rfic, observ;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ver_documentos);
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

        selectDocumento=(Button)findViewById(R.id.agregar_documento);
        selectDocumento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();
            }
        });



        nombreDocumento=(EditText)findViewById(R.id.nombre_documento);
        btnAgregarALista=(Button)findViewById(R.id.agregar_documento_a_lista);
        btnAgregarALista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                documento=new Documento();
                documento.setId(Integer.parseInt(id));
                nomDocumento=nombreDocumento.getText().toString();
                if(nomDocumento.isEmpty()){
                    Toast.makeText(VerDocumentos.this,"Falta nombre documento",
                            Toast.LENGTH_LONG).show();
                }else{
                    documento.setNombre(nomDocumento);
                    if(byteArray==null){
                        Toast.makeText(VerDocumentos.this,"Falta documento",
                                Toast.LENGTH_LONG).show();
                    }else{
                        documento.setDocumento(documentData);
                        //AGREGAR A LA LISTA
                        RelativeLayout.LayoutParams mParam = new RelativeLayout.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT, ((listDocumentos.size()*1200)+1200) );
                        listDocumentos.add(documento);
                        listView.setLayoutParams(mParam);
                        personaService = Apis.getPersonaService();
                        Call<String> call1=personaService.setDocumento(documento);
                        call1.enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                            }
                            @Override
                            public void onFailure(Call<String> call, Throwable t) {
                            }
                        });
                        byteArray=null;
                    }
                }

            }
        });
        Button bt;
        bt=(Button)findViewById(R.id.volver);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //REGRESAR A LA PANTALLA ANTERIOR Y GUARDAR TODA LA INFORMACION PARA NO HACER OTRA PETICION
                Intent intent=new Intent(VerDocumentos.this, PersonaActivity.class);
                intent.putExtra("ID",id);
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

        listView=(ListView)findViewById(R.id.listView);

        listDocs();

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(final AdapterView<?> adapterView, View view,
                                           final int position, long id) {
                new AlertDialog.Builder(VerDocumentos.this)
                        .setIcon(android.R.drawable.ic_delete)
                        .setTitle("Borrar documento?")
                        .setMessage("Se borrará el documento y no podrá recuperase")
                        .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Documento docEliminar= new Documento();
                                docEliminar=listDocumentos.get(position);
                                idEliminar=docEliminar.getIdDocumento();
                                System.out.println(idEliminar);

                                personaService = Apis.getPersonaService();
                                Call<String> call1=personaService.deleteDoc(idEliminar);
                                call1.enqueue(new Callback<String>() {
                                    @Override
                                    public void onResponse(Call<String> call, Response<String> response) {
                                        listDocumentos.remove(position);
                                        RelativeLayout.LayoutParams mParam = new RelativeLayout.LayoutParams(
                                                ViewGroup.LayoutParams.MATCH_PARENT, listView.getHeight()-1200);

                                        listView.setLayoutParams(mParam);
                                    }
                                    @Override
                                    public void onFailure(Call<String> call, Throwable t) {
                                    }
                                });
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {

    }

    public void listDocs(){
        personaService= Apis.getPersonaService();
        Call<List<Documento>> call=personaService.getDocs(Integer.parseInt(id));
        call.enqueue(new Callback<List<Documento>>() {
            @Override
            public void onResponse(Call<List<Documento>> call, Response<List<Documento>> response) {
                listDocumentos = response.body();
                RelativeLayout.LayoutParams mParam = new RelativeLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, (1200*listDocumentos.size()));
                listView.setAdapter(new DocumentoAdapter(VerDocumentos.this,R.layout.content_ver_docs,listDocumentos));
                listView.setLayoutParams(mParam);
            }

            @Override
            public void onFailure(Call<List<Documento>> call, Throwable t) {
                Log.e("Error:",t.getMessage());
            }
        });
    }
    private void  selectImage(){
        final CharSequence[] items = {"CÁMARA", "GALERÍA", "CANCELAR"};
        AlertDialog.Builder builder= new AlertDialog.Builder(VerDocumentos.this);
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
                documentData= Base64.encodeToString(byteArray, Base64.DEFAULT);
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
}

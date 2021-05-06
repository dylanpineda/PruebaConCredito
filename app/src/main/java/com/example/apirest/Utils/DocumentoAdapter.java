package com.example.apirest.Utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.apirest.Activities.PersonaActivity;
import com.example.apirest.Model.Documento;
import com.example.apirest.Model.Persona;
import com.example.apirest.R;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class DocumentoAdapter extends ArrayAdapter<Documento>{

    private Context context;
    private List<Documento> documentos;

    public DocumentoAdapter(@NonNull Context context, int resource, @NonNull List<Documento> objects) {
        super(context, resource, objects);
        this.context=context;
        this.documentos=objects;
    }
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView=layoutInflater.inflate(R.layout.content_ver_docs,parent,false);



        try{
            final String base= documentos.get(position).getDocumento();
            byte[] byteArrray = Base64.decode(base,0);
            Bitmap bmp = BitmapFactory.decodeByteArray(byteArrray, 0 , byteArrray.length);


            ImageView imageView = (ImageView)rowView.findViewById(R.id.documento);
            TextView txtidPersona=(TextView)rowView.findViewById(R.id.IdPersona);
            TextView txtNombre=(TextView)rowView.findViewById(R.id.Nombre);
            imageView.setImageBitmap(bmp);


            txtidPersona.setText(String.format("ID:%d",documentos.get(position).getId()));
            txtNombre.setText(String.format("NOMBRE:%s",documentos.get(position).getNombre()));
        }catch (Exception e){
            System.out.println(e.toString());
        }

       // Bitmap bmp = BitmapFactory.decodeByteArray(byteArrray, 0,byteArrray.length);

        return rowView;
    }
}

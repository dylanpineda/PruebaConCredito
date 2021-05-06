package com.example.apirest.Utils;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.apirest.Activities.PersonaActivity;
import com.example.apirest.Model.Persona;
import com.example.apirest.R;

import java.util.List;

public class PersonaAdapter extends ArrayAdapter<Persona> {

    private Context context;
    private  List<Persona>personas;

    public PersonaAdapter(@NonNull Context context, int resource, @NonNull List<Persona> objects) {
        super(context, resource, objects);
        this.context=context;
        this.personas=objects;
    }

    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView=layoutInflater.inflate(R.layout.content_main,parent,false);

        TextView txtidPersona=(TextView)rowView.findViewById(R.id.IdPersona);
        TextView txtNombre=(TextView)rowView.findViewById(R.id.Nombre);;
        TextView txtApPaterno=(TextView)rowView.findViewById(R.id.apPaterno);
        TextView txtApMaterno=(TextView)rowView.findViewById(R.id.apMaterno);
        TextView txtStatusProspecto=(TextView)rowView.findViewById(R.id.statusProspecto);


        txtidPersona.setText(String.format("ID:%d",personas.get(position).getId()));
        txtNombre.setText(String.format("NOMBRE:%s",personas.get(position).getNombre()));
        txtApPaterno.setText(String.format("APELLIDO PATERNO: %s",personas.get(position).getApPaterno()));
        txtApMaterno.setText(String.format("APELLIDO MATERNO: %s",personas.get(position).getApMaterno()));
        txtStatusProspecto.setText(String.format("STATUSPROSPECTO: %s",personas.get(position).getStatusProspecto()));

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent=new Intent(context, PersonaActivity.class);
                intent.putExtra("ID",String.valueOf(personas.get(position).getId()));
                intent.putExtra("NOMBRE",personas.get(position).getNombre());
                intent.putExtra("APPATERNO",personas.get(position).getApPaterno());
                intent.putExtra("APMATERNO",personas.get(position).getApMaterno());
                intent.putExtra("CALLE",personas.get(position).getCalle());
                intent.putExtra("COLONIA",personas.get(position).getColonia());
                intent.putExtra("NUMERO",personas.get(position).getNumero());
                intent.putExtra("CP",personas.get(position).getCp());
                intent.putExtra("TELEFONO",personas.get(position).getTelefono());
                intent.putExtra("RFC",personas.get(position).getRfc());
                intent.putExtra("STATUSPROSPECTO",personas.get(position).getStatusProspecto());
                intent.putExtra("OBSERVACIONES", personas.get(position).getObservaciones());

               context.startActivity(intent);
            }
        });
        return rowView;
    }

}

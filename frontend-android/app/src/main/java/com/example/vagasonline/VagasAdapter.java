package com.example.vagasonline;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class VagasAdapter extends ArrayAdapter<Vagas> {
    private int layout;


    public VagasAdapter(@NonNull Context context, int resource, @NonNull List<Vagas> objects) {
        super(context, resource, objects);
        this.layout=resource;
    }

    @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {   if (convertView == null){
            LayoutInflater inflater = (LayoutInflater)
                    getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(this.layout,parent,false);
        }


            TextView tvRegistro = (TextView)convertView.findViewById(R.id.tvRegistro);
            TextView tvEmpresa = (TextView)convertView.findViewById(R.id.tvEmpresa);
            TextView tvCargo = (TextView)convertView.findViewById(R.id.tvCargo);
            TextView tvLocal = (TextView)convertView.findViewById(R.id.tvLocal);
            TextView tvJornada = (TextView)convertView.findViewById(R.id.tvJornada);
            TextView tvSalario = (TextView)convertView.findViewById(R.id.tvSalario);

            Vagas vagas = this.getItem(position);

            tvRegistro.setText(""+vagas.getRegistro());
            tvEmpresa.setText(vagas.getEmpresa().getNome_fantasia());
            tvCargo.setText(vagas.getCargo());
            tvLocal.setText(vagas.getCidade() + " - " + vagas.getEstado());
            tvJornada.setText(vagas.getJornada_trabalho());
            tvSalario.setText(vagas.getRemuneracao());


            tvRegistro.setText(""+vagas.getRegistro());
            tvCargo.setText(""+vagas.getCargo());
            return convertView;
        }
    }



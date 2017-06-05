package com.example.labsoftware1.personamaterial;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Lab Software 1 on 20/05/2017.
 */

public class AdaptadorPersona extends RecyclerView.Adapter<AdaptadorPersona.PersonaViewHolder> {

    private ArrayList<Apartamento> apartamentos;
    private OnPersonaClickListener clickListener;

    public AdaptadorPersona(ArrayList<Apartamento> apartamentos, OnPersonaClickListener clickListener){
        this.apartamentos = apartamentos;
        this.clickListener=clickListener;
    }

    @Override
    public AdaptadorPersona.PersonaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_persona,parent,false);
        return new PersonaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AdaptadorPersona.PersonaViewHolder holder, int position) {
            final Apartamento p = apartamentos.get(position);
            //holder.foto.setImageResource(Integer.parseInt(p.getUrlfoto()));
        Picasso.with(holder.view.getContext()).load(p.getUrlfoto()).into(holder.foto);

            holder.nomenclatura.setText(p.getNomenclatura());
            holder.precio.setText(p.getPrecio());
            holder.metrocuadrado.setText(p.getMetrocuadrado());

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onPersonaClick(p);
            }
        });
    }

    @Override
    public int getItemCount() {
        return apartamentos.size();
    }

    public static class PersonaViewHolder extends RecyclerView.ViewHolder{
        private ImageView foto;
        private TextView nomenclatura;
        private TextView precio;
        private TextView metrocuadrado;
        private View view;

        public PersonaViewHolder(View itemView) {
            super(itemView);
            view= itemView;
            foto = (ImageView)itemView.findViewById(R.id.fotoPersona);
            nomenclatura = (TextView)itemView.findViewById(R.id.txtNomenclaturaP);
            precio = (TextView) itemView.findViewById(R.id.txtPrecioP);
            metrocuadrado = (TextView)itemView.findViewById(R.id.txtmetroscuadrados);
        }
    }

    public interface OnPersonaClickListener{
        void onPersonaClick(Apartamento p);
    }

}

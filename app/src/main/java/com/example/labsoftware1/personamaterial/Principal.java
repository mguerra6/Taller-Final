package com.example.labsoftware1.personamaterial;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;

public class Principal extends AppCompatActivity implements AdaptadorPersona.OnPersonaClickListener {
    private RecyclerView listado;
    private ArrayList<Apartamento> apartamentos;
    private AdaptadorPersona adapter;
    private LinearLayoutManager llm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        listado = (RecyclerView) findViewById(R.id.lstOpciones);

        apartamentos = Datos.traerPersonas(getApplicationContext());
        adapter = new AdaptadorPersona(apartamentos,this);

        llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        listado.setLayoutManager(llm);
        listado.setAdapter(adapter);

    }

    public void agregar(View v){
        finish();
        Intent i = new Intent(Principal.this, AgregarPersona.class);
        startActivity(i);
    }

    @Override
    public void onPersonaClick(Apartamento p) {
        //finish();
        Intent i = new Intent(Principal.this,DetallePersona.class);
        Bundle b = new Bundle();
        b.putString("nomenclatura",p.getNomenclatura());
        b.putString("precio",p.getPrecio());
        b.putString("urlfoto",p.getUrlfoto());

        i.putExtra("datos",b);
        startActivity(i);
    }
}

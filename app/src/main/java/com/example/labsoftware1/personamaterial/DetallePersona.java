package com.example.labsoftware1.personamaterial;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class DetallePersona extends AppCompatActivity {
   private CollapsingToolbarLayout collapsingToolbarLayout;
    private Apartamento p;
    private String nomenclatura, precio, metrocuadrado, urlfoto;
    private Bundle b;
    private Intent i;
    private ImageView foto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_persona);

        Toolbar toolbar= (Toolbar)findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        i = getIntent();
        b=i.getBundleExtra("datos");
        nomenclatura = b.getString("nomenclatura");
        precio = b.getString("precio");
        urlfoto = b.getString("urlfoto");

        collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar);
        foto = (ImageView)findViewById(R.id.fotoPersona);

        Picasso.with(getApplicationContext()).load(urlfoto).into(foto);
        collapsingToolbarLayout.setTitle(nomenclatura+" "+precio);

    }
}

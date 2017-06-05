package com.example.labsoftware1.personamaterial;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;

import java.io.ByteArrayOutputStream;

public class AgregarPersona extends AppCompatActivity {

    private EditText cajanomenclatura;
    private EditText cajaprecio;
    private EditText cajapiso;
    private EditText cajametrocuadrado;

    private CheckBox chkbalcon;
    private CheckBox chksombra;

    private boolean guardado;

    private TextInputLayout icajanomenclatura;
    private TextInputLayout icajaprecio;
    private TextInputLayout icajapiso;
    private TextInputLayout icajametrocuadrado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_persona);

        cajanomenclatura = (EditText)findViewById(R.id.txtnomenclatura);
        cajaprecio= (EditText)findViewById(R.id.txtprecio);
        cajapiso = (EditText)findViewById(R.id.txtpiso);
        cajametrocuadrado = (EditText)findViewById(R.id.txtmetroscuadrados);

        icajanomenclatura = (TextInputLayout) findViewById(R.id.NomenclaturaPiso);
        icajaprecio = (TextInputLayout)findViewById(R.id.precioApartamento);
        icajapiso = (TextInputLayout)findViewById(R.id.pisoApartamento);
        icajametrocuadrado = (TextInputLayout)findViewById(R.id.metroscuadradosApartamento);

        guardado = false;

        cajanomenclatura.addTextChangedListener(new TextWatcherPersonalizado(icajanomenclatura,getResources().getString(R.string.error_1)) {
            @Override
            public boolean estaVacio(Editable s) {
                if(TextUtils.isEmpty(s) && !guardado) return true;
                else return false;
            }
        });


        cajaprecio.addTextChangedListener(new TextWatcherPersonalizado(icajaprecio,getResources().getString(R.string.error_2)) {
            @Override
            public boolean estaVacio(Editable s) {
                if(TextUtils.isEmpty(s)&& !guardado) return true;
                else return false;
            }
        });


        cajapiso.addTextChangedListener(new TextWatcherPersonalizado(icajapiso,getResources().getString(R.string.error_3)) {
            @Override
            public boolean estaVacio(Editable s) {
                if(TextUtils.isEmpty(s)&& !guardado) return true;
                else return false;
            }
        });


        cajametrocuadrado.addTextChangedListener(new TextWatcherPersonalizado(icajametrocuadrado,getResources().getString(R.string.error_4)) {
            @Override
            public boolean estaVacio(Editable s) {
                if(TextUtils.isEmpty(s)&& !guardado) return true;
                else return false;
            }
        });

    }




    public int fotoAleatoria(){
        int fotos[] = {R.drawable.images,R.drawable.images2,R.drawable.images3};
        int numero = (int)(Math.random() * 3);
        return fotos[numero];
    }
    public void guardar(View v)  {
        String urlfoto, idfoto, nomenclatura, metrocuadrado, precio, piso, balcom = "",sombra = "";
        Apartamento p;

        int foto;

        if(validarTodo()){

            nomenclatura = cajanomenclatura.getText().toString();
            precio = cajaprecio.getText().toString();
            piso = cajapiso.getText().toString();
            metrocuadrado = cajametrocuadrado.getText().toString();

            foto = fotoAleatoria();
            Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(),foto);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG,100,baos);
            byte[] imagenBytes = baos.toByteArray();
            urlfoto = Base64.encodeToString(imagenBytes,Base64.DEFAULT);
            try {
                baos.close();
            }catch (Exception e){

            }

            if(chkbalcon.isChecked()){
                balcom = getResources().getString(R.string.balcon);
            }
            if(chksombra.isChecked()){
                sombra = getResources().getString(R.string.sombra);
            }

            idfoto=String.valueOf(foto);
            p = new Apartamento(urlfoto, idfoto, nomenclatura, precio, piso, metrocuadrado,balcom, sombra);
            p.guardar(getApplicationContext());

            InputMethodManager imp =(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imp.hideSoftInputFromWindow(cajanomenclatura.getWindowToken(),0);
            Snackbar.make(v,getResources().getString(R.string.mensaje_exitoso_guardar),Snackbar.LENGTH_SHORT).show();
            guardado= true;
            limpiar();

        }
    }



    public void limpiar(){
        cajanomenclatura.setText("");
        cajapiso.setText("");
        cajaprecio.setText("");
        cajametrocuadrado.setText("");
        chkbalcon.setChecked(false);
        chksombra.setChecked(false);
        cajanomenclatura.requestFocus();

        guardado = false;
    }

    public void limpiar(View v){
        limpiar();
    }


    public boolean validarNomenclatura() {
        if (cajanomenclatura.getText().toString().isEmpty()) {
            cajanomenclatura.setError(this.getResources().getString(R.string.error_1));
            cajanomenclatura.requestFocus();
            return false;
        }
        return true;
    }


    public void buscar(View v){
        Apartamento p;
        String balcom, sombra;;
        if(validarNomenclatura()) {
            p = Datos.buscarApartamento(getApplicationContext(), cajanomenclatura.getText().toString());
            if(p!=null){
                cajametrocuadrado.setText(p.getMetrocuadrado());
                cajaprecio.setText(p.getPrecio());
                cajapiso.setText(p.getPiso());
                balcom = p.getBalcom();
                if(balcom.contains(getResources().getString(R.string.balcon))) chkbalcon.setChecked(true);
                sombra = p.getSombra();
                if(sombra.contains(getResources().getString(R.string.sombra))) chksombra.setChecked(true);
            }
        }
    }



    public boolean validarTodo(){
        if(cajanomenclatura.getText().toString().isEmpty()){
            icajanomenclatura.setError(getResources().getString(R.string.error_1));
            cajanomenclatura.requestFocus();
            return false;
        }
        if(cajaprecio.getText().toString().isEmpty()){
            icajaprecio.setError(getResources().getString(R.string.error_2));
            cajaprecio.requestFocus();
            return false;
        }
        if(cajapiso.getText().toString().isEmpty()){
            icajapiso.setError(getResources().getString(R.string.error_3));
            cajapiso.requestFocus();
            return false;
        }

        if(cajametrocuadrado.getText().toString().isEmpty()){
            icajametrocuadrado.setError(getResources().getString(R.string.error_4));
            cajametrocuadrado.requestFocus();
            return false;
        }


        return true;
    }

    public void onBackPressed(){
        finish();
        Intent i = new Intent(AgregarPersona.this,Principal.class);
        startActivity(i);
    }
}


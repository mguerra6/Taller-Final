package com.example.labsoftware1.personamaterial;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.UUID;

/**
 * Created by Lab Software 1 on 20/05/2017.
 */

public class Apartamento {

    private String uuid;
    private String urlfoto;
    private String idfoto;
    private String nomenclatura;
    private String metrocuadrado;
    private String precio;
    private String piso;
    private String balcom;
    private String sombra;


    public Apartamento(String urlfoto, String nomenclatura, String precio, String piso, String metrocuadrado, String idfoto){

    }

    public Apartamento(String urlfoto, String idfoto, String nomenclatura, String metrocuadrado, String precio, String piso, String balcom, String sombra) {
        this.uuid = UUID.randomUUID().toString();
        this.urlfoto = urlfoto;
        this.idfoto = idfoto;
        this.nomenclatura = nomenclatura;
        this.metrocuadrado = metrocuadrado;
        this.precio = precio;
        this.piso = piso;
        this.balcom = balcom;
        this.sombra = sombra;
    }

    public Apartamento(String uuid, String urlfoto, String idfoto, String nomenclatura, String metrocuadrado, String precio, String piso, String balcom, String sombra) {
        this.uuid = uuid;
        this.urlfoto = urlfoto;
        this.idfoto = idfoto;
        this.nomenclatura = nomenclatura;
        this.metrocuadrado = metrocuadrado;
        this.precio = precio;
        this.piso = piso;
        this.balcom = balcom;
        this.sombra = sombra;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUrlfoto() {
        return urlfoto;
    }

    public void setUrlfoto(String urlfoto) {
        this.urlfoto = urlfoto;
    }

    public String getIdfoto() {
        return idfoto;
    }

    public void setIdfoto(String idfoto) {
        this.idfoto = idfoto;
    }

    public String getNomenclatura() {
        return nomenclatura;
    }

    public void setNomenclatura(String nomenclatura) {
        this.nomenclatura = nomenclatura;
    }

    public String getMetrocuadrado() {
        return metrocuadrado;
    }

    public void setMetrocuadrado(String metrocuadrado) {
        this.metrocuadrado = metrocuadrado;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getPiso() {
        return piso;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public String getBalcom() {
        return balcom;
    }

    public void setBalcom(String balcom) {
        this.balcom = balcom;
    }

    public String getSombra() {
        return sombra;
    }

    public void setSombra(String sombra) {
        this.sombra = sombra;
    }

    public void guardar(Context contexto){
        //declarar las variables
        SQLiteDatabase db;
        String sql;

        //Abrir la conexion de base datos en modo escritura
        PersonasSQLiteOpenHelper  aux = new PersonasSQLiteOpenHelper(contexto,"DBApartamentos",null,4);
        db = aux.getWritableDatabase();

        //insertar forma 1
        sql = "INSERT INTO Apartamentos values('"
                +this.getUuid()+"','"
                +this.getUrlfoto()+"','"
                +this.getIdfoto()+"','"
                +this.getNomenclatura()+"','"
                +this.getMetrocuadrado()+"','"
                +this.getPrecio()+"','"
                +this.getPiso()+"','"
                +this.getBalcom()+"','"
                +this.getSombra()+"')";


        db.execSQL(sql);

        //insert forma 2

      /*  ContentValues nuevoRegistro = new ContentValues();
        nuevoRegistro.put("foto",this.getFoto());
        nuevoRegistro.put("cedula",this.getCedula());
        nuevoRegistro.put("nombre",this.getNombre());
        nuevoRegistro.put("apellido",this.getApellido());
        nuevoRegistro.put("sexo",this.getSexo());
        nuevoRegistro.put("pasatiempo",this.getPasatiempo());

        db.insert("Personas",null,nuevoRegistro);
*/
        //cerrar conexion
        db.close();

    }

    public void eliminar(Context contexto){
        //declarar las variables
        SQLiteDatabase db;
        String sql;

        //Abrir la conexion de base datos en modo escritura
        PersonasSQLiteOpenHelper  aux = new PersonasSQLiteOpenHelper(contexto,"DBApartamentos",null,1);
        db = aux.getWritableDatabase();

        sql = "DELETE FROM Apartamentos where nomenclatura='"+this.getNomenclatura()+"'";
        db.execSQL(sql);
        db.close();

    }

    public void modificar(Context contexto){
        //declarar las variables
        SQLiteDatabase db;
        String sql;

        //Abrir la conexion de base datos en modo escritura
        PersonasSQLiteOpenHelper  aux = new PersonasSQLiteOpenHelper(contexto,"DBApartamentos",null,1);
        db = aux.getWritableDatabase();

        //insertar forma 1
        sql = "UPDATE Apartamentos SET metrocuadrado='"+this.getMetrocuadrado()+"', precio='"+this.getPrecio()+"', piso='"+this.getPiso()+"', " + "balcom='" +this.getBalcom()+"' " + "sombra='" +this.getSombra()+"' " + "where nomenclatura ='"+this.getNomenclatura()+"'";

        db.execSQL(sql);

        //cerrar conexion
        db.close();

    }

}

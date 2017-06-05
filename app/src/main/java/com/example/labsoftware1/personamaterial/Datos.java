package com.example.labsoftware1.personamaterial;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Lab Software 1 on 13/05/2017.
 */

public class Datos {
    private static Apartamento a;
    public static ArrayList<Apartamento> traerPersonas(Context contexto){
        ArrayList<Apartamento> apartamentos = new ArrayList<>();

        //Declarar variables
        SQLiteDatabase db;
        String sql, uuid,urlfoto, idfoto, nomenclatura, metrocuadrado, precio, piso, balcom, sombra;
        ;
        Apartamento p;
        //Abrir conexión de lectura
        PersonasSQLiteOpenHelper aux = new PersonasSQLiteOpenHelper(contexto,"DBApartamentos",null, 4);
        db = aux.getReadableDatabase();

        //Cursor
        sql ="select * from Apartamentos";
        Cursor c = db.rawQuery(sql,null);

        //Recorrido del cursor
        if(c.moveToFirst()){
            do{
                uuid = c.getString(0);
                urlfoto = c.getString(1);
                nomenclatura = c.getString(2);
                metrocuadrado = c.getString(3);
                precio = c.getString(4);
                piso = c.getString(5);
                balcom = c.getString(6);
                sombra = c.getString(7);
                idfoto = c.getColumnName(8);

                p = new Apartamento(uuid,urlfoto,nomenclatura, metrocuadrado, precio, piso, balcom, sombra, idfoto);
                apartamentos.add(p);

            }while (c.moveToNext());
        }

        db.close();

        return apartamentos;

    }

    public static Apartamento buscarApartamento(Context contexto, String ced){


        //Declarar variables
        SQLiteDatabase db;
        String sql, uuid,urlfoto, idfoto, nomenclatura, metrocuadrado, precio, piso, balcom, sombra;
        Apartamento p=null;
        //Abrir conexión de lectura
        PersonasSQLiteOpenHelper aux = new PersonasSQLiteOpenHelper(contexto,"DBApartamentos",null, 4);
        db = aux.getReadableDatabase();

        //Cursor
        sql ="select * from Apartamentos where nomenclatura ='"+ced+"'";
        Cursor c = db.rawQuery(sql,null);

        //Recorrido del cursor
        if(c.moveToFirst()){

            uuid = c.getString(0);
            urlfoto = c.getString(1);
            nomenclatura = c.getString(2);
            metrocuadrado = c.getString(3);
            precio = c.getString(4);
            piso = c.getString(5);
            balcom = c.getString(6);
            sombra = c.getString(7);
            idfoto = c.getColumnName(8);

            p = new Apartamento(uuid,urlfoto, nomenclatura, metrocuadrado, precio, piso, balcom, sombra, idfoto);
        }

        db.close();
        return p;
    }

}

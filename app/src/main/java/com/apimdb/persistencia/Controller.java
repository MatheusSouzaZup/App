package com.apimdb.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.apimdb.domain.Filme;
import java.util.ArrayList;


/**
 * Created by ZUP on 22/03/2017.
 */

public class Controller {

    private CreateDataBase createDataBase;
    private SQLiteDatabase db;

    public Controller(Context context){
        createDataBase.getInstance(context);

    }
    public  Controller(){

    }
    public synchronized long inserirDados(String table, ContentValues values){

        db = createDataBase.getWritableDatabase();
        long resultado = db.insert(table,null,values);

        return resultado;
    }

    public synchronized Cursor CarregaDados(String tabela, String[] campos){
        Cursor cursor;
        db = createDataBase.getReadableDatabase();
        cursor = db.query(tabela,campos,null,null,null,null,null,null);

        if(cursor!= null){
            cursor.moveToFirst();
        }
        return cursor;
    }

    public synchronized void DeletaDados(String tabela, String where){

        db = createDataBase.getReadableDatabase();
        db.delete(tabela,where,null);
    }
    public ArrayList<Filme> getListaFilme() {

        DataBase db = new DataBase();
        String[] campos = db.getCampos();
        int[] indice = new int[campos.length];
        Cursor cursor;
        cursor = this.CarregaDados("filmes_salvos", db.getCampos());
        Filme novo;
        ArrayList<Filme> Lista = new ArrayList<Filme>();
        while (cursor != null) {

            novo = new Filme();
            novo.setTitle(cursor.getString(cursor.getColumnIndex("TITLE")));
            novo.setPlot(cursor.getString(cursor.getColumnIndex("PLOT")));
            novo.setDirector(cursor.getString(cursor.getColumnIndex("DIRECTOR")));
            novo.setActors(cursor.getString(cursor.getColumnIndex("ACTOR")));
            novo.setGenre(cursor.getString(cursor.getColumnIndex("GENRE")));
            novo.setRuntime(cursor.getString(cursor.getColumnIndex("RUNTIME")));
            novo.setLanguage(cursor.getString(cursor.getColumnIndex("LANGUAGE")));
           // novo.setImagem(cursor.getBlob(cursor.getColumnIndex("IMAGEM")));

            cursor.moveToNext();
            Lista.add(novo);
        }

        return Lista;
    }
}
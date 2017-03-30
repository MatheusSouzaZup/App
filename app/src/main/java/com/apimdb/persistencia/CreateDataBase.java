package com.apimdb.persistencia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ZUP on 22/03/2017.
 */

public class CreateDataBase extends SQLiteOpenHelper{

    private static final String nome_banco = "bancoApi.db";
    public static final String nome_tabela = "salvos";
    public static DataBase tabela = new DataBase();
    private static final int version = 3;

    public CreateDataBase(Context context)
    {
        super(context,nome_banco,null,version);
    }
    private static CreateDataBase instance;
    public static synchronized CreateDataBase getInstance(Context context){
        if(instance == null)
            instance = new CreateDataBase(context);
        return instance;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + nome_tabela + "(" + tabela.campos() + ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + nome_tabela);
        this.onCreate(db);
    }
}

package com.apimdb.connection;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.util.Log;

import com.apimdb.domain.Filme;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;



public class Utils {


    public Filme getInformacao(String end){

        String json;
        Filme filmeObj;

        json = NetworkUtils.getJSONFromAPI(end);
        Log.i("Resultado", json);
        //retorno = parseJson(json);


        Gson gson = new Gson();
        filmeObj  = gson.fromJson(json,Filme.class);
        filmeObj.setImagem(baixarImagem(filmeObj.getPoster()));
        return filmeObj;

    }

    public ArrayList<Filme> getInformacaoArray(String end) {
        String json;


        json = NetworkUtils.getJSONFromAPI(end);
        Log.i("Resultado", json);
        //retorno = parseJson(json);

        try{
            ArrayList<Filme> list;

            JSONObject object = new JSONObject(json);
            String object1 = object.getString("Response");
            if(object1.toString().equals("False")){
                return null;
            }

            JSONArray array = object.getJSONArray("Search");
            Type type = new TypeToken<ArrayList<Filme>>(){}.getType();

            Gson gson = new Gson();

            list = gson.fromJson(array.toString(),type);
            for(Filme f:list){
                f.setImagem(baixarImagem(f.getPoster()));
            }

            return list;
        }

        catch (JSONException e){
            e.printStackTrace();
            return null;
        }
    }

    public Bitmap baixarImagem(String url) {
        try{
            URL endereco;
            InputStream inputStream;
            Bitmap imagem; endereco = new URL(url);
            inputStream = endereco.openStream();
            imagem = BitmapFactory.decodeStream(inputStream);
            inputStream.close();
            return imagem;
        }catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


}

package com.apimdb.connection;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.os.Handler;
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
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class Utils {


    public Filme getInformacao(String end){

        String json;
        Filme filmeObj;

        json = end;
        Log.i("Resultado", json);
        //retorno = parseJson(json);


        Gson gson = new Gson();
        filmeObj  = gson.fromJson(json,Filme.class);
        filmeObj.setImagem(downloadImage(filmeObj.getPoster()));
        return filmeObj;

    }

    public ArrayList<Filme> getInformacaoArray(String end) {
        String json;
        json = end;

        try{
            ArrayList<Filme> list;
            Gson gson = new Gson();
            JSONObject object = new JSONObject(json);
            String object1 = object.getString("Response");
            if(object1.toString().equals("False")){
                return null;
            }
            JSONArray array;
            array = object.getJSONArray("Search");
            Type type = new TypeToken<ArrayList<Filme>>(){}.getType();
            list = gson.fromJson(array.toString(),type);

            /*for(Filme f:list){
                f.setImagem(baixarImagem(f.getPoster()));
            }*/
            return list;
        }

        catch (JSONException e){
            e.printStackTrace();
            return null;
        }
    }

    public List<Filme> getImages(List<Filme> movies){

        for(Filme f : movies){
            f.setImagem(downloadImage(f.getPoster()));
        }
        return movies;
    }
    public Bitmap downloadImage(final String urlname) {
        final Bitmap[] image = new Bitmap[1];
        new Thread() {
            public void run() {
                try {
                    URL url = new URL(urlname);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    InputStream inputStream = connection.getInputStream();
                    image[0] = BitmapFactory.decodeStream(inputStream);
                    inputStream.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        return image[0];
    }


}

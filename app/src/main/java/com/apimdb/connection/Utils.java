package com.apimdb.connection;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.os.Handler;
import android.util.Log;

import com.apimdb.MainActivity;
import com.apimdb.domain.Filme;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class Utils {
    private List<Filme> myList;
    public List<Filme> getImages(List<Filme> movies){

        for(Filme f : movies){
            f.setImagem(downloadImage(f.getPoster()));
        }
        myList = movies;
        return movies;
    }
    public Bitmap downloadImage(String urlname) {
        Bitmap image = null;
                try {
                    URL url = new URL(urlname);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    InputStream inputStream = connection.getInputStream();
                    image = BitmapFactory.decodeStream(inputStream);
                    inputStream.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
        return image;
    }
    public String getUrlAllInformation(String imdbid) {
        return  "http://www.omdbapi.com/?i=" + imdbid;
    }
    public boolean checkConnection(Context context) {
        boolean conected;
        ConnectivityManager conectivityManager = (ConnectivityManager)  context.getSystemService(context.CONNECTIVITY_SERVICE);
        if(conectivityManager.getActiveNetworkInfo() !=null
                && conectivityManager.getActiveNetworkInfo().isAvailable()
                && conectivityManager.getActiveNetworkInfo().isConnected()){
            conected = true;
        }else{
            conected = false;
        }
        return conected;
    }
    public List<Filme> getmyList(){
        return myList;
    }

}

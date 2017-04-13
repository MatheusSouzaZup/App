package com.apimdb;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.apimdb.connection.Utils;
import com.apimdb.domain.Filme;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.app.PendingIntent.getActivity;

public class ExtendActivity extends AppCompatActivity {
    private TextView tvTitle;
    private TextView tvInfos;
    private ImageView ivImage;
    private Toolbar myToolbar;
    private Filme myMovie;
    String imdbid, title;
    Bitmap image;
    int position;
    private Utils utils = new Utils();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extend);
        myToolbar = (Toolbar) findViewById(R.id.my_toolbar_extend);
        Intent intent = getIntent();


        byte[] imageByteArray = null;

            if(intent != null)
            {
                Bundle params = intent.getExtras();

                    if(params != null){
                        imdbid = params.getString("imdbid");
                        imageByteArray = params.getByteArray("image");
                        position = params.getInt("pos");
                        title = params.getString("title");
                    }
            }
            image = BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.length);
            myToolbar.setTitle(title);
            setSupportActionBar(myToolbar);
            ivImage = (ImageView) findViewById(R.id.extend_my_image);
            tvTitle = (TextView) findViewById(R.id.extend_my_title);
            tvInfos = (TextView) findViewById(R.id.extent_my_plot);
            searchInfos();
            fillActivity();

    }
    public void fillActivity() {
        ivImage.setImageBitmap(image);
        tvTitle.setText(title);
        tvInfos.setText(myMovie.toString());
    }
    public void searchInfos(){
        if(utils.checkConnection(ExtendActivity.this) == true) {
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, utils.getUrlAllInformation(imdbid), new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Filme movie;
                    try {
                        movie = new Gson().fromJson(response.getJSONArray("Search").toString(), Filme.class);
                        myMovie = movie;
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println("Erro");
                    error.printStackTrace();
                }
            }
            );

            MyApplication.getInstance().addToRequestQueue(request);
        }
        else{
            Toast.makeText(ExtendActivity.this, "Sem Conexão", Toast.LENGTH_SHORT).show();
        }
    }

}
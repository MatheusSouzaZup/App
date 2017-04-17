package com.apimdb;


import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.apimdb.adapter.MovieAdapter;
import com.apimdb.connection.Utils;
import com.apimdb.domain.Movie;
import com.apimdb.persistencia.Controller;
import com.apimdb.persistencia.CreateDataBase;
import com.google.gson.Gson;
import org.json.JSONObject;
import java.io.ByteArrayOutputStream;



public class ExtendActivity extends AppCompatActivity {
    private TextView tvTitle;
    private TextView tvInfos;
    private ImageView ivImage;
    private Toolbar myToolbar;
    private ImageButton imageButton;
    private Movie myMovie;
    String imdbid, title;
    Bitmap image;
    int position;
    int contextint = 0;
    private Utils utils = new Utils();
    private Controller controllerDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extend);
        myToolbar = (Toolbar) findViewById(R.id.my_toolbar_extend);
        controllerDB = new Controller(getApplicationContext());
        initCompontents();

    }
    private void initCompontents() {

        Intent intent = getIntent();

        byte[] imageByteArray = null;

        if (intent != null) {
            Bundle params = intent.getExtras();

            if (params != null) {
                imdbid = params.getString(MovieAdapter.KEY_IMDBID);
                contextint = params.getInt("contextint");
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
        imageButton = (ImageButton) findViewById(R.id.imageButton);
        if(contextint == 2){
            View v = findViewById(R.id.imageButton);
            imageButton.setVisibility(View.GONE);
            getinfosfrombd();
        }
        else{
            getInfosFromService();
        }
    }
    private void getinfosfrombd() {
        myMovie = controllerDB.getMoviefrombd("salvos",imdbid);
        myMovie.setImagem(image);
        fillActivity(myMovie);
    }
    private void fillActivity(Movie f) {
        ivImage.setImageBitmap(image);
        tvTitle.setText(title);
        tvInfos.setText(myMovie.toString());
        myMovie = f;
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = setupvalues(myMovie);
                long resultado = controllerDB.inserirDados(CreateDataBase.NOME_TABELA, values);
                 if (resultado == -1) {
                     Toast.makeText(getApplicationContext(), "Salvo!", Toast.LENGTH_SHORT).show();

                } else {
                     Toast.makeText(getApplicationContext(), "Este item já esta salvo!", Toast.LENGTH_SHORT).show();
                 }
            }
        });
    }
    public byte[] bitmaptoblob(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();

    }
    private ContentValues setupvalues(Movie myMovie) {
        ContentValues values = new ContentValues();
        values.put("TITLE", myMovie.getTitle());
        values.put("YEAR", myMovie.getYear());
        values.put("RATED", myMovie.getRated());
        values.put("RELEASED", myMovie.getReleased());
        values.put("RUNTIME", myMovie.getRuntime());
        values.put("GENRE", myMovie.getGenre());
        values.put("DIRECTOR", myMovie.getDirector());
        values.put("ACTORS", myMovie.getActors());
        values.put("PLOT", myMovie.getPlot());
        values.put("LANGUAGE", myMovie.getLanguage());
        values.put("IMAGE", bitmaptoblob(myMovie.getImagem()));
        values.put("IMDBID", myMovie.getImdbID());
        values.put("IMDBRATING", myMovie.getImdbRating());
        values.put("POSTER", myMovie.getPoster());
        return values;
    }
    public void getInfosFromService() {
        if (utils.checkConnection(ExtendActivity.this) == true) {
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, utils.getUrlAllInformation(imdbid), new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Movie movie;
                    movie = new Gson().fromJson(response.toString(), Movie.class);
                    myMovie = movie;
                    myMovie.setImagem(image);
                    fillActivity(myMovie);
                }

            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println(getString(R.string.error));
                    error.printStackTrace();
                }
            }
            );

            MyApplication.getInstance().addToRequestQueue(request);
        } else {
            Toast.makeText(ExtendActivity.this, getString(R.string.not_connect), Toast.LENGTH_SHORT).show();
        }
    }

}
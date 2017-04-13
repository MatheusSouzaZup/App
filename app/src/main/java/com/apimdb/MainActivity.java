package com.apimdb;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.apimdb.connection.Utils;
import com.apimdb.domain.Filme;
import com.apimdb.fragments.MovieFragment;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import static com.apimdb.R.mipmap.ic_filmreel_black;

public class MainActivity extends AppCompatActivity{
    private Toolbar myToolbar;
    private List<Filme> list = new ArrayList<Filme>();
    private String search;
    private String url = "http://www.omdbapi.com/?s=";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setLogo(ic_filmreel_black);
        setSupportActionBar(myToolbar);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        android.widget.SearchView sv = (android.widget.SearchView) menu.findItem(R.id.menuSearch).getActionView();
        sv.setOnQueryTextListener(new SearchFiltro());


        return super.onCreateOptionsMenu(menu);
    }

    private class SearchFiltro implements android.widget.SearchView.OnQueryTextListener {

        @Override
        public boolean onQueryTextChange(String newText) {

            return false;
        }

        @Override
        public boolean onQueryTextSubmit(String query) {
            search = query.toString().replace(' ', '+');
            mySearch();
            return false;
        }
    }
    public String geturl(){
        return url +search;
    }
        public void mySearch(){
            final GetImages getImages = new GetImages(MainActivity.this);
            getImages.execute();

        }
        public void callMovieFragment(){
            MovieFragment fra = (MovieFragment) getSupportFragmentManager().findFragmentByTag("mainFrag");
            fra = new MovieFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.myIncFragmentContainer, fra, "mainFrag");
            ft.commit();
        }
        @Override
        public boolean onOptionsItemSelected(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.menuSavedMovies:
                    Intent intent = new Intent(MainActivity.this, SavedActivity.class);
                    startActivity(intent);
                    return true;

                default:
                    return super.onOptionsItemSelected(menuItem);
            }
        }

    public List<Filme> getList(){
        return list;
    }

    public class GetImages extends AsyncTask<List<Filme>, Void, Void> {
        List<Filme> listAux;
        private Context context;
        private ProgressDialog load;
        final Utils util = new Utils();

        @Override
        protected void onPreExecute(){
            load = ProgressDialog.show(context,"","Loading...",true);
        }

        public GetImages(Context context) {
            listAux = new ArrayList<Filme>();
            this.context = context;
        }
        @Override
        protected Void doInBackground(List<Filme>... params) {
            if(util.checkConnection(MainActivity.this) == true) {

                JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, geturl(), new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        List<Filme> movies = null;
                        try {
                            movies = Arrays.asList(new Gson().fromJson(response.getJSONArray("Search").toString(), Filme[].class));
                            list = util.getImages(movies);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                }, new ErrorListener() {
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
                Toast.makeText(MainActivity.this, "Sem Conexão", Toast.LENGTH_SHORT).show();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void v){
            load.dismiss();
            callMovieFragment();

        }

        public List<Filme> getLista() {
            return list;
        }

    }



}

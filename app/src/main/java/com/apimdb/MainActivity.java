package com.apimdb;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import com.apimdb.connection.Utils;
import com.apimdb.domain.Filme;
import com.apimdb.fragments.MovieFragment;

import static com.apimdb.R.mipmap.ic_filmreel_black;

public class MainActivity extends AppCompatActivity{
    private Toolbar myToolbar;
    private ArrayList<Filme> list;
    private String search;
    private String url = "http://www.omdbapi.com/?s=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setLogo(ic_filmreel_black);
        setSupportActionBar(myToolbar);
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
            Search();
            return false;
        }
    }
    public String geturl(){
        return url +search;
    }
        public void Search() {
            boolean conection = checkConnection();
            if(conection == true) {

                GetJson download = new GetJson(MainActivity.this);
                GetJson2 download2 = new GetJson2(MainActivity.this);
                download.execute( url + search);

                try {
                    list = download.get();
                    download2.execute(list);
                    list = download2.getLista();

                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else{
                Toast.makeText(MainActivity.this, "Sem Conexão", Toast.LENGTH_SHORT).show();
            }

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

    public ArrayList<Filme> getList(){
        return list;
    }


    public class GetJson extends AsyncTask<String, Void, ArrayList<Filme>> {
        private Context context;
        private ProgressDialog load;

        public GetJson(Context context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            load = ProgressDialog.show(context, "", "Loading...", true);
        }

        @Override
        protected ArrayList<Filme> doInBackground(String... params) {
            Utils util = new Utils();

            return util.getInformacaoArray(params[0]);
        }

        @Override
        protected void onPostExecute(ArrayList<Filme> filmeObj) {
            load.dismiss();
        }

    }


    public class GetJson2 extends AsyncTask<ArrayList<Filme>, Void, Void> {
        ArrayList<Filme> list;
        private Context context;
        private ProgressDialog load;


        @Override
        protected void onPreExecute(){
            load = ProgressDialog.show(context,"","Loading...",true);
        }

        public GetJson2(Context context) {
            list = new ArrayList<Filme>();
            this.context = context;
        }



        @Override
        protected Void doInBackground(ArrayList<Filme>... params) {
            Utils util = new Utils();

            for (Filme f : params[0]) {
                f = util.getInformacao("http://www.omdbapi.com/?i=" + f.getImdbID());
                list.add(f);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void v){
            load.dismiss();

            MovieFragment fra = (MovieFragment) getSupportFragmentManager().findFragmentByTag("mainFrag");
                fra = new MovieFragment();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.myIncFragmentContainer, fra, "mainFrag");
                ft.commit();

        }

        public ArrayList<Filme> getLista() {
            return list;
        }

    }

    public boolean checkConnection()
    {
        boolean conected;
        ConnectivityManager conectivityManager = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
            if(conectivityManager.getActiveNetworkInfo() !=null
                    && conectivityManager.getActiveNetworkInfo().isAvailable()
                    && conectivityManager.getActiveNetworkInfo().isConnected()){
                conected = true;
            }else{
                conected = false;
            }
            return conected;
    }
}

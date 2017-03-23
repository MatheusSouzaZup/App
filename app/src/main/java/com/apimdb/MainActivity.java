package com.apimdb;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import com.apimdb.connection.Utils;
import com.apimdb.domain.Filme;
import com.apimdb.fragments.MovieFragment;

public class MainActivity extends AppCompatActivity {
    private Toolbar myToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setTitle("Lista");
        setSupportActionBar(myToolbar);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuItem item = menu.findItem(R.id.menuSearch);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        Log.i("Mensagem", "Aqui");



        return super.onCreateOptionsMenu(menu);
    }
    private class SearchFiltro implements SearchView.OnQueryTextListener {

        @Override
        public boolean onQueryTextChange(String newText) {

            Log.i("Script", "onQueryTextChange->" + newText);

            return false;
        }

        @Override
        public boolean onQueryTextSubmit(String query) {
            Log.i("Script", "onQueryTextSubmit->" + query);
                String x = query.toString().replace(' ', '+');
                GetJson download = new GetJson(MainActivity.this);
                GetJson2 download2 = new GetJson2(MainActivity.this);
                download.execute("http://www.omdbapi.com/?s=" + x);

                try {
                    ArrayList<Filme> list;
                    list = download.get();
                    download2.execute(list);
                    list = download2.getLista();
                    ///Here
                    MovieFragment fra = (MovieFragment) getSupportFragmentManager().findFragmentByTag("mainFrag");

                    if (fra == null) {
                        fra = new MovieFragment(list);
                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.myIncFragmentContainer, fra, "mainFrag");
                        ft.commit();
                    }

                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            return false;
        }
    }
    /*
        @Override
        public boolean onOptionsItemSelected(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.menuSearch:
                    Intent intent = new Intent(MainActivity.this, SavedActivity.class);
                    startActivity(intent);
                    return true;

                default:
                    return super.onOptionsItemSelected(menuItem);
            }
        }
        */
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
                // f = util.getInformacao("http://www.omdbapi.com/?t=" + f.getTitle().replace(' ', '+'));

                list.add(f);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void v){
            load.dismiss();
        }

        public ArrayList<Filme> getLista() {
            return list;
        }

    }
}

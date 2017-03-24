package com.apimdb;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.apimdb.domain.Filme;
import com.apimdb.fragments.MovieFragment;
import com.apimdb.fragments.MovieFragmentSaved;

public class SavedActivity extends AppCompatActivity {
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved);
        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        Filme filme = new Filme();
        MovieFragmentSaved fra = (MovieFragmentSaved) getSupportFragmentManager().findFragmentByTag("mainFragSaved");

        if (fra == null) {
            fra = new MovieFragmentSaved(filme.getLista());
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.myIncFragmentContainer, fra, "mainFragSaved");
            ft.commit();
        }
    }
}

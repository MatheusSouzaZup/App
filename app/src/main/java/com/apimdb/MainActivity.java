package com.apimdb;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.apimdb.*;
import com.apimdb.Dados.Filmes;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity {

    private Toolbar myToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        SearchView sv = new SearchView(this);
        sv.setOnQueryTextListener(new SearchFiltro());
        MenuItem m1 = menu.add(0, 0, 0, "Item 1");
        m1.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        m1.setActionView(sv);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);


        return true;
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
            return false;
        }
    }

    public List<Filmes>  getLista(){
            List<Filmes> listaFilmes = new List<Filmes>() {
                @Override
                public int size() {
                    return 0;
                }

                @Override
                public boolean isEmpty() {
                    return false;
                }

                @Override
                public boolean contains(Object o) {
                    return false;
                }

                @NonNull
                @Override
                public Iterator<Filmes> iterator() {
                    return null;
                }

                @NonNull
                @Override
                public Object[] toArray() {
                    return new Object[0];
                }

                @NonNull
                @Override
                public <T> T[] toArray(@NonNull T[] a) {
                    return null;
                }

                @Override
                public boolean add(Filmes filmes) {
                    return false;
                }

                @Override
                public boolean remove(Object o) {
                    return false;
                }

                @Override
                public boolean containsAll(@NonNull Collection<?> c) {
                    return false;
                }

                @Override
                public boolean addAll(@NonNull Collection<? extends Filmes> c) {
                    return false;
                }

                @Override
                public boolean addAll(int index, @NonNull Collection<? extends Filmes> c) {
                    return false;
                }

                @Override
                public boolean removeAll(@NonNull Collection<?> c) {
                    return false;
                }

                @Override
                public boolean retainAll(@NonNull Collection<?> c) {
                    return false;
                }

                @Override
                public void clear() {

                }

                @Override
                public boolean equals(Object o) {
                    return false;
                }

                @Override
                public int hashCode() {
                    return 0;
                }

                @Override
                public Filmes get(int index) {
                    return null;
                }

                @Override
                public Filmes set(int index, Filmes element) {
                    return null;
                }

                @Override
                public void add(int index, Filmes element) {

                }

                @Override
                public Filmes remove(int index) {
                    return null;
                }

                @Override
                public int indexOf(Object o) {
                    return 0;
                }

                @Override
                public int lastIndexOf(Object o) {
                    return 0;
                }

                @Override
                public ListIterator<Filmes> listIterator() {
                    return null;
                }

                @NonNull
                @Override
                public ListIterator<Filmes> listIterator(int index) {
                    return null;
                }

                @NonNull
                @Override
                public List<Filmes> subList(int fromIndex, int toIndex) {
                    return null;
                }
            };


            Filmes novo = new Filmes();
            novo.setTitle("Harry Potter and the Deathly Hallows: Part 2");
            listaFilmes.add(novo);
            return listaFilmes;
    }
}

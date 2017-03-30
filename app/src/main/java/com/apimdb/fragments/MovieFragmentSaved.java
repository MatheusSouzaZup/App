package com.apimdb.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.apimdb.R;
import com.apimdb.SavedActivity;
import com.apimdb.adapter.MovieAdapter;
import com.apimdb.adapter.MovieSavedAdapter;
import com.apimdb.domain.Filme;
import com.apimdb.persistencia.Controller;

import java.util.ArrayList;

/**
 * Created by Matheus on 19/03/2017.
 */

public class MovieFragmentSaved extends android.support.v4.app.Fragment {

    private RecyclerView myRecyclerView;
    private ArrayList<Filme> myList;
    private Controller controller;

        @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState){
            View view = inflater.inflate(R.layout.fragment_movies_saved,container,false);
            myRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_saved);
            myRecyclerView.setHasFixedSize(true);
            myRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

                    super.onScrollStateChanged(recyclerView, newState);
                }

                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    LinearLayoutManager llm =(LinearLayoutManager) myRecyclerView.getLayoutManager();
                    MovieSavedAdapter adapter = (MovieSavedAdapter) myRecyclerView.getAdapter();
                        if(myList.size() == llm.findLastCompletelyVisibleItemPosition()+1){
                            ArrayList<Filme> listAux =  myList;
                                for (int i = 0; i<listAux.size();i++){
                                    adapter.addListItem(listAux.get(i),myList.size());
                                }
                        }
                }
            });

            LinearLayoutManager llm = new LinearLayoutManager(getActivity());
            llm.setOrientation(LinearLayoutManager.VERTICAL);
            myRecyclerView.setLayoutManager(llm);
            myList = ((SavedActivity) getActivity()).myList;
            MovieSavedAdapter adapter = new MovieSavedAdapter(myList,getActivity());

            myRecyclerView.setAdapter(adapter);
            return view;
        }

}

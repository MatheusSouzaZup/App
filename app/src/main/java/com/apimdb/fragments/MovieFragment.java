package com.apimdb.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import com.apimdb.R;
import com.apimdb.adapter.MovieAdapter;
import com.apimdb.domain.Filme;
/**
 * Created by Matheus on 19/03/2017.
 */

public class MovieFragment extends android.support.v4.app.Fragment {

    private RecyclerView myRecyclerView;
    private ArrayList<Filme> myList;

        public MovieFragment(ArrayList<Filme> l)
        {
            myList = l;
            Log.i("Lista",l.get(0).getPlot());

        }
        @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState){
            View view = inflater.inflate(R.layout.fragment_movies,container,false);
            myRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
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
                    MovieAdapter adapter = (MovieAdapter) myRecyclerView.getAdapter();
                        if(myList.size() == llm.findLastCompletelyVisibleItemPosition()+1){
                            //Testing
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
            //Testing
            MovieAdapter adapter = new MovieAdapter(myList,getActivity());

            myRecyclerView.setAdapter(adapter);
            return view;
        }

}

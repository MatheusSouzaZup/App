package com.apimdb.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.apimdb.MainActivity;
import com.apimdb.R;
import com.apimdb.adapter.MovieAdapter;
import com.apimdb.domain.Movie;
import java.util.List;

/**
 * Created by Matheus on 19/03/2017.
 */

public class DefaultFragment extends Fragment {

    private RecyclerView myRecyclerView;
    private List<Movie> myList;
    private Movie filmeobj = new Movie();

        @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
            View view = inflater.inflate(R.layout.fragment_movies,container,false);

            return view;
        }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        myRecyclerView = (RecyclerView) getActivity().findViewById(R.id.recyclerView);
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
                if(myList.size() != llm.findLastCompletelyVisibleItemPosition()+1){
                    List<Movie> listAux =  ((MainActivity) getActivity()).getmyOscarList();
                    for (int i = 0; i<listAux.size();i++){
                        adapter.addListItem(listAux.get(i),myList.size());
                    }
                }
            }
        });


        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        myRecyclerView.setLayoutManager(llm);
        myList = ((MainActivity) getActivity()).getmyOscarList();
        MovieAdapter adapter = new MovieAdapter(myList, getActivity());
        myRecyclerView.setAdapter(adapter);
    }

}

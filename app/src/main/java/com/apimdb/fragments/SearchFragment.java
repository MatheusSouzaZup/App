package com.apimdb.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import com.apimdb.Dados.Filmes;
import com.apimdb.MainActivity;
import com.apimdb.R;
import com.apimdb.adapter.SearchAdapter;

import java.util.List;

/**
 * Created by Matheus on 16/03/2017.
 */

public class SearchFragment extends Fragment {

    private RecyclerView myRecyclerView;
    private List<Filmes> myList;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState){
        View view =inflater.inflate(R.layout.fragment_search,container,false);

            myRecyclerView = (RecyclerView) view.findViewById(R.id.search_fragment);
            myRecyclerView.setHasFixedSize(true);
            myRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                }

                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    LinearLayoutManager llm = (LinearLayoutManager) myRecyclerView.getLayoutManager();
                    SearchAdapter adapter = (SearchAdapter) myRecyclerView.getAdapter();

                        if(myList.size()==llm.findLastVisibleItemPosition()+1)
                        {
                            List<Filmes> listAux =((MainActivity) getActivity()).getLista();
                            for(int i = 0; i < listAux.size();i++){
                                adapter.addListItem(listAux.get(i), myList.size());
                            }
                        }
               }
            });

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        myRecyclerView.setLayoutManager(llm);

        myList = ((MainActivity)getActivity()).getLista();
        SearchAdapter adapter = new SearchAdapter(getActivity(),myList);
        myRecyclerView.setAdapter(adapter);
        return view;
    }

}
package com.apimdb.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.apimdb.R;
import com.apimdb.domain.Filme;

import java.util.List;


/**
 * Created by Matheus on 19/03/2017.
 */

public class MovieAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private List<Filme> myList;
    private LayoutInflater myLayoutInflater;
    public MovieAdapter(List<Filme> l, Context c){
        myList = l;
        myLayoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = myLayoutInflater.inflate(R.layout.item_movie_card,parent,false);
        MyViewHolder mvh = new MyViewHolder(v);
        return mvh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.imMovie.setImageBitmap(myList.get(position).getImagem());
        holder.tvTitle.setText(myList.get(position).getTitle());
        holder.tvPlot.setText(myList.get(position).getPlot());
    }

    @Override
    public int getItemCount() {
        return myList.size();
    }
    public void addListItem(Filme f, int position){
        myList.add(f);
        notifyItemInserted(position);
    }


}

package com.apimdb.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.apimdb.ExtendActivity;
import com.apimdb.R;
import com.apimdb.SavedActivity;
import com.apimdb.domain.Filme;

import java.util.List;

/**
 * Created by ZUP on 24/03/2017.
 */

public class MovieSavedAdapter extends RecyclerView.Adapter<ViewHolderSaved> {

    private List<Filme> myList;
    private LayoutInflater myLayoutInflater;
    private Context context;
    public MovieSavedAdapter(List<Filme> l, Context c){
        myList = l;
        myLayoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        context = c;
    }
    @Override
    public ViewHolderSaved onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = myLayoutInflater.inflate(R.layout.item_movie_card_saved,parent,false);
        ViewHolderSaved mvh = new ViewHolderSaved(v);
        return mvh;
    }

    @Override
    public void onBindViewHolder(final ViewHolderSaved holder, int position) {

        holder.tvTitle.setText(myList.get(position).getTitle());
        holder.tvDescription.setText(myList.get(position).getPlot());

  /*      holder.btnExpand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(, ExtendActivity.class));
            }
        });
        */
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

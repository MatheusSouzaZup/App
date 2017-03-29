package com.apimdb.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.apimdb.ExtendActivity;
import com.apimdb.R;
import com.apimdb.domain.Filme;

import java.util.List;


/**
 * Created by Matheus on 19/03/2017.
 */

public class MovieAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private List<Filme> myList;
    private LayoutInflater myLayoutInflater;
    private Context context;
    public MovieAdapter(List<Filme> l, Context c){
        myList = l;
        myLayoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        context = c;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = myLayoutInflater.inflate(R.layout.item_movie_card,parent,false);
        MyViewHolder mvh = new MyViewHolder(v);
        return mvh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Drawable drawable;
        holder.imMovie.setImageBitmap(myList.get(position).getImagem());
        holder.tvTitle.setText(myList.get(position).getTitle());
        holder.tvPlot.setText(myList.get(position).getPlot());
        holder.btnExpand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();

                bundle.putString("title", myList.get(position).getTitle());
                bundle.putString("infos",myList.get(position).toString());
                bundle.putSerializable("image", myList.get(position).getPoster());
                Intent intent = new Intent(context, ExtendActivity.class);
                intent.putExtras(bundle);

                context.startActivity(intent);
            }
        });
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

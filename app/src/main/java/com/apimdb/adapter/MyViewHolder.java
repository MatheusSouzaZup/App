package com.apimdb.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.apimdb.R;

/**
 * Created by ZUP on 20/03/2017.
 */

public class MyViewHolder extends RecyclerView.ViewHolder{
    public ImageView imMovie;
    public TextView tvTitle;
    public TextView tvDescription;
    public MyViewHolder(View itemView) {
        super(itemView);

        imMovie = (ImageView) itemView.findViewById(R.id.my_image);
        tvTitle = (TextView) itemView.findViewById(R.id.my_title);
        tvDescription = (TextView) itemView.findViewById(R.id.my_description);
    }
}
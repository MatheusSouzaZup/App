package com.apimdb.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.apimdb.R;

/**
 * Created by ZUP on 20/03/2017.
 */

public class MyViewHolder extends RecyclerView.ViewHolder{
    public ImageView imMovie;
    public TextView tvTitle;
    public TextView tvPlot;
    public Button btnExpand;
    public Button btnAdicionar;
    public MyViewHolder(View itemView) {
        super(itemView);

        imMovie = (ImageView) itemView.findViewById(R.id.my_image);
        tvTitle = (TextView) itemView.findViewById(R.id.my_title);
        tvPlot = (TextView) itemView.findViewById(R.id.my_description);
        btnExpand = (Button) itemView.findViewById(R.id.btnExpandir);
        btnAdicionar = (Button) itemView.findViewById(R.id.btnAdicionar);
    }
}
package com.apimdb.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.apimdb.R;
import com.apimdb.Dados.Filmes;

import java.util.List;

/**
 * Created by Matheus on 16/03/2017.
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.MyViewHolder> {
    private List<Filmes> myList;
    private LayoutInflater myLayoutInflater;

    public SearchAdapter(Context c, List<Filmes> l)
    {
        myList = l;
        myLayoutInflater= (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = myLayoutInflater.inflate(R.layout.item_search, parent,false);
        MyViewHolder mvh = new MyViewHolder(v);
        return mvh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.imPoster.setImageBitmap(myList.get(position).getImagem());
        holder.tvtTitle.setText(myList.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return myList.size();
    }
    public void addListItem(Filmes f, int position)
    {
        myList.add(f);
        notifyItemInserted(position);
    }

    public class MyViewHolder  extends RecyclerView.ViewHolder{
        public ImageView imPoster;
        public TextView tvtTitle;


            public MyViewHolder(View itemView) {
                super(itemView);

                imPoster = (ImageView) itemView.findViewById(R.id.my_image);
                tvtTitle = (TextView) itemView.findViewById(R.id.my_title);

            }
        }
}

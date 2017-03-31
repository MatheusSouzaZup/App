package com.apimdb.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.apimdb.ExtendActivity;
import com.apimdb.R;
import com.apimdb.SavedActivity;
import com.apimdb.domain.Filme;
import com.apimdb.persistencia.Controller;
import com.apimdb.persistencia.CreateDataBase;

import java.io.ByteArrayOutputStream;
import java.util.List;

/**
 * Created by ZUP on 24/03/2017.
 */

public class MovieSavedAdapter extends RecyclerView.Adapter<ViewHolderSaved> {

    private List<Filme> myList;
    private LayoutInflater myLayoutInflater;
    private Context context;
    private Controller controller;
    public MovieSavedAdapter(List<Filme> l, Context c){
        myList = l;
        myLayoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        context = c;
        controller = new Controller(context);
    }
    @Override
    public ViewHolderSaved onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = myLayoutInflater.inflate(R.layout.item_movie_card_saved,parent,false);
        ViewHolderSaved mvh = new ViewHolderSaved(v);
        return mvh;
    }

    @Override
    public void onBindViewHolder(final ViewHolderSaved holder, final int position) {
        holder.imMovie.setImageBitmap(myList.get(position).getImagem());
        holder.tvTitle.setText(myList.get(position).getTitle());
        holder.tvDescription.setText(myList.get(position).getPlot());

        //Configurando Imagem para mandar a outra activity
        Drawable drawable;
        Bitmap bitmap;
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        drawable = holder.imMovie.getDrawable();
        bitmap = ((BitmapDrawable)drawable).getBitmap();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100, stream);
        final byte[] bitMapData = stream.toByteArray();


        holder.btnExpand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();

                bundle.putString("title", myList.get(position).getTitle());
                bundle.putString("infos",myList.get(position).toString());
                bundle.putSerializable("image", bitMapData);
                Intent intent = new Intent(context, ExtendActivity.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

        holder.btnRemover.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                final String where = CreateDataBase.tabela.TITLE + "=" + "'" + myList.get(position).getTitle() + "'";
                controller.DeletaDados(CreateDataBase.NOME_TABELA, where);
                Toast.makeText(context, "Removed!", Toast.LENGTH_SHORT).show();
                ((SavedActivity) context).refresh();

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

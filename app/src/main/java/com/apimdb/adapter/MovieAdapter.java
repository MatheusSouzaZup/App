package com.apimdb.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.apimdb.ExtendActivity;
import com.apimdb.MainActivity;
import com.apimdb.R;
import com.apimdb.SavedActivity;
import com.apimdb.domain.Filme;
import com.apimdb.persistencia.Controller;
import com.apimdb.persistencia.CreateDataBase;
import com.apimdb.persistencia.DataBase;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;


/**
 * Created by Matheus on 19/03/2017.
 */

public class MovieAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private List<Filme> myList;
    private LayoutInflater myLayoutInflater;
    private Context context;
    private Controller controllerDB;
    public MovieAdapter(List<Filme> l, Context c){
        myList = l;
        myLayoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        context = c;
        controllerDB = new Controller(context);
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = myLayoutInflater.inflate(R.layout.item_movie_card,parent,false);
        MyViewHolder mvh = new MyViewHolder(v);
        return mvh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {


        holder.imMovie.setImageBitmap(myList.get(position).getImagem());
        holder.tvTitle.setText(myList.get(position).getTitle());
        holder.tvPlot.setText(myList.get(position).getPlot());

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
        holder.btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (context.getClass().equals(MainActivity.class)) {
                     ContentValues values = new ContentValues();

                     values.put("TITLE", myList.get(position).getTitle());
                     values.put("YEAR", myList.get(position).getYear());
                     values.put("RATED", myList.get(position).getRated());
                     values.put("RELEASED", myList.get(position).getReleased());
                     values.put("RUNTIME", myList.get(position).getRuntime());
                     values.put("GENRE", myList.get(position).getGenre());
                     values.put("DIRECTOR", myList.get(position).getDirector());
                     values.put("ACTORS", myList.get(position).getActors());
                     values.put("PLOT", myList.get(position).getPlot());
                     values.put("LANGUAGE", myList.get(position).getLanguage());
                     values.put("IMAGE", bitmaptoblob(myList.get(position).getImagem()));
                     values.put("IMDBID", myList.get(position).getImdbID());
                     values.put("IMDBRATING", myList.get(position).getImdbRating());
                     //values.put("POSTER", myList.get(position).getPoster());

                     long resultado = controllerDB.inserirDados(CreateDataBase.NOME_TABELA, values);
                     Log.i("Msg", String.valueOf(resultado));
                     if (resultado == -1) {
                         Toast.makeText(context, "This movie is already saved!", Toast.LENGTH_SHORT).show();

                     } else {
                         Toast.makeText(context, "Save", Toast.LENGTH_SHORT).show();

                     }
                 }

            }
        });
    }
    public byte[] bitmaptoblob(Bitmap bitmap) {

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

    @Override
    public int getItemCount() {
        return myList.size();
    }
    public void addListItem(Filme f, int position){

        if(myList.contains(f) == false) {
            myList.add(f);
        }
        notifyItemInserted(position);
    }


}

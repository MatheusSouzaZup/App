package com.apimdb;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ExtendActivity extends AppCompatActivity {
    private TextView tvTitle;
    private TextView tvInfos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extend);
        Intent intent = getIntent();
        String title = "", infos="";
        Bitmap image;


            if(intent != null)
            {
                Bundle params = intent.getExtras();

                    if(params != null){
                        title = params.getString("title");
                        infos = params.getString("infos");

                    }
            }
            tvTitle = (TextView) findViewById(R.id.extend_my_title);
            tvInfos = (TextView) findViewById(R.id.extent_my_plot);
            tvTitle.setText(title);
            tvInfos.setText(infos);


    }
}

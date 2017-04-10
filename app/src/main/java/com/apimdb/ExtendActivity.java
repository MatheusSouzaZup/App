package com.apimdb;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

public class ExtendActivity extends AppCompatActivity {
    private TextView tvTitle;
    private TextView tvInfos;
    private ImageView ivImage;
    private Toolbar myToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extend);
        myToolbar = (Toolbar) findViewById(R.id.my_toolbar_extend);

        Intent intent = getIntent();
        String title = "", infos="";
        Bitmap image;
        byte[] imageByteArray = null;

            if(intent != null)
            {
                Bundle params = intent.getExtras();

                    if(params != null){
                        title = params.getString("title");
                        infos = params.getString("infos");
                        imageByteArray = params.getByteArray("image");
                    }
            }
            image = BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.length);
            myToolbar.setTitle(title);
            setSupportActionBar(myToolbar);
            ivImage = (ImageView) findViewById(R.id.extend_my_image);
            tvTitle = (TextView) findViewById(R.id.extend_my_title);
            tvInfos = (TextView) findViewById(R.id.extent_my_plot);


            ivImage.setImageBitmap(image);
            tvTitle.setText(title);
            tvInfos.setText(infos);


    }
}

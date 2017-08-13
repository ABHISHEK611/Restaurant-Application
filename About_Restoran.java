package com.example.abhishek.restoran;

import android.graphics.Paint;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class About_Restoran extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about__restoran);
        TextView tv=(TextView)findViewById(R.id.tv);
        tv.setPaintFlags(tv.getPaintFlags() |  Paint.UNDERLINE_TEXT_FLAG);

        ImageView iv=(ImageView)findViewById(R.id.iv);
        iv.setBackgroundResource(R.drawable.about_anime);

        AnimationDrawable restorabout=(AnimationDrawable)iv.getBackground();
        restorabout.start();
    }
}

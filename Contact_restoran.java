package com.example.abhishek.restoran;

import android.graphics.Paint;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Contact_restoran extends AppCompatActivity {

    private TextView tv;
    private TextView tv1;
    private TextView tv3;
    private TextView tv5;
    private TextView tv7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_restoran);

        ImageView iv=(ImageView)findViewById(R.id.iv);
        iv.setBackgroundResource(R.drawable.contact_anime);

        AnimationDrawable restorabout=(AnimationDrawable)iv.getBackground();
        restorabout.start();

        tv = (TextView)findViewById(R.id.tv);
        tv.setPaintFlags(tv.getPaintFlags() |  Paint.UNDERLINE_TEXT_FLAG);

        tv1 = (TextView)findViewById(R.id.tv1);
        tv1.setPaintFlags(tv1.getPaintFlags() |  Paint.UNDERLINE_TEXT_FLAG);

        tv3 = (TextView)findViewById(R.id.tv3);
        tv3.setPaintFlags(tv3.getPaintFlags() |  Paint.UNDERLINE_TEXT_FLAG);

        tv5 = (TextView)findViewById(R.id.tv5);
        tv5.setPaintFlags(tv5.getPaintFlags() |  Paint.UNDERLINE_TEXT_FLAG);

        tv7 = (TextView)findViewById(R.id.tv7);
        tv7.setPaintFlags(tv7.getPaintFlags() |  Paint.UNDERLINE_TEXT_FLAG);
    }
}

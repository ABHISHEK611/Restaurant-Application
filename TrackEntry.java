package com.example.abhishek.restoran;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class TrackEntry extends AppCompatActivity implements View.OnClickListener {
    Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_entry);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bt=(Button)findViewById(R.id.track1);
        bt.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        Intent next=new Intent(TrackEntry.this,MapTrackingActivity.class);
        startActivity(next);
    }
}

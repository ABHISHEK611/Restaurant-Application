package com.example.abhishek.restoran;

import android.content.Intent;
import android.graphics.Paint;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ImageView iv=(ImageView)findViewById(R.id.iv);
        iv.setBackgroundResource(R.drawable.restoran_anime);

        AnimationDrawable restorananime=(AnimationDrawable)iv.getBackground();
        restorananime.start();

        TextView tv5=(TextView)findViewById(R.id.tv5);
        tv5.setPaintFlags(tv5.getPaintFlags() |  Paint.UNDERLINE_TEXT_FLAG);
        tv5.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        } else
        {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id)
        {
            case R.id.nav_signin:
                Intent next=new Intent(MainActivity.this,Signin.class);
                startActivity(next);
                break;
            case R.id.nav_menu:
                if(FirebaseAuth.getInstance().getCurrentUser()==null)
                {
                 Intent next1=new Intent(MainActivity.this,Signin.class);
                startActivity(next1);
                }
                break;
            case R.id.nav_about:
                Intent next2=new Intent(MainActivity.this,About_Restoran.class);
                startActivity(next2);
                break;
            case R.id.nav_help:
                Intent next3=new Intent(MainActivity.this,Contact_restoran.class);
                startActivity(next3);
                break;
            case R.id.nav_signout:
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(this, "Successfully Signed out", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_trackorder:
                Intent next4=new Intent(MainActivity.this,TrackEntry.class);
                startActivity(next4);
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View view)
    {
    Intent next1=new Intent(MainActivity.this,Registration.class);
        startActivity(next1);
    }
}

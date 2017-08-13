package com.example.abhishek.restoran;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Payment extends AppCompatActivity implements View.OnClickListener {

    private RadioButton radio1,radio2,radio3;
    private Spinner spinner1,spinner2;
    private RadioGroup radioGroup;
    private TextView textView1,textView3,textView5,textView7;
    private String name;
    private long price;
    private int quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        radio1 = (RadioButton)findViewById(R.id.rb1);
        radio2 = (RadioButton)findViewById(R.id.rb2);
        radio3 = (RadioButton)findViewById(R.id.rb3);

        spinner1 = (Spinner)findViewById(R.id.sp1);
        spinner2 = (Spinner)findViewById(R.id.sp2);

        List<String> list=new ArrayList<>();
        list.add("SBI");
        list.add("CUB");
        list.add("PNB");
        list.add("Union bank");
        list.add("Axis bank");
        list.add("ICICI bank");
        list.add("Kotak mahindra bank");

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner1.setAdapter(adapter);
        spinner2.setAdapter(adapter);

        spinner1.getSelectedView();
        spinner1.setEnabled(false);

        spinner2.getSelectedView();
        spinner2.setEnabled(false);

        textView1 = (TextView)findViewById(R.id.tv1);
        textView3 = (TextView)findViewById(R.id.tv3);
        textView5 = (TextView)findViewById(R.id.tv5);
        textView7 = (TextView)findViewById(R.id.tv7);

        if (getIntent() == null || getIntent().getExtras() == null)
        {
            return;
        }
        name = getIntent().getStringExtra("com.example.abhishek.restoran.nameExtra");
        textView1.setText(name);
        price = getIntent().getLongExtra("com.example.abhishek.restoran.priceExtra",10);
        textView5.setText(String.valueOf(price));
        quantity = getIntent().getIntExtra("com.example.abhishek.restoran.quantityExtra", 1);
        textView3.setText(String.valueOf(quantity));
        int a=(int)price;
        int quan=a*quantity;
        textView7.setText(String.valueOf(quan));

        Button buttonpay=(Button)findViewById(R.id.paybt);
        buttonpay.setOnClickListener(this);

    }

    public void onClickListener(View view)
    {
        radioGroup = (RadioGroup) findViewById(R.id.rg);
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.rb1:
                if (checked) {
                    spinner1.setEnabled(true);
                    spinner2.setEnabled(false);
                }
                else {
                    Toast.makeText(this, "Select a bank", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.rb2:
                if (checked) {
                    spinner2.setEnabled(true);
                    spinner1.setEnabled(false);
                }
                else {
                    Toast.makeText(this, "Select a bank", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.rb3:
                if (checked) {
                    spinner1.setEnabled(false);
                    spinner2.setEnabled(false);
                    Toast.makeText(this, "COD", Toast.LENGTH_SHORT).show();
                    break;
                }
        }
    }

    @Override
    public void onClick(View view) {
        if (radio1.isChecked()|| radio2.isChecked()|| radio3.isChecked())
        {
            Intent next = new Intent(Payment.this, Meal_Book.class);
            next.putExtra("com.example.abhishek.restoran.nameExtra", name);
            next.putExtra("com.example.abhishek.restoran.priceExtra", price);
            next.putExtra("com.example.abhishek.restoran.quantityExtra", quantity);
            startActivity(next);
        }
        else
        {
            Toast.makeText(this, "Select a payment Option", Toast.LENGTH_SHORT).show();
        }
    }
}

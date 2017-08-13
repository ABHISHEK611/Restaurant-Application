package com.example.abhishek.restoran;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Random;

public class Meal_Book extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal__book);

        TextView tv2 = (TextView) findViewById(R.id.tv2);
        TextView tv4 = (TextView) findViewById(R.id.tv4);
        TextView tv6 = (TextView) findViewById(R.id.tv6);
        TextView tv8 = (TextView) findViewById(R.id.tv8);
        TextView tv10=(TextView) findViewById(R.id.tv10);
        final TextView tv9=(TextView) findViewById(R.id.tv9);


        if (getIntent() == null || getIntent().getExtras() == null) {
            return;
        }
        String name = getIntent().getStringExtra("com.example.abhishek.restoran.nameExtra");
        tv2.setText(name);
        long price = getIntent().getLongExtra("com.example.abhishek.restoran.priceExtra",10);
        tv4.setText(String.valueOf(price));
        int quantity = getIntent().getIntExtra("com.example.abhishek.restoran.quantityExtra", 1);
        tv6.setText(String.valueOf(quantity));
        int a=(int)price;
        int quan=a*quantity;
        tv8.setText(String.valueOf(quan));
        int oid=getRandomNumberInRange(1000,9999);
        tv10.setText(String.valueOf(oid));

        FirebaseDatabase db=FirebaseDatabase.getInstance();
        DatabaseReference msgRef=db.getReference("Order");

        HashMap<String,Object> data=new HashMap<>();
        data.put("FoodItem",name);
        data.put("Price",price);
        data.put("Quantity",quantity);
        data.put("Total_Amount",quan);
        data.put("OrderId",oid);

        msgRef.push().setValue(data, new DatabaseReference.CompletionListener()
        {
            @Override
            public void onComplete(DatabaseError derr, DatabaseReference drefer)
            {
                if(derr==null)
                {
                    Toast.makeText(Meal_Book.this, "Remember the Order Id for Future References", Toast.LENGTH_LONG).show();
                    tv9.setText("You'll be receiving the Order within 1hr.\nThank You for letting us serving you.");
                }
                else
                {
                    tv9.setText(derr.getMessage());
                }
            }
        });
    }
    int getRandomNumberInRange(int min,int max)
    {
        if(min>=max)
        {
            throw new IllegalArgumentException("Max. must be greater than Min.");
        }
        Random r=new Random();
        return r.nextInt((max-min)+1)+min;
    }
}

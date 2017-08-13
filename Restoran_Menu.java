package com.example.abhishek.restoran;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class Restoran_Menu extends AppCompatActivity {

    ArrayList<MenuModel> menulist = new ArrayList<>();
    private RecyclerView rvmenulist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restoran__menu);


        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference ref = db.getReference();

        rvmenulist = (RecyclerView) findViewById(R.id.rvmenulist);
        final BindingRecyclerandCard foodlist = new BindingRecyclerandCard();
        rvmenulist.setLayoutManager(new LinearLayoutManager(this));
        rvmenulist.setAdapter(foodlist);

        final ProgressBar pbloader = (ProgressBar) findViewById(R.id.pbloader);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.hasChildren()) {
                    DataSnapshot ds = snapshot.child("Menu_List"); //yeh poora nhi likhte iter likh ke Ctrl+Alt+ kch karte hai toh isse enhanced for loop aajayega
                    //brackets ke bad type 'iter' and then press ctrl+alt and then jo iterable wala option de use select karo and the enhanced for loop ke andar ka part khud se ajaega
                    for (DataSnapshot fooditem : ds.getChildren()) {
                        menulist.add(new MenuModel(fooditem));
                        foodlist.notifyDataSetChanged();
                        //Toast.makeText(Restoran_Menu.this, "DataLoaded", Toast.LENGTH_SHORT).show();
                    }
                    pbloader.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(Restoran_Menu.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                pbloader.setVisibility(View.GONE);
            }
        });

    }

    class MenuModel
    {
        DataSnapshot item;

        public MenuModel(DataSnapshot item)
        {
            this.item = item;
        }
    }

    class Holder extends RecyclerView.ViewHolder {

        public final TextView tvname;
        public final TextView tvprice;
        public final CardView cv;


        public Holder(final View itemView) {
            super(itemView);
            tvname = (TextView) itemView.findViewById(R.id.tvname);
            tvprice = (TextView) itemView.findViewById(R.id.tvprice);
            cv = (CardView) itemView.findViewById(R.id.cv);
        }
    }

    class BindingRecyclerandCard extends RecyclerView.Adapter<Holder> {

        @Override
        public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.single_row_design, parent, false);
            return new Holder(view);
        }

        @Override
        public void onBindViewHolder(Holder holder, int position) {
            MenuModel menuModel = menulist.get(position);
            final String name = menuModel.item.child("Name").getValue(String.class);
            holder.tvname.setText(name);
            final Long price = menuModel.item.child("Price").getValue(Long.class);
            holder.tvprice.setText(String.valueOf(price));
            holder.cv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog ad = new AlertDialog.Builder(Restoran_Menu.this).create();
                    View v = LayoutInflater.from(Restoran_Menu.this).inflate(R.layout.custom_alert_dialog, null, false);
                    final EditText et1 = (EditText) v.findViewById(R.id.et);
                    TextView tv = (TextView) v.findViewById(R.id.tv);
                    TextView tv1 = (TextView) v.findViewById(R.id.tv1);
                    tv.setText(name);
                    tv1.setText(String.valueOf(price));
                    ad.setView(v);
                    ad.setTitle("Book your meal:");
                    ad.setIcon(R.drawable.ic_food_item_black_24px);
                    ad.setButton(DialogInterface.BUTTON_POSITIVE, "Book", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            String qtyStr = et1.getText().toString();
                            if (qtyStr.isEmpty()) {
                                et1.setError("wrong value");
                                return;
                            }
                            final int quant = Integer.parseInt(qtyStr);


                            Intent next = new Intent(Restoran_Menu.this, Payment.class);
                            next.putExtra("com.example.abhishek.restoran.nameExtra", name);
                            next.putExtra("com.example.abhishek.restoran.priceExtra", price);
                            next.putExtra("com.example.abhishek.restoran.quantityExtra", quant);
                            startActivity(next);

                        }
                    });
                    ad.show();
                }
            });
            //holder.cv.set aise karke cardview ke item ko call karo
            //alert dialog with edit text
        }

        @Override
        public int getItemCount() {
            return menulist.size();
        }
    }
}
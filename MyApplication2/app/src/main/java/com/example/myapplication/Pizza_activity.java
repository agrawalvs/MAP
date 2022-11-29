package com.example.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.ArrayList;

public class  Pizza_activity extends AppCompatActivity {
    ArrayList<String> itemq = new ArrayList<String>();
    ArrayList<pizza> list1 = new ArrayList<pizza>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pizza);

        LinearLayout l1 = findViewById(R.id.p1);
        LinearLayout l2 = findViewById(R.id.p2);

        //pop-up menu
        l1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu p1 = new PopupMenu(Pizza_activity.this,l1);
                p1.getMenuInflater().inflate(R.menu.menu2, p1.getMenu());
                p1.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if(item.getItemId()==R.id.add){
                            TextView title = findViewById(R.id.p1_head);
                            TextView cost = findViewById(R.id.p1_cost);
                            String titlee =title.getText().toString();
                            float costt = Float.parseFloat(cost.getText().toString());
//                            addtocart(titlee, costt, itemq);
                            list1.add(new pizza(titlee,costt,itemq));
                            for (pizza i : list1){
                                Log.d("array list", i.title+i.cost+String.valueOf(i.customization));
                            }

                            Toast.makeText(getApplicationContext(),item.getTitle(),Toast.LENGTH_LONG).show();
                        }
                        else if(item.getItemId()==R.id.Cus){
                            String[] listItems = getResources().getStringArray(R.array.shopping_item1);
                            customize(listItems);
                        }else{
                            return false;
                        }
                        return true;
                    }
                });
                p1.show();
            }
        });

        // Context menu
        registerForContextMenu(l2);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu2, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.add){
            TextView title = findViewById(R.id.p2_head);
            TextView cost = findViewById(R.id.p2_cost);
            String titlee =title.getText().toString();
            float costt = Float.parseFloat(cost.getText().toString());
//                            addtocart(titlee, costt, itemq);
            list1.add(new pizza(titlee,costt,itemq));
            for (pizza i : list1){
                Log.d("array list", i.title+i.cost+String.valueOf(i.customization));
            }
        }
        else if(item.getItemId()==R.id.Cus){
            String[] listItems = getResources().getStringArray(R.array.shopping_item2);
            customize(listItems);
        }else{
            return false;
        }
        return true;
    }

    public void customize(String[] shopping_item){
        String [] listItems = shopping_item;
        ArrayList<Integer> mUserItems = new ArrayList<>();
        boolean[] checkedItems = new boolean[listItems.length];
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(Pizza_activity.this);
        mBuilder.setTitle("Customization");
        mBuilder.setMultiChoiceItems(listItems, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int position, boolean isChecked) {

                if (isChecked) {
                            if (!mUserItems.contains(position)) {
                                mUserItems.add(position);
                            }
                        } else if (mUserItems.contains(position)) {
                            mUserItems.remove(position);
                        }
            }
        });

        mBuilder.setCancelable(false);
        mBuilder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                for (int i = 0; i < mUserItems.size(); i++) {
                    itemq.add(listItems[mUserItems.get(i)]);
                }
            }
        });
        mBuilder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        AlertDialog mDialog = mBuilder.create();
        mDialog.show();

    }

//    public void addtocart(String title, float cost, String [] listt){
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.cart:
                ArrayList<Object> playerObjects = new ArrayList<Object>();

                for(pizza a : list1){
                    playerObjects.add((Object)a);
                }
                Intent iintent = new Intent(Pizza_activity.this, cart_activity.class);
                Bundle args = new Bundle();
                args.putSerializable("ARRAYLIST",(Serializable)playerObjects);
                iintent.putExtra("BUNDLE",args);
                startActivity(iintent);
//                Toast.makeText(Pizza_activity.this, "cart",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Pizza:
                Toast.makeText(Pizza_activity.this, "pizza",Toast.LENGTH_SHORT).show();
                Intent pizza_intent = new Intent(Pizza_activity.this,Pizza_activity.class);
                startActivity(pizza_intent);
                return true;
            case R.id.Pasta:
                Toast.makeText(Pizza_activity.this, "pasta",Toast.LENGTH_SHORT).show();
                Intent pasta_intent = new Intent(Pizza_activity.this,Pasta_activity.class);
                startActivity(pasta_intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}



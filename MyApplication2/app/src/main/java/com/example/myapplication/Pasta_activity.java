package com.example.myapplication;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Pasta_activity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pasta);

        LinearLayout l1 = findViewById(R.id.p1);
        LinearLayout l2 = findViewById(R.id.p2);

        //pop-up menu
        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu p1 = new PopupMenu(Pasta_activity.this,l1);
                p1.getMenuInflater().inflate(R.menu.menu2, p1.getMenu());
                p1.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        // Toast message on menu item clicked
                        Toast.makeText(Pasta_activity.this, "You Clicked " + menuItem.getTitle(), Toast.LENGTH_SHORT).show();
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
            Toast.makeText(getApplicationContext(),item.getTitle(),Toast.LENGTH_LONG).show();
        }
        else if(item.getItemId()==R.id.Cus){
            Toast.makeText(getApplicationContext(),item.getTitle(),Toast.LENGTH_LONG).show();
        }else{
            return false;
        }
        return true;
    }
}

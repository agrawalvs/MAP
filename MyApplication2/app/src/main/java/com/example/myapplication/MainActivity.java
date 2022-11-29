package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

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
                Toast.makeText(MainActivity.this, "cart",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Pizza:
                Toast.makeText(MainActivity.this, "pizza",Toast.LENGTH_SHORT).show();
                Intent pizza_intent = new Intent(MainActivity.this,Pizza_activity.class);
                startActivity(pizza_intent);
                return true;
            case R.id.Pasta:
                Toast.makeText(MainActivity.this, "pasta",Toast.LENGTH_SHORT).show();
                Intent pasta_intent = new Intent(MainActivity.this,Pasta_activity.class);
                startActivity(pasta_intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
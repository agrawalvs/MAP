package com.example.myapplication;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import java.util.ArrayList;
import java.util.Calendar;

public class cart_activity extends AppCompatActivity{

    final String CHANNEL_ID="channel1";
    ArrayList<pizza> addedTocart = new ArrayList<pizza>();
    TextView totalamount;
    Button placeorder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart);
        totalamount = findViewById(R.id.textView2);
        totalamount.setVisibility(View.GONE);
        placeorder = findViewById(R.id.button);
        placeorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNotification();
            }
        });

        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        ArrayList<Object> players = null;
        players = (ArrayList<Object>) args.getSerializable("ARRAYLIST");


        for (Object objs : players) {
            addedTocart.add((pizza) objs);
        }


        if (addedTocart != null) {
            double totalAmount = 0;
            LinearLayout llMain = findViewById(R.id.cart_list);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT
            );

            for (pizza p : addedTocart) {
                Log.d("data", String.valueOf(p.title));
                Log.d("data", String.valueOf(p.cost));
                Log.d("data", String.valueOf(p.customization));

                TextView name = new TextView(this);
                TextView amountdetail = new TextView(this);
                TextView customisation = new TextView(this);
                TextView line = new TextView(this);

                name.setText("Name : " + String.valueOf(p.title));
                amountdetail.setText("Amount : " + String.valueOf(p.cost));
                customisation.setText("Customisations :" + String.valueOf(p.customization));
                line.setText("-------------------------------------------------------------------");
                name.setLayoutParams(params);
                amountdetail.setLayoutParams(params);
                customisation.setLayoutParams(params);
                llMain.addView(name);
                llMain.addView(amountdetail);
                llMain.addView(customisation);
                llMain.addView(line);
                totalAmount += p.cost;
            }

            totalamount.setVisibility(View.VISIBLE);
            totalamount.setText("Total Amount : " + totalAmount);
        }
    }
        void createNotification()
    {
            NotificationManager
                    nm=(NotificationManager)this.getSystemService(NOTIFICATION_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                CharSequence name = "Notification Example";
                String description = "This is a Demo Notification";
                int importance = NotificationManager.IMPORTANCE_DEFAULT;
                NotificationChannel channel = new
                        NotificationChannel(CHANNEL_ID, name, importance);
                channel.setDescription(description);
                nm.createNotificationChannel(channel);
            }
            NotificationCompat.Builder builder =
                    new NotificationCompat.Builder(this,CHANNEL_ID)
                            .setSmallIcon(R.drawable.ic_baseline_shopping_cart_24)
                            .setContentTitle("Your order has been placed successfully.")
                    ;
            builder.setAutoCancel(false);

        Intent intent = new Intent(getApplicationContext(), Notificationn.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        Calendar date = Calendar.getInstance();
        long timeInSecs = date.getTimeInMillis() + 30*60*1000;

        intent.putExtra("time",timeInSecs);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_CANCEL_CURRENT);
        builder.setContentIntent(pi);
        nm.notify(0, builder.build());
        };
}

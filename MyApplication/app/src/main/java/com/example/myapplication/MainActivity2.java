package com.example.myapplication;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MainActivity2 extends AppCompatActivity {
    Button helpline;
    Button feedback;
    Button loc;
    Button query;
    Button download;
    TextView fb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("name");
        String email = bundle.getString("email");
        String mob_no = bundle.getString("mob_no");
        String age = bundle.getString("age");
        String gender = bundle.getString("Gender");
        String frequency = bundle.getString("frequency");
        String interests = bundle.getString("interests");

        String details="Name : "+name+"\n"+"Email : "+email+"\n"+"Mobile No. : "+mob_no+
                "\n"+"Age : "+age+"\n"+"Gender : "+gender+"\n"+"frquency : "+frequency+
                "\n"+"Intrests : "+ interests;

        fb = findViewById(R.id.tvfr);
        TextView textView = findViewById(R.id.textView);
        textView.setText(details);

        helpline = findViewById(R.id.button);
        helpline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:9373899610"));

                if (ActivityCompat.checkSelfPermission(MainActivity2.this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                Toast.makeText(MainActivity2.this,"clicked on helpline", Toast.LENGTH_SHORT).show();
                startActivity(callIntent);
            }
        });

//        feedback = findViewById(R.id.button2);
//        feedback.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent viewIntent =
//                        new Intent("android.intent.action.VIEW",
//                                Uri.parse("https://docs.google.com/forms/d/e/1FAIpQLSfmYbkjPDj8r5Wdr0JGonvJ0J988jXNIDwea7dSHI78nAhGYw/viewform?usp=sf_link"));
//                startActivity(viewIntent);
//            }
//        });

        loc = findViewById(R.id.button3);
        loc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri gmmIntentUri = Uri.parse("google.navigation:q=21.17665890215609, 79.06163034409083&mode=d");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });

        query = findViewById(R.id.button4);
        query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) throws ActivityNotFoundException {
                Intent selectorIntent = new Intent(Intent.ACTION_SENDTO);
                selectorIntent.setData(Uri.parse("mailto:"));

                final Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"address@mail.com"});
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "The subject");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "The email body");
                emailIntent.setSelector( selectorIntent );

                startActivity(emailIntent);
            }
        });

        download = findViewById(R.id.button5);
        download.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.tictactoe.wintrino"));
                startActivity(intent);
            }
        });
    }

    public void ShowDialog(View view) {
        final AlertDialog.Builder popDialog = new AlertDialog.Builder(this);

        LinearLayout linearLayout = new LinearLayout(this);
        final RatingBar rating = new RatingBar(this);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        rating.setLayoutParams(lp);
        rating.setNumStars(5);
        rating.setStepSize(1);

        //add ratingBar to linearLayout
        linearLayout.addView(rating);


        popDialog.setIcon(android.R.drawable.btn_star_big_on);
        popDialog.setTitle("Add Rating: ");

        //add linearLayout to dailog
        popDialog.setView(linearLayout);



        rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                System.out.println("Rated val:"+v);
            }
        });



        // Button OK
        popDialog.setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                fb.setText("The given feedback :" + String.valueOf(rating.getProgress()));
                                dialog.dismiss();
                            }

                        })

                // Button Cancel
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        popDialog.create();
        popDialog.show();
    }
}




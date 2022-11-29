package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.icu.util.Calendar;
import android.os.Build;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

public class MainActivity extends AppCompatActivity {
    Button bt_date;
    String date;
    TextView Age;
    RadioGroup rg;
    RadioButton rgb;
    Spinner spinner;
    ListView lview;
    CheckBox tnc;
    Button submit;

    String gender;
    String freq;
    String selected;
    String age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt_date = findViewById(R.id.bt_date);
        Age = findViewById(R.id.Age);
        rg = (RadioGroup) findViewById(R.id.radioGroup);
        spinner = (Spinner) findViewById(R.id.spinner1);
        lview = (ListView) findViewById(R.id.Listv1);
        tnc = ( CheckBox ) findViewById( R.id.chk1 );
        submit = (Button) findViewById(R.id.btn2);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                freq = spinner.getSelectedItem().toString();
                Toast.makeText(MainActivity.this,freq, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                freq = "Nothing Selected";
            }
        });
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.frequency, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);



        lview.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.intrests, android.R.layout.simple_list_item_multiple_choice);
        lview.setAdapter(adapter2);
        String[] intrests = getResources().getStringArray(R.array.intrests);
        lview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView arg0, View arg1, int arg2,long arg3)
            {
                selected = "";
                SparseBooleanArray sbArray = lview.getCheckedItemPositions();
                for (int i = 0; i < sbArray.size(); i++) {
                    int key = sbArray.keyAt(i);
                    if (sbArray.get(key)) {
//                        Log.d(LOG_TAG, intrests[key]);
                        if(selected.isEmpty()) {
                            selected = selected + intrests[key];
                        }
                        else
                            selected = selected + "," + intrests[key];
                    }
                }
                Toast.makeText(MainActivity.this,selected, Toast.LENGTH_SHORT).show();
            }
        });



        tnc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tnc.isChecked())
                    submit.setEnabled(true);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, MainActivity2.class);

                final EditText name = findViewById(R.id.nameET);
                String namee = name.getText().toString();
                final EditText email = findViewById(R.id.emailidET);
                String emaill = email.getText().toString();
                final EditText mob_no = findViewById(R.id.MnoET);
                String mob_noo = mob_no.getText().toString();
                Bundle bundle = new Bundle();
                bundle.putString("name", namee);
                bundle.putString("email",emaill);
                bundle.putString("mob_no",mob_noo);
                bundle.putString("age",age);
                bundle.putString("Gender",gender);
                bundle.putString("frequency",freq);
                bundle.putString("interests",selected);
                myIntent.putExtras(bundle);
                MainActivity.this.startActivity(myIntent);
            }
        });
    }





    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.mRB:
                if (checked) {
                    gender = "male";
                    Toast.makeText(MainActivity.this, gender, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.fRB:
                if (checked) {
                    gender = "female";
                    Toast.makeText(MainActivity.this,gender, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.oRB:
                if (checked) {
                    gender = "other";
                    Toast.makeText(MainActivity.this,gender, Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void setDate (View view){
        final Calendar c = Calendar.getInstance();
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        int mMonth = c.get(Calendar.MONTH);
        int mYear = c.get(Calendar.YEAR);
        DatePickerDialog dateDialog = new
                DatePickerDialog(view.getContext(), datePickerListener, mYear, mMonth, mDay);
        dateDialog.getDatePicker().setMaxDate(new Date().getTime());
        dateDialog.show();
    }

    private final DatePickerDialog.OnDateSetListener
            datePickerListener = new DatePickerDialog.OnDateSetListener() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                    Calendar c = Calendar.getInstance();
                    c.set(Calendar.YEAR, year);
                    c.set(Calendar.MONTH, month);
                    c.set(Calendar.DAY_OF_MONTH, day);
                    month+=1;
                    date = day + "/" + month + "/" + year;
                    Age.setText(Integer.toString(calculateAge(c.getTimeInMillis()))+" yrs");
                    age = Integer.toString(calculateAge(c.getTimeInMillis()))+" yrs";
                }
            };



    @RequiresApi(api = Build.VERSION_CODES.N)
    int calculateAge(long date){
        int age;
        Calendar dob = Calendar.getInstance();
        dob.setTimeInMillis(date);
        Calendar today = Calendar.getInstance();
        age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
        if(today.get(Calendar.DAY_OF_MONTH) < dob.get(Calendar.DAY_OF_MONTH)){
            age--;
        }
        return age;
    }
}
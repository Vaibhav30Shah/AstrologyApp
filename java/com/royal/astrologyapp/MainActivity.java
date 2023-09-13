package com.royal.astrologyapp;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    Spinner cityspiner;
    TextView tvdate, tvtime;
    ImageView date, time;
    Button btn_submit;
    EditText edt_fname, edt_mname, edt_lname, edt_email, edt_pin;
    RadioGroup radioGroup;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    private int day, month, year;
    private int hour;
    private int minute;
    String city[] = {"----Select City----", "Ahmedabad", "Surat", "Vadodara", "Maheshana"};
    String strRating, strdate, strtime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        //validation
        edt_fname = findViewById(R.id.edt_fname);
        edt_mname = findViewById(R.id.edt_mname);
        edt_lname = findViewById(R.id.edt_Lname);
        edt_email = findViewById(R.id.edt_Email);
        edt_pin = findViewById(R.id.edt_pin);
        btn_submit = findViewById(R.id.btn_submit);
        radioGroup = findViewById(R.id.radgroup);

        cityspiner = findViewById(R.id.spinner_city);
        tvdate = findViewById(R.id.tv_date);
        date = findViewById(R.id.btn_date);
        tvtime = findViewById(R.id.tv_time);
        time = findViewById(R.id.btn_Time);

        //Date
        Calendar calendar = Calendar.getInstance();
        day = calendar.get(Calendar.DAY_OF_MONTH);
        month = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);

        //time
        hour = calendar.get(Calendar.HOUR);
        minute = calendar.get(Calendar.MINUTE);
        onClickListener();

    }

    private void onClickListener() {
        //time Set
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                        tvtime.setText(hour + ":" + minute);
                    }
                }, hour, minute, false);
                timePickerDialog.show();
            }
        });
        //Date Set
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int dayofmonth) {
                        tvdate.setText(dayofmonth + "/" + (month + 1) + "/" + year);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
        //city Set
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, city) {

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                TextView tvdata = (TextView) super.getDropDownView(position, convertView, parent);
                if (position == 0) {
                    tvdata.setTextColor(Color.YELLOW);
                } else {
                    tvdata.setTextColor(Color.YELLOW);
                }
                return tvdata;
            }
        };
        cityspiner.setAdapter(arrayAdapter);

        //Validation msg
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strFn = edt_fname.getText().toString();
                String strMn = edt_mname.getText().toString();
                String strLn = edt_lname.getText().toString();
                strdate=tvdate.getText().toString();
                strtime=tvtime.getText().toString();
                String strEmail = edt_email.getText().toString();
                int SelectedId = radioGroup.getCheckedRadioButtonId();
                validation(strFn,strMn,strLn,strEmail,SelectedId);

            }
        });

    }

    private void validation(String strFn, String strMn, String strLn, String strEmail, int selectedId) {

        if (strFn.equals("")) {
            Toast.makeText(MainActivity.this, "Please enter first Name", Toast.LENGTH_SHORT).show();
        } else if (strMn.equals("")) {
            Toast.makeText(MainActivity.this, "Please enter Middle Name", Toast.LENGTH_SHORT).show();
        } else if (strLn.equals("")) {
            Toast.makeText(MainActivity.this, "Please enter Last Name", Toast.LENGTH_SHORT).show();
        } else if (strEmail.equals("")) {
            Toast.makeText(MainActivity.this, "Please enter Email", Toast.LENGTH_SHORT).show();
        } else if (!strEmail.matches(emailPattern)) {
            Toast.makeText(MainActivity.this, "Please enter valid Email", Toast.LENGTH_SHORT).show();
        } else if (selectedId == -1) {
            Toast.makeText(MainActivity.this, "Please Select Gender", Toast.LENGTH_SHORT).show();
        } else if (year > 2023 || year <= 1921) {
            Toast.makeText(MainActivity.this, "Please Enter Valid Birth Year", Toast.LENGTH_SHORT).show();
        } else if (edt_pin.length() > 6 || edt_pin.length() < 6 || edt_pin.length() == 0) {
            Toast.makeText(MainActivity.this, "Please Enter Valid Pincode", Toast.LENGTH_SHORT).show();
        } else {

            finalDataDisplay(strFn,strMn, strLn,strEmail);

        }

    }

    private void finalDataDisplay(String strFn, String strMn, String strLn, String strEmail) {

        LayoutInflater layoutInflater = getLayoutInflater();
        View myview = layoutInflater.inflate(R.layout.activity_rating_box, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        AlertDialog alertDialog = builder.create();
        Button btnRSubmit = myview.findViewById(R.id.btn_rsubmit);
        RatingBar ratingBar = myview.findViewById(R.id.ratingbar);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {

            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {

                strRating = String.valueOf(v);

            }
        });

        btnRSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (alertDialog.isShowing()){
                    alertDialog.dismiss();
                }
                Intent i = new Intent(MainActivity.this, KundliDisplay.class);
                i.putExtra("KEY_FN",strFn);
                i.putExtra("KEY_LN",strLn);
                i.putExtra("KEY_RATING",strRating);
                i.putExtra("KEY_TIME",strtime);
                i.putExtra("KEY_DATE",strdate);
                startActivity(i);

            }
        });

        alertDialog.setView(myview);
        alertDialog.show();
    }

}
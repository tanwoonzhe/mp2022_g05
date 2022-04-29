package com.example.laibrary.ui.book;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.laibrary.R;

public class BookSetDate extends AppCompatActivity {

    Button set;
    String date, sday, smonth, syear;
    DatePicker datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_set_date);

        datePicker = findViewById(R.id.datepicker);
        set = findViewById(R.id.btnSet);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int day = datePicker.getDayOfMonth();
                int month = datePicker.getMonth() + 1;
                int year = datePicker.getYear();

                sday = String.valueOf(day);
                smonth = String.valueOf(month);
                syear = String.valueOf(year);

                date = String.valueOf(day) + "/" + String.valueOf(month) + "/" + String.valueOf(year);
                Toast.makeText(getBaseContext(), "result: " + date, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(BookSetDate.this, Booking.class);
                intent.putExtra("keyrentdate", date);
                intent.putExtra("keyrentday", sday);
                intent.putExtra("keyrentmonth", smonth);
                intent.putExtra("keyrentyear", syear);

                startActivity(intent);

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

}
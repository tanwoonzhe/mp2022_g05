package com.example.laibrary.ui.book;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.laibrary.R;

public class Booking extends AppCompatActivity {

    private EditText fullName,ic,pNumber,quantity,rentDate;
    private Spinner BukuSpinner;
    private Button Bk;
    String []Book;
    int index;
    String  bookName,ifullname,iic,ipnumber,iquantity,irentdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        fullName = findViewById(R.id.etFullName);
        ic = findViewById(R.id.etIc);
        pNumber = findViewById(R.id.etPnumber);
        quantity = findViewById(R.id.etQuantity);
        rentDate = findViewById(R.id.etRent);
        BukuSpinner = findViewById(R.id.namaBuku);
        Bk = findViewById(R.id.btnBook);


        Book = getResources().getStringArray(R.array.Buku_array);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ArrayAdapter <String>adapter=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,Book);
        BukuSpinner.setAdapter(adapter);
        BukuSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                 index=adapterView.getSelectedItemPosition();
                bookName = Book[index];
                Toast.makeText(getBaseContext(),"book= "+Book[index],Toast.LENGTH_SHORT).show();

            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Bk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ifullname = fullName.getText().toString();
                iic = ic.getText().toString();
                ipnumber = pNumber.getText().toString();
                iquantity = quantity.getText().toString();
                irentdate = rentDate.getText().toString();

                Intent intent = new Intent(Booking.this,ComfirmBooking.class);
                intent.putExtra("keyfullname",ifullname);
                intent.putExtra("keyic",iic);
                intent.putExtra("keypnumber",ipnumber);
                intent.putExtra("keyquantity",iquantity);
                intent.putExtra("keyrentdate",irentdate);
                intent.putExtra("keybookname",bookName);

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
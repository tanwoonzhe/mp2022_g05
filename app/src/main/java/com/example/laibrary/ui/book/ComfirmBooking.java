package com.example.laibrary.ui.book;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.laibrary.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ComfirmBooking extends AppCompatActivity {

    private TextView fullname, icnumber,pnumber,nbook,quantity,rentdate,turndate,total;
    private Button comfirm, change;
    int nquantity;
    double price;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    String ifullname,iicnumber,ipnumber,inbook,iquantity,irentdate,iprice;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comfirm_booking);

        fullname = findViewById(R.id.tvFullName);
        icnumber = findViewById(R.id.tvIc);
        pnumber = findViewById(R.id.tvPnumber);
        nbook = findViewById(R.id.tvbname);
        quantity = findViewById(R.id.tvQuantity);
        rentdate = findViewById(R.id.tvRent);
        turndate = findViewById(R.id.tvReTurn);
        total = findViewById(R.id.tvTotal);
        comfirm = findViewById(R.id.btnComfirm);
        change = findViewById(R.id.btnChange);

        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ifullname = getIntent().getStringExtra("keyfullname");
        iicnumber = getIntent().getStringExtra("keyic");
        ipnumber =  getIntent().getStringExtra("keypnumber");
        inbook = getIntent().getStringExtra("keybookname");
        iquantity = getIntent().getStringExtra("keyquantity");
        irentdate = getIntent().getStringExtra("keyrentdate");

        fullname.setText(ifullname);
        icnumber.setText(iicnumber);
        pnumber.setText(ipnumber);
        nbook.setText(inbook);
        quantity.setText(iquantity);
        rentdate.setText(irentdate);

        Intent intent = getIntent();

        String id = FirebaseAuth.getInstance().getCurrentUser().getUid();

        databaseReference = FirebaseDatabase.getInstance().getReference("Booking").child(id);


        nquantity = Integer.parseInt(iquantity);

        if (inbook.equals("Rainforest Creatures of Malaysia")){
            price = nquantity*2;

        }else if (inbook.equals("Pergi Covid-19! Jangan Datang Lagi!")){
            price = nquantity*3;

        }else if (inbook.equals("Kelembai: Ceritera Tidak Terhikayat")){
            price = nquantity*4;

        }else if (inbook.equals("Hendak Ke Mana Gagak?")){
            price = nquantity*2.2;

        }else if (inbook.equals("Damia dan Kuda Kepang Ajaib")){
            price = nquantity*3.2;

        }else if (inbook.equals("Music and My Friends")){
            price = nquantity*4;

        }else if (inbook.equals("Gaja Loves The Sea")){
            price = nquantity*5;

        }else if (inbook.equals("Misi Melur")){
            price = nquantity*1;

        }else if (inbook.equals("Keris: Warisan Melayu")){
            price = nquantity*2;

        }else if (inbook.equals("63 Tumbuhan Ubatan")){
            price = nquantity*6;

        }

        iprice = String.format("%.2f",price);
        total.setText(iprice);

        intent.putExtra("keyTotalPrice",iprice);

        comfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = databaseReference.push().getKey();
                DatabaseReference databaseReference = firebaseDatabase.getReference("Booking Detail").child(firebaseAuth.getUid());
                BookingDetail bookingDetail = new BookingDetail(id,ifullname,iicnumber,ipnumber,inbook,iquantity,irentdate,iprice);
                intent.putExtra("keybookingid",id);
                databaseReference.child(id).setValue(bookingDetail);

                finish();
                startActivity(new Intent(ComfirmBooking.this,BookPayment.class));
            }
        });

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ComfirmBooking.this, Booking.class));
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
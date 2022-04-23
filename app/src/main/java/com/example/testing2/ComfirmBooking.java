package com.example.testing2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ComfirmBooking extends AppCompatActivity {

    private TextView fullname, icnumber,pnumber,nbook,quantity,rentdate,turndate,total;
    private Button comfirm;
    int nquantity;
    double price;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    String ifullname,iicnumber,ipnumber,inbook,iquantity,irentdate,iprice;

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

        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

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

        comfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseReference databaseReference = firebaseDatabase.getReference("Booking Detail").child(firebaseAuth.getUid());
                BookingDetail bookingDetail = new BookingDetail(ifullname,iicnumber,ipnumber,inbook,iquantity,irentdate,iprice);

                databaseReference.setValue(bookingDetail);

                finish();
                startActivity(new Intent(ComfirmBooking.this,SecondActivity.class));
            }
        });

    }
}
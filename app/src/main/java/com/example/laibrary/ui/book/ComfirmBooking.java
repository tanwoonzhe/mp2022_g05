package com.example.laibrary.ui.book;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
    String ifullname,iicnumber,ipnumber,inbook,iquantity,irentdate,iprice, irentday, irentmonth, irentyear, istatus;
    DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comfirm_booking);

        fullname = findViewById(R.id.tvFullName);
        icnumber = findViewById(R.id.tvIc);
        pnumber = findViewById(R.id.tvPnumber);
        nbook = findViewById(R.id.tvbname);
        quantity = findViewById(R.id.tvQuantity);
        total = findViewById(R.id.tvTotal);
        comfirm = findViewById(R.id.btnComfirm);
        change = findViewById(R.id.btnChange);
        rentdate = findViewById(R.id.tvRent);

        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseDatabase2 = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ifullname = getIntent().getStringExtra("keyfullname");
        iicnumber = getIntent().getStringExtra("keyic");
        ipnumber =  getIntent().getStringExtra("keypnumber");
        inbook = getIntent().getStringExtra("keybookname");
        iquantity = getIntent().getStringExtra("keyquantity");
        irentdate = getIntent().getStringExtra("keyrentdate");
        irentyear = getIntent().getStringExtra("keyrentyear");
        irentday = getIntent().getStringExtra("keyrentday");
        irentmonth = getIntent().getStringExtra("keyrentmonth");

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

        comfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String id = databaseReference.push().getKey();
                istatus = "1";
                DatabaseReference databasedate = firebaseDatabase2.getReference("Date").child(firebaseAuth.getUid());
                DateDatabase dateDatabase = new DateDatabase(irentday, irentmonth, irentyear, istatus);
                databasedate.setValue(dateDatabase);

                DatabaseReference databaseReference = firebaseDatabase.getReference("Booking Info").
                        child(firebaseAuth.getUid()).child(id);
                BookingDetail bookingDetail = new BookingDetail();
                bookingDetail.setRentday(irentday);
                bookingDetail.setRentmonth(irentmonth);
                bookingDetail.setRentyear(irentyear);
                bookingDetail.setBookingID(id);
                bookingDetail.setFullname(ifullname);
                bookingDetail.setIcnumber(iicnumber);
                bookingDetail.setPhonenumber(ipnumber);
                bookingDetail.setNamebook(inbook);
                bookingDetail.setQuantity(iquantity);
                bookingDetail.setRentdate(irentdate);
                bookingDetail.setTotal(iprice);

                databaseReference.setValue(bookingDetail);

                Intent intent = new Intent(ComfirmBooking.this,BookPayment.class);
                intent.putExtra("keybookingid",id);
                intent.putExtra("keyTotalPrice",iprice);


                //finish();
                startActivity(intent);
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
package com.example.laibrary.ui.book;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.laibrary.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BookingHistory extends AppCompatActivity {

    private TextView name,ic,pnumber,quantity,namebook,rentdate,total;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_history);

        name = findViewById(R.id.tvFN);
        ic = findViewById(R.id.tvIN);
        pnumber = findViewById(R.id.tvPN);
        namebook = findViewById(R.id.tvBK);
        quantity = findViewById(R.id.tvQ);
        rentdate = findViewById(R.id.tvR);
        total = findViewById(R.id.tvT);


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        databaseReference = firebaseDatabase.getReference("Booking Detail").child(firebaseAuth.getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                BookingDetail bookingDetail = snapshot.getValue(BookingDetail.class);
                name.setText("full name: "+bookingDetail.getFullname());
                ic.setText("IC number: "+bookingDetail.getIcnumber());
                pnumber.setText("Phone Number: "+bookingDetail.getPhonenumber());
                namebook.setText("Name Book: "+bookingDetail.getNamebook());
                quantity.setText("Quantity: "+bookingDetail.getQuantity());
                rentdate.setText("Rent Date: "+bookingDetail.getRentdate());
                total.setText("Total: "+bookingDetail.getTotal());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

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
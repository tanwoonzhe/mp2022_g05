package com.example.testing2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BookingHistory extends AppCompatActivity {

    private TextView name,ic,pnumber,quantity,namebook,rentdate,total;
    private Button back;
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
        back = findViewById(R.id.btnBack);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();


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

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BookingHistory.this,SecondActivity.class));
            }
        });
    }
}
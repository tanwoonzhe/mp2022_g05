package com.example.laibrary.ui.book;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.laibrary.LoginActivity;
import com.example.laibrary.MainActivity;
import com.example.laibrary.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

public class BookPayment extends AppCompatActivity implements PaymentResultListener {

    Button btpay;
    String userpaymentamount, userbookingid;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_payment);

        btpay = findViewById(R.id.bt_pay);

        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //assign amount
        //String sAmount = "100";
        userpaymentamount = getIntent().getStringExtra("keyTotalPrice");


        userbookingid = getIntent().getStringExtra("keybookingid");


        //convert and round off

        int amount = Math.round(Float.parseFloat(userpaymentamount)*100);

        btpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //initialize razorpay checkout
                Checkout checkout = new Checkout();

                //set key id
                checkout.setKeyID("rzp_test_JWbPNT2KXEHCMu");

                //set image
                checkout.setImage(R.drawable.rzp_logo);

                //initialize json object
                JSONObject object = new JSONObject();

                try {
                    //put name
                    object.put("name", "Laibrary");
                    //put description
                    object.put("description", "Test Payment");
                    //put theme color
                    object.put("theme.color", "#0093DD");
                    //put currency unit
                    object.put("currency", "MYR");
                    //put amount
                    object.put("amount", amount);
                    //put mobiile number
                    object.put("prefill.contact", "987654321");
                    //put email
                    object.put("prefill.email", "tanwoonzhe@gmail.com");
                    //open rezorpay checkout activity
                    checkout.open(BookPayment.this, object);

                } catch (JSONException e){
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    public void onPaymentSuccess(String s) {
        //initialize alert dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //set title
        builder.setTitle("Payment ID");
        //set message
        builder.setMessage(s);
        //show alert dialog
        builder.show();

        Intent intent = new Intent(BookPayment.this, BookingReceipt.class);
        intent.putExtra("keyid",userbookingid);
        intent.putExtra("keypaymentid",s);
        intent.putExtra("keyamount",userpaymentamount);
        startActivity(intent);
        DatabaseReference databaseReference = firebaseDatabase.getReference("Payment Detail").child(userbookingid);
        payment payment = new payment(userbookingid,s);

        databaseReference.setValue(payment);

        //finish();

    }

    @Override
    public void onPaymentError(int i, String s) {
        //diaplay toast
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
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
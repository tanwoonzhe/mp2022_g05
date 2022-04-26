package com.example.laibrary.ui.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.laibrary.MainActivity;
import com.example.laibrary.R;
import com.google.firebase.FirebaseError;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FeedbackActivity extends AppCompatActivity {

    EditText FDemail, FDfeedback;
    Button send;
    private FirebaseAuth firebaseAuth;
    String email, feedback;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        FDemail = findViewById(R.id.etFDEmail);
        FDfeedback = findViewById(R.id.etFDfeedback);
        send = findViewById(R.id.btnSend);

        firebaseAuth = FirebaseAuth.getInstance();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate()){
                    String stringFeedback = FDfeedback.getText().toString();
                    String stringEmail = FDemail.getText().toString();

                    DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
                    DatabaseReference FDRef = rootRef.child("Feedback");
                    String key = FDRef.push().getKey();

                    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = firebaseDatabase.getReference("Feedback");


                    FeedbackForm feedbackForm = new FeedbackForm(stringEmail, stringFeedback);

                    myRef.child(key).setValue(feedbackForm);

                    startActivity(new Intent(FeedbackActivity.this, MainActivity.class));

                }
            }
        });

    }

    private boolean validate(){
        Boolean result = false;
        email = FDemail.getText().toString();
        feedback = FDfeedback.getText().toString();

        if (email.isEmpty() || feedback.isEmpty()){
            Toast.makeText(this, "Please enter all the details", Toast.LENGTH_SHORT).show();
        } else  {
            result = true;
        }

        return result;
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
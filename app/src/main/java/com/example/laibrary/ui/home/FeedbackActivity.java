package com.example.laibrary.ui.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.laibrary.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FeedbackActivity extends AppCompatActivity {

    EditText FDemail, FDfeedback;
    Button send;
    private FirebaseAuth firebaseAuth;
    String email, feedback;

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

                    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = firebaseDatabase.getReference("Feedback").child(stringEmail);

                    FeedbackForm feedbackForm = new FeedbackForm(stringEmail,stringFeedback);
                    myRef.setValue(feedbackForm);

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
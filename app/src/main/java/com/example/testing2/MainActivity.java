package com.example.testing2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private EditText Name;
    private EditText Password;
    private TextView Info;
    private Button login;
    private int counter =5;
    private TextView userRegistration ;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private TextView forgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Name = (EditText)findViewById(R.id.etName);
        Password = (EditText)findViewById(R.id.etPassword);
        Info = (TextView) findViewById(R.id.tvInfo);
        login = (Button) findViewById(R.id.btnLogin);
        forgotPassword = (TextView) findViewById(R.id.tvForgotPassword);

        userRegistration = (TextView)findViewById(R.id.tvRegister);

        Info.setText("No of attemps remaining: 5");

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        FirebaseUser user = firebaseAuth.getCurrentUser();

        if (user !=null){
            finish();
            startActivity(new Intent(MainActivity.this, SecondActivity.class));
        }



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(Name.getText().toString(), Password.getText().toString());

            }
        });

        userRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RegistrationActivity.class));
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, PasswordActivity.class));
            }
        });

    }

    private void validate (String userName, String userPassword){

        progressDialog.setMessage("Please wait patiently.");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(userName, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    progressDialog.dismiss();
                    //Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                    checkEmailVerification();
                }else {
                    Toast.makeText(MainActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                    counter--;
                    Info.setText("No of reamaining attemps: "+ counter);
                    progressDialog.dismiss();
                    if( counter == 0){
                        login.setEnabled(false);
                    }
                }
            }
        });


    }

    private void checkEmailVerification(){
        FirebaseUser firebaseUser = firebaseAuth.getInstance().getCurrentUser();
        Boolean emailFlag = firebaseUser.isEmailVerified();

        if (emailFlag){
            finish();
            startActivity(new Intent(MainActivity.this, SecondActivity.class));
        }else {
            Toast.makeText(this, "Verify your email.", Toast.LENGTH_SHORT).show();
            firebaseAuth.signOut();
        }


    }

}
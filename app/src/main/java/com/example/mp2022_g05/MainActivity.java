package com.example.mp2022_g05;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText Name;
    private EditText Password;
    private TextView Info;
    private Button login;
    private int counter =5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Name = (EditText)findViewById(R.id.etName);
        Password = (EditText)findViewById(R.id.etPassword);
        Info = (TextView) findViewById(R.id.tvInfo);
        login = (Button) findViewById(R.id.btnLogin);

        Info.setText("No of attemps remaining: 5");

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(Name.getText().toString(), Password.getText().toString());

            }
        });



    }

    private void validate (String UserName, String userPassword){
        if((UserName.equals("Admin") ) && (userPassword.equals("1234"))){
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
        }else {
            counter--;

            Info.setText("No of attelmps remaining: "+ String.valueOf(counter));

            if (counter == 0){
                login.setEnabled(false);
            }
        }
    }


}
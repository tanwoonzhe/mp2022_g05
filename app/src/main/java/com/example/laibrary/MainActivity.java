package com.example.laibrary;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.laibrary.ui.book.BookFragment;
import com.example.laibrary.ui.book.BookHistory;
import com.example.laibrary.ui.book.BookingDetail;
import com.example.laibrary.ui.book.DateDatabase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.laibrary.databinding.ActivityMainBinding;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private EditText Name;
    private EditText Password;
    private TextView Info;
    private Button login;
    private int counter =5;
    private TextView userRegistration ;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private TextView forgotPassword;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private String bookingid;
    int currentyear, currentmonth, currentday, idyear, idmonth, idday, newyear, newmonth, newday;
    Calendar calendar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_book, R.id.navigation_profile)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        bookingid = getIntent().getStringExtra("keyid");

        Calendar instance = Calendar.getInstance();
        currentmonth = instance.get(Calendar.MONTH)+1;
        currentyear = instance.get(Calendar.YEAR);
        currentday = instance.get(Calendar.DAY_OF_MONTH);

        databaseReference = firebaseDatabase.getReference("Date").child(firebaseAuth.getUid());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DateDatabase dateDatabase = snapshot.getValue(DateDatabase.class);
                String day = dateDatabase.getDay();
                String month = dateDatabase.getMonth();
                String year = dateDatabase.getYear();

                if(day !=null || month != null || year != null){
                    idday = Integer.parseInt(day);
                    idmonth = Integer.parseInt(month);
                    idyear = Integer.parseInt(year);
                }else {
                    idday = 0;
                    idmonth = 0;
                    idyear = 0;
                }

                String status = dateDatabase.getStatus();

                if(status.equals("1")){

                    newmonth = idmonth+1;
                    newyear = idyear;
                    newday = idday;

                    if(newmonth>12){
                        newmonth = (idmonth+1)%12;
                        newyear = idyear+1;
                    }

                    if(idmonth == 2){
                        if((idyear==2020) ||(idyear == 2024)|| (idyear == 2028)){
                            newday = idday%29;
                        }else{
                            newday = idday%28;
                        }
                    }

                    if ((currentday == newday) && (currentmonth == newmonth) && (currentyear == newyear)){

                        Intent notificationIntent = new Intent(MainActivity.this, MainActivity.class);
                        PendingIntent pi = PendingIntent.getActivity(MainActivity.this, 0, notificationIntent, 0);

                        NotificationCompat.Builder notification = new NotificationCompat.Builder(MainActivity.this)
                                .setContentTitle("Laibrary App")
                                .setContentText("Today is the deadline returning book. Remember to return your book by today.")
                                .setSmallIcon(R.drawable.favicon).setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.logo))
                                .setColor(getResources().getColor(R.color.wallet_holo_blue_light))
                                .setVibrate(new long[]{0, 300, 300, 300})
                                .setLights(Color.WHITE, 1000, 5000)
                                .setContentIntent(pi)
                                .setAutoCancel(true)
                                .setPriority(NotificationCompat.PRIORITY_HIGH);

                        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        notificationManager.notify(0, notification.build());

                    }
                }else if(status.equals("0")){

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}


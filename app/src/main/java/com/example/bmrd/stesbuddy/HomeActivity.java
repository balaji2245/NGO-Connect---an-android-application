package com.example.bmrd.stesbuddy;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

public class HomeActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT=600;

    private FirebaseAuth mAuth;

    private DatabaseReference mUserDatabase;
    private FirebaseUser mCurrentUser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mAuth = FirebaseAuth.getInstance();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                Intent homeIntent=new Intent(HomeActivity.this,StartActivity.class);
//                startActivity(homeIntent);
      //          finish();
            }
        },SPLASH_TIME_OUT);


        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){

            NotificationChannel channel=new NotificationChannel("MyNotifications","MyNotificaations", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager= getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

/*
        FirebaseMessaging.getInstance().subscribeToTopic("general")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "Successful";
                        if (!task.isSuccessful()) {
                            msg = "Failed";
                        }

                        Toast.makeText(HomeActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
*/

    }




    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if ( currentUser == null ) {            //to send the user to start page if they are not already logged in
            sendToStart();
        }else {


            ///////////////////////////////////////

            String current_uid = "";
            current_uid = currentUser.getUid();

            mUserDatabase = FirebaseDatabase.getInstance().getReference().child("All_users").child(current_uid);


            mUserDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    // Toast.makeText(MainMessActivity.this,dataSnapshot.toString(),Toast.LENGTH_LONG).show();

                    String type = "";
                    type = dataSnapshot.child("type").getValue().toString();

                    if (type.equals("admin")) {


                        //   System.out.println(name + "}}}}}}}}}}}}}}}}}}}}}}}}}}}}}");
                        Intent mainIntent = new Intent(HomeActivity.this, AdminLoginActivity.class);
                        mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(mainIntent);
                        finish();

                    }


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


            mUserDatabase = FirebaseDatabase.getInstance().getReference().child("All_users").child(current_uid);


            mUserDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    // Toast.makeText(MainMessActivity.this,dataSnapshot.toString(),Toast.LENGTH_LONG).show();

                    String type = "";
                    type = dataSnapshot.child("type").getValue().toString();

                    if (type.equals("ngo")) {


                        //   System.out.println(name + "}}}}}}}}}}}}}}}}}}}}}}}}}}}}}");
                        Intent mainIntent = new Intent(HomeActivity.this, MainNGOActivity.class);
                        mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(mainIntent);
                        finish();

                    }


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


            mUserDatabase = FirebaseDatabase.getInstance().getReference().child("All_users").child(current_uid);


            mUserDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    // Toast.makeText(MainMessActivity.this,dataSnapshot.toString(),Toast.LENGTH_LONG).show();

                    String type = "";
                    type = dataSnapshot.child("type").getValue().toString();

                    if (type.equals("rest")) {


                        //   System.out.println(name + "}}}}}}}}}}}}}}}}}}}}}}}}}}}}}");
                        Intent mainIntent = new Intent(HomeActivity.this, MainRESTActivity.class);
                        mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(mainIntent);
                        finish();

                    }


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


            mUserDatabase = FirebaseDatabase.getInstance().getReference().child("All_users").child(current_uid);


            mUserDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    // Toast.makeText(MainMessActivity.this,dataSnapshot.toString(),Toast.LENGTH_LONG).show();

                    String type = "";
                    type = dataSnapshot.child("type").getValue().toString();

                    if (type.equals("vol")) {


                        //   System.out.println(name + "}}}}}}}}}}}}}}}}}}}}}}}}}}}}}");
                        Intent mainIntent = new Intent(HomeActivity.this, HomVolActivity.class);
                        mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(mainIntent);
                        finish();

                    }


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


        }//else
        /////////////////////////////////////////////


    }

    private void sendToStart() {
        Intent startIntent=new Intent(HomeActivity.this,StartActivity.class);
        startActivity(startIntent);
        finish();                           //to make sure the user dont come back to main page by pressing back button

    }





}

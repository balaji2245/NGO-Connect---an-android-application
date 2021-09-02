package com.example.bmrd.stesbuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private DatabaseReference mUserDatabase;

    private Toolbar mToolbar;

    private TextView mName;
    private TextView mEmail;

    private Button mLocation;

    private Button mSelects;

    private Button mNGOLog;
    private Button mAllNGO;
    private Button mRESTLog;
    private Button myInfoButton;

    private Button mAdminBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);



        mToolbar=(Toolbar) findViewById(R.id.main_app_bar2);                //top toolbar
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("STES buddy");

        mLocation=(Button) findViewById(R.id.button3);

        mNGOLog=(Button) findViewById(R.id.NGO_lg_btn);
        mAllNGO=(Button) findViewById(R.id.allNGObtn);

        mSelects=(Button) findViewById(R.id.selectedBTN);

        myInfoButton=(Button) findViewById(R.id.myInfoBTN);

        mAdminBTN=(Button) findViewById(R.id.adminBTN);

        mRESTLog=(Button) findViewById(R.id.restLoginBTN);

        mAuth = FirebaseAuth.getInstance();

       mName=(TextView)findViewById(R.id.nameText);
        mEmail=(TextView)findViewById(R.id.emailText);

        mUserDatabase= FirebaseDatabase.getInstance().getReference().child("balaji");

        System.out.println("Outside");

        mUserDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Toast.makeText(MainActivity.this,dataSnapshot.toString(),Toast.LENGTH_LONG).show();

                String name=dataSnapshot.child("info").getValue().toString();
                String email=dataSnapshot.child("time").getValue().toString();


                System.out.println("Im inside**********************************************************");

                //  String image=dataSnapshot.child("image").getValue().toString();

                mName.setText(name);
                mEmail.setText(email);


                //for image uploading
                //  Picasso.with(AmbegaoActivity.this).load(image).into(mImage1);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("error////////////////////////////////////////////////////");


            }
        });

        myInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent login_intent=new Intent(MainActivity.this,MyInfoActivity.class);
                startActivity(login_intent);

            }
        });


        mSelects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent login_intent=new Intent(MainActivity.this,GotSelectedInActivity.class);
                startActivity(login_intent);

            }
        });



        mAllNGO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent login_intent=new Intent(MainActivity.this,ShowNGOActivity.class);
                startActivity(login_intent);

            }
        });

        mAdminBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent login_intent=new Intent(MainActivity.this,AdminLoginActivity.class);
                startActivity(login_intent);

            }
        });



        mRESTLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent login_intent=new Intent(MainActivity.this,MainRESTActivity.class);
                startActivity(login_intent);

            }
        });


        mLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent login_intent=new Intent(MainActivity.this,MapsActivity.class);
                startActivity(login_intent);

            }
        });

        mNGOLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent mainIntent=new Intent(MainActivity.this,MainNGOActivity.class);
                //  mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(mainIntent);
                // finish();
            }
        });




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {                 //for menu of three dots
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        if(item.getItemId() == R.id.main_logout_btn){               //for signing out
            FirebaseAuth.getInstance().signOut();
            sendToStart();
        }
        else
            if(item.getItemId() == R.id.dev_info){               //developer information

                Intent login_intent=new Intent(MainActivity.this,ContactActivity.class);
                startActivity(login_intent);
        }
        return true;
    }




    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();


        if ( currentUser == null ) {            //to send the user to start page if they are not already logged in
            sendToStart();
        }

    }

    private void sendToStart() {
        Intent startIntent=new Intent(MainActivity.this,StartActivity.class);
        startActivity(startIntent);
        finish();                           //to make sure the user dont come back to main page by pressing back button

    }


}

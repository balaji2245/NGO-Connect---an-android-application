package com.example.bmrd.stesbuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomVolActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private FirebaseUser mCurrentUser;
    private DatabaseReference mUserDatabase;

    private FirebaseAuth mAuth;

    private Button myInfoButton;
    private Button gotSelectedButton;
    private Button allNGOButton;
    private Button getLocButton;
    private Button recommendButton;
    private String current_uid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hom_vol);



        mToolbar=(Toolbar) findViewById(R.id.main_app_bar2);                //top toolbar
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Volunteer's Home");

        mAuth = FirebaseAuth.getInstance();

        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();

        current_uid = mCurrentUser.getUid();


        myInfoButton=(Button) findViewById(R.id.myInfoBTN);
        gotSelectedButton=(Button) findViewById(R.id.gotSelectedInBTN);
        allNGOButton=(Button) findViewById(R.id.allNGOBTN);
        getLocButton=(Button) findViewById(R.id.getLocBTN);
        recommendButton=(Button) findViewById(R.id.recommendBTN);



        recommendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                mUserDatabase = FirebaseDatabase.getInstance().getReference().child("Volunteers").child(current_uid).child("Recommend");



                mUserDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                     int flag=0;

                        int edu=Integer.parseInt(dataSnapshot.child("education").getValue().toString());
                        int soc=Integer.parseInt(dataSnapshot.child("social").getValue().toString());
                        int fem=Integer.parseInt(dataSnapshot.child("female").getValue().toString());
                        int trib=Integer.parseInt(dataSnapshot.child("tribal").getValue().toString());
                        int you=Integer.parseInt(dataSnapshot.child("youth").getValue().toString());



                        int max=edu;
                        String max1="education";

                        if(soc>max){
                            max=soc;
                            max1="social";
                        }

                        if(fem>max){
                            max=fem;
                            max1="female";
                        }
                        if(trib>max){
                            max=trib;
                            max1="tribal";
                        }
                        if(you>max){
                            max=trib;
                            max1="youth";
                        }

                        if(max!=0){
                            flag=1;
                        }


                        if(flag==1) {
                            Intent login_intent = new Intent(HomVolActivity.this, RecommendActivity.class);
                            startActivity(login_intent);
                        }else{

                            Toast.makeText(HomVolActivity.this,"We have no recommendations for you",Toast.LENGTH_LONG).show();

                        }



                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });




            }
        });


        myInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent login_intent=new Intent(HomVolActivity.this,MyInfoActivity.class);
                startActivity(login_intent);

            }
        });

        gotSelectedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent login_intent=new Intent(HomVolActivity.this,GotSelectedInActivity.class);
                startActivity(login_intent);

            }
        });


        allNGOButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent login_intent=new Intent(HomVolActivity.this,ShowNGOActivity.class);
                startActivity(login_intent);

            }
        });



        getLocButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent login_intent=new Intent(HomVolActivity.this,MapsActivity4.class);
                startActivity(login_intent);

            }
        });






    }


    /////////////////////////////////////////////
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

            Intent login_intent=new Intent(HomVolActivity.this,ContactActivity.class);
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
        Intent startIntent=new Intent(HomVolActivity.this,StartActivity.class);
        startActivity(startIntent);
        finish();                           //to make sure the user dont come back to main page by pressing back button

    }






}

package com.example.bmrd.stesbuddy;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainRESTActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private DatabaseReference mPostReference;

    private Toolbar mToolbar;

    private DatabaseReference mUserDatabase;
    private FirebaseUser mCurrentUser;

    private Task<Void> mDatabase;

    //android layout

    private EditText eventDate;
    private EditText vacacyEdit;


    private TextView mName;
    private Button mChangeButton;

    private Button mAppliedList;

    //   private StorageReference mImageStorage;

    private String current_uid;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main_rest);


            mToolbar=(Toolbar) findViewById(R.id.main_app_bar2);                //top toolbar
            setSupportActionBar(mToolbar);
            getSupportActionBar().setTitle("Restaurant Home");




            mName = (TextView) findViewById(R.id.NGOnameText);
            mChangeButton = (Button) findViewById(R.id.change_button);


            eventDate = (EditText) findViewById(R.id.dateTextEnter);



            mAppliedList = (Button) findViewById(R.id.addRESTbtn);

            //         mImageStorage= FirebaseStorage.getInstance().getReference();


            ////
            mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();

            current_uid = mCurrentUser.getUid();


            mUserDatabase = FirebaseDatabase.getInstance().getReference().child("REST_list").child(current_uid);



            mUserDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    // Toast.makeText(MainMessActivity.this,dataSnapshot.toString(),Toast.LENGTH_LONG).show();

                    String name = dataSnapshot.child("name").getValue().toString();


                    mName.setText(name);

                    //for image uploading
                    //               Picasso.with(MainMessActivity.this).load(image).into(mImage);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            mAppliedList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    Intent login_intent=new Intent(MainRESTActivity.this,FoodAcceptedActivity.class);
                    startActivity(login_intent);




                }
            });




            mChangeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String food=eventDate.getText().toString();
                    if(food.matches("-?\\d+")) {
                        mPostReference = FirebaseDatabase.getInstance().getReference().child("Accepted_food").child(current_uid);
                        mPostReference.removeValue();


                       // String food = eventDate.getText().toString();
                        // String vacancy=vacacyEdit.getText().toString();

                        updateEvent(food);
                    }
                    else{
                        Toast.makeText(MainRESTActivity.this, "Please enter food amount in integer", Toast.LENGTH_LONG).show();
                    }


                }
            });


        } catch (Exception e) {
            Toast.makeText(MainRESTActivity.this, "You are not authorized NGO owner please contact the app owner to get authorized or login as student", Toast.LENGTH_LONG).show();

            //sendToStart
            Intent startIntent = new Intent(MainRESTActivity.this, MainActivity.class);
            startActivity(startIntent);
            finish();                           //to make sure the user dont come back to main page by pressing back button


            // sendToStart();
        }


    }


    private void updateEvent(String food) {


        FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();

        String uid=current_user.getUid();

        mDatabase=FirebaseDatabase.getInstance().getReference().child("REST_list").child(uid).child("food").setValue(food);
    //    mDatabase=FirebaseDatabase.getInstance().getReference().child("NGO_list").child(uid).child("vacancy").setValue(vacancy1);


//
//                    HashMap<String,String> VolunteersMap= new HashMap<>();
//                    VolunteersMap.put("date",date1);
//                    VolunteersMap.put("vacancy",vacancy1);
//
//
//                    mDatabase.setValue(VolunteersMap);




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

            Intent login_intent=new Intent(MainRESTActivity.this,ContactActivity.class);
            startActivity(login_intent);
        }
        return true;
    }


    private void sendToStart() {
        Intent startIntent=new Intent(MainRESTActivity.this,StartActivity.class);
        startActivity(startIntent);
        finish();                           //to make sure the user dont come back to main page by pressing back button

    }




}

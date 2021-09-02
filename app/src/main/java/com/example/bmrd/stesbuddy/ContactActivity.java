package com.example.bmrd.stesbuddy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ContactActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    private DatabaseReference mUserDatabase;

    private TextView mName;
    private TextView mEmail;
    private TextView mEdu;
    private TextView mContact;

    private TextView mGuide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        //Toolbar
//        mToolbar=(Toolbar) findViewById(R.id.login_toolbar);
//        setSupportActionBar(mToolbar);
//        getSupportActionBar().setTitle("Contact App Developer");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mName=(TextView)findViewById(R.id.nameText);
        mEmail=(TextView)findViewById(R.id.emailText);
        mEdu=(TextView)findViewById(R.id.eduText);
        mContact=(TextView)findViewById(R.id.contactText);
        mGuide=(TextView)findViewById(R.id.guideText);

        mUserDatabase= FirebaseDatabase.getInstance().getReference().child("admin");


        mUserDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                // Toast.makeText(MainMessActivity.this,dataSnapshot.toString(),Toast.LENGTH_LONG).show();

                String name=dataSnapshot.child("name1").getValue().toString();
                String email=dataSnapshot.child("name2").getValue().toString();
                String edu=dataSnapshot.child("name3").getValue().toString();
                String contact=dataSnapshot.child("name4").getValue().toString();
                String guide=dataSnapshot.child("guide").getValue().toString();

                //  String image=dataSnapshot.child("image").getValue().toString();

                mName.setText(name);
                mEmail.setText(email);
                mEdu.setText(edu);
                mContact.setText(contact);
                mGuide.setText(guide);


                //for image uploading
                //  Picasso.with(AmbegaoActivity.this).load(image).into(mImage1);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }
}

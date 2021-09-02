package com.example.bmrd.stesbuddy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MyInfoActivity extends AppCompatActivity {

    private TextView userId;
    private TextView userName;

    private TextView contactText;
    private TextView ageText;
    private TextView qualText;


    private DatabaseReference mUserDatabase;

    private FirebaseUser mCurrentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info);

        userName=(TextView)findViewById(R.id.nameText);
        userId=(TextView)findViewById(R.id.userIdText);

        contactText=(TextView)findViewById(R.id.ContactText);
        ageText=(TextView)findViewById(R.id.AgeText);
        qualText=(TextView)findViewById(R.id.QualText);


        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();

        final String current_uid = mCurrentUser.getUid();


        mUserDatabase= FirebaseDatabase.getInstance().getReference().child("Volunteers").child(current_uid);

        mUserDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                // Toast.makeText(MainMessActivity.this,dataSnapshot.toString(),Toast.LENGTH_LONG).show();

                String name=dataSnapshot.child("Name").getValue().toString();

                String contact=dataSnapshot.child("contact").getValue().toString();
                String age=dataSnapshot.child("age").getValue().toString();
                String qual=dataSnapshot.child("qual").getValue().toString();
               // String id=dataSnapshot.child("id").getValue().toString();

                //  String image=dataSnapshot.child("image").getValue().toString();

                userName.setText(name);
                userId.setText(current_uid);

                contactText.setText(contact);
                ageText.setText(age);
                qualText.setText(qual);


                //for image uploading
                //  Picasso.with(AmbegaoActivity.this).load(image).into(mImage1);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




    }
}

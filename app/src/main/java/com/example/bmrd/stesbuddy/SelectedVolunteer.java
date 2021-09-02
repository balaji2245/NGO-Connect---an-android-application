package com.example.bmrd.stesbuddy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class SelectedVolunteer extends AppCompatActivity {

    private TextView nameTextView;
    private TextView idTextView;
    private TextView ageTextView;
    private TextView contactTextView;
    private TextView qualTextView;
    private TextView genderTextView;

    private Button acceptButton;

    private DatabaseReference mUserDatabase;

    private DatabaseReference mDatabase;
    private TextView NGOnameView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_volunteer);

        nameTextView=(TextView)findViewById(R.id.nameText);

        NGOnameView=(TextView)findViewById(R.id.NGOnameTextView);

        idTextView=(TextView)findViewById(R.id.idText);

        ageTextView=(TextView)findViewById(R.id.ageText);
        contactTextView=(TextView)findViewById(R.id.contText);
        qualTextView=(TextView)findViewById(R.id.qualText);
        genderTextView=(TextView)findViewById(R.id.genderText);

        acceptButton=(Button) findViewById(R.id.acceptBTN);


        Bundle bundle=getIntent().getExtras();

        String id=bundle.getString("id");

        idTextView.setText(id);


        System.out.println(id+"================================");



        mUserDatabase= FirebaseDatabase.getInstance().getReference().child("Volunteers").child(id);


        mUserDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                // Toast.makeText(MainMessActivity.this,dataSnapshot.toString(),Toast.LENGTH_LONG).show();

                String name=dataSnapshot.child("Name").getValue().toString();

                String age=dataSnapshot.child("age").getValue().toString();
                String contact=dataSnapshot.child("contact").getValue().toString();
                String qual=dataSnapshot.child("qual").getValue().toString();
                String gend=dataSnapshot.child("gender").getValue().toString();

                nameTextView.setText(name);
                ageTextView.setText(age);
                contactTextView.setText(contact);
                qualTextView.setText(qual);
                genderTextView.setText(gend);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });




        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String id=idTextView.getText().toString();


                acceptVol(id);    //sending id of the tapped vol

                iGotSelectedIn(id);             //vol id

            }
        });
    }


    private void acceptVol(final String VolId) {

        FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();

        String current_ngo=current_user.getUid();

        mDatabase=FirebaseDatabase.getInstance().getReference().child("accepted_vol_list").child(current_ngo).child(VolId);

      String Vol_name=nameTextView.getText().toString();

        HashMap<String,String> VolunteersMap= new HashMap<>();
        VolunteersMap.put("id",VolId);
        VolunteersMap.put("name",Vol_name);


        mDatabase.setValue(VolunteersMap);

    }


    private void iGotSelectedIn(final String VolId) {

        FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();

        String current_ngo=current_user.getUid();


        mUserDatabase=FirebaseDatabase.getInstance().getReference().child("NGO_list").child(current_ngo);

        mUserDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();

                String current_ngo=current_user.getUid();



                // Toast.makeText(MainMessActivity.this,dataSnapshot.toString(),Toast.LENGTH_LONG).show();

                 String name=dataSnapshot.child("name").getValue().toString();

                 System.out.println(name+"[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[");

                NGOnameView.setText(name);

                laterFunction(current_ngo,VolId);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });



    }


///////now storing data of i got selected

    public void laterFunction(String current_ngo, String VolId) {



        mDatabase = FirebaseDatabase.getInstance().getReference().child("i_got_selected_in").child(VolId).child(current_ngo);


        HashMap<String, String> VolunteersMap = new HashMap<>();
        VolunteersMap.put("id", current_ngo);
        VolunteersMap.put("name", NGOnameView.getText().toString());

        System.out.println(NGOnameView.getText().toString() + "[[[[[[[[[[[[[[[[[[[[[[[[]]]]]]]]]]]<<<<<<");

        mDatabase.setValue(VolunteersMap);
    }








}

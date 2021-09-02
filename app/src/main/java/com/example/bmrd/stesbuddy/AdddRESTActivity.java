package com.example.bmrd.stesbuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class AdddRESTActivity extends AppCompatActivity {

    private TextView RESTid;
    private TextView RESTname;
    private TextView RESTfood;
    private TextView RESTlocation;
    private TextView RESTlon;
    private TextView RESTlat;



    private DatabaseReference mUserDatabase;

    private Button addRESTbtn;
    private Button finishButton;


    private DatabaseReference mDatabase;
    private Task<Void> mRESTDatabase;


    private Button addRESTlocbtn;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addd_rest);


        RESTid = (TextView) findViewById(R.id.userIdText);
        RESTname = (TextView) findViewById(R.id.RESTnameText);
        RESTlocation = (TextView) findViewById(R.id.addRESTText);
        addRESTbtn = (Button) findViewById(R.id.addBTN);

        RESTlat = (TextView) findViewById(R.id.latText);
        RESTlon = (TextView) findViewById(R.id.lonText);
        addRESTlocbtn = (Button) findViewById(R.id.locationBTN);

        finishButton = (Button) findViewById(R.id.finishBTN);


        addRESTbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String rest_name=RESTname.getText().toString();
                String rest_id=RESTid.getText().toString();
                String rest_location=RESTlocation.getText().toString();




                System.out.println(rest_id+">>>>>>>>>>>>>>>>>>>>>>");


//        FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
//
//        String uid=current_user.getUid();

                mDatabase= FirebaseDatabase.getInstance().getReference().child("REST_list").child(rest_id);

                HashMap<String,String> VolunteersMap2= new HashMap<>();
                VolunteersMap2.put("name",rest_name);
                VolunteersMap2.put("location",rest_location);

                // VolunteersMap2.put("food",rest_food);
                System.out.println(rest_id+"<<<<<<<<<<<<<<");
                VolunteersMap2.put("id",rest_id);
                VolunteersMap2.put("food","");
                VolunteersMap2.put("lat","");
                VolunteersMap2.put("lon","");
                mDatabase.setValue(VolunteersMap2);


                Intent login_intent=new Intent(AdddRESTActivity.this,MapsActivity2.class);
                startActivity(login_intent);

                System.out.println(rest_id+"><><><><><><><>><<>");



            }
        });


        addRESTlocbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String rest_id=RESTid.getText().toString();
                mUserDatabase= FirebaseDatabase.getInstance().getReference().child("admin").child("Temp_loc");


                mUserDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        // Toast.makeText(MainMessActivity.this,dataSnapshot.toString(),Toast.LENGTH_LONG).show();



                        String lat=dataSnapshot.child("Lat").getValue().toString();
                        String lon=dataSnapshot.child("Lan").getValue().toString();
                        System.out.println(lat+"????????????");

                        RESTlat.setText(lat);
                        RESTlon.setText(lon);

                        //for image uploading
                        //  Picasso.with(AmbegaoActivity.this).load(image).into(mImage1);

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


                mRESTDatabase=FirebaseDatabase.getInstance().getReference().child("REST_list").child(rest_id).child("lat").setValue(RESTlat.getText().toString());
                mRESTDatabase=FirebaseDatabase.getInstance().getReference().child("REST_list").child(rest_id).child("lon").setValue(RESTlon.getText().toString());
                System.out.println(RESTlat.getText().toString()+"????????????////////");


            }
        });

        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String rest_id=RESTid.getText().toString();

                mRESTDatabase=FirebaseDatabase.getInstance().getReference().child("REST_list").child(rest_id).child("lat").setValue(RESTlat.getText().toString());
                mRESTDatabase=FirebaseDatabase.getInstance().getReference().child("REST_list").child(rest_id).child("lon").setValue(RESTlon.getText().toString());
                System.out.println(RESTlat.getText().toString()+"????????????////////");


            }
        });




    }
}

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

public class AddNGOActivity extends AppCompatActivity {

    private TextView NGOid;
    private TextView NGOname;
    private TextView NGOvacancy;
    private TextView NGOlocation;
    private TextView NGOlon;
    private TextView NGOlat;
    private TextView NGOdate;
    private TextView NGOdomain;


    private DatabaseReference mUserDatabase;

    private Button addNGObtn;
    private Button finishButton;


    private DatabaseReference mDatabase;
    private Task<Void> mRESTDatabase;


    private Button addNGOlocbtn;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ngo);


        NGOid = (TextView) findViewById(R.id.idText);
        NGOname = (TextView) findViewById(R.id.nameText);
        NGOlocation = (TextView) findViewById(R.id.addressText);
        NGOdomain = (TextView) findViewById(R.id.domainText);
        addNGObtn = (Button) findViewById(R.id.addBTN);

        NGOlat = (TextView) findViewById(R.id.latText);
        NGOlon = (TextView) findViewById(R.id.lonText);
        addNGOlocbtn = (Button) findViewById(R.id.updateBTN);

        finishButton = (Button) findViewById(R.id.finishBTN);


        addNGObtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ngo_name=NGOname.getText().toString();
                String ngo_id=NGOid.getText().toString();
                String ngo_location=NGOlocation.getText().toString();
                String ngo_domain=NGOdomain.getText().toString();



                System.out.println(ngo_id+">>>>>>>>>>>>>>>>>>>>>>");


//        FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
//
//        String uid=current_user.getUid();

                mDatabase= FirebaseDatabase.getInstance().getReference().child("NGO_list").child(ngo_id);

                HashMap<String,String> VolunteersMap2= new HashMap<>();
                VolunteersMap2.put("name",ngo_name);
                VolunteersMap2.put("location",ngo_location);

                // VolunteersMap2.put("food",rest_food);
                System.out.println(ngo_id+"<<<<<<<<<<<<<<");

                VolunteersMap2.put("id",ngo_id);
                VolunteersMap2.put("vacancy","");
                VolunteersMap2.put("domain",ngo_domain);
                VolunteersMap2.put("date","");
                VolunteersMap2.put("lat","");
                VolunteersMap2.put("lon","");
                mDatabase.setValue(VolunteersMap2);


                Intent login_intent=new Intent(AddNGOActivity.this,MapsActivity3.class);
                startActivity(login_intent);

                System.out.println(ngo_id+"><><><><><><><>><<>");



            }
        });


        addNGOlocbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String ngo_id=NGOid.getText().toString();
                mUserDatabase= FirebaseDatabase.getInstance().getReference().child("admin").child("Temp_loc1");


                mUserDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        // Toast.makeText(MainMessActivity.this,dataSnapshot.toString(),Toast.LENGTH_LONG).show();



                        String lat=dataSnapshot.child("Lat").getValue().toString();
                        String lon=dataSnapshot.child("Lan").getValue().toString();
                        System.out.println(lat+"????????????");

                        NGOlat.setText(lat);
                        NGOlon.setText(lon);

                        //for image uploading
                        //  Picasso.with(AmbegaoActivity.this).load(image).into(mImage1);

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


                mRESTDatabase=FirebaseDatabase.getInstance().getReference().child("NGO_list").child(ngo_id).child("lat").setValue(NGOlat.getText().toString());
                mRESTDatabase=FirebaseDatabase.getInstance().getReference().child("NGO_list").child(ngo_id).child("lon").setValue(NGOlon.getText().toString());
                System.out.println(NGOlat.getText().toString()+"????????????////////");


            }
        });

        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String ngo_id=NGOid.getText().toString();

                mRESTDatabase=FirebaseDatabase.getInstance().getReference().child("NGO_list").child(ngo_id).child("lat").setValue(NGOlat.getText().toString());
                mRESTDatabase=FirebaseDatabase.getInstance().getReference().child("NGO_list").child(ngo_id).child("lon").setValue(NGOlon.getText().toString());
                System.out.println(NGOlat.getText().toString()+"????????????////////");


            }
        });




    }
}

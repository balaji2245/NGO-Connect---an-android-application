package com.example.bmrd.stesbuddy;

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

public class SelectedREST2 extends AppCompatActivity {


    private TextView NGOid;

    private TextView NGOname;
    private TextView NGOdomain;

    private TextView NGOcontact;
    private TextView NGOaddress;
    private TextView NGOlat;
    private TextView NGOlon;
    private TextView NGOstatus;

    private TextView RESTname;

    private TextView RESTfssai;

    private Button acceptBTN;
    private Button regectBTN;


    private DatabaseReference mUserDatabase;

    private DatabaseReference mDatabase;
    private Task<Void> mDatabase2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_rest2);

        NGOid=(TextView)findViewById(R.id.idText);

        RESTfssai=(TextView)findViewById(R.id.textView19);
        RESTname=(TextView)findViewById(R.id.textView17);

        NGOname=(TextView)findViewById(R.id.NameText);
        NGOdomain=(TextView)findViewById(R.id.DomainText);

        NGOcontact=(TextView)findViewById(R.id.VacancyText);
        NGOaddress=(TextView)findViewById(R.id.AddressText);

        NGOlat=(TextView)findViewById(R.id.latText);
        NGOlon=(TextView)findViewById(R.id.lonText);
        NGOstatus=(TextView)findViewById(R.id.statusText);


        acceptBTN=(Button) findViewById(R.id.acceptButton);
        regectBTN=(Button) findViewById(R.id.regectButton);

        Bundle bundle=getIntent().getExtras();

        String id=bundle.getString("id");

        NGOid.setText(id);


        System.out.println(id+"================================");


        mUserDatabase= FirebaseDatabase.getInstance().getReference().child("future_rest").child(id);


        mUserDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                // Toast.makeText(MainMessActivity.this,dataSnapshot.toString(),Toast.LENGTH_LONG).show();

                String name=dataSnapshot.child("name").getValue().toString();


                String address=dataSnapshot.child("location").getValue().toString();

                String contact=dataSnapshot.child("contact").getValue().toString();

                String fssai=dataSnapshot.child("fssai").getValue().toString();

                String lat=dataSnapshot.child("lat").getValue().toString();
                String lon=dataSnapshot.child("lon").getValue().toString();
                String status=dataSnapshot.child("status").getValue().toString();
                //  String image=dataSnapshot.child("image").getValue().toString();

                NGOname.setText(name);

                RESTfssai.setText("FSSAI no:");
                RESTname.setText("ID");

                NGOcontact.setText(contact);

                NGOdomain.setText(fssai);

                NGOaddress.setText(address);
                NGOlat.setText(lat);
                NGOlon.setText(lon);
                NGOstatus.setText(status);

                //for image uploading
                //  Picasso.with(AmbegaoActivity.this).load(image).into(mImage1);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });





        acceptBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String id=NGOid.getText().toString();

                mDatabase2=FirebaseDatabase.getInstance().getReference().child("future_rest").child(id).child("status").setValue("accepted");

                acceptNGO(id);    //sending id of the tapped NGO



            }
        });


        regectBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String id=NGOid.getText().toString();

                mDatabase2=FirebaseDatabase.getInstance().getReference().child("future_rest").child(id).child("status").setValue("regected");

            }
        });



    }

    private void acceptNGO(final String NGOid) {



        mDatabase=FirebaseDatabase.getInstance().getReference().child("REST_list").child(NGOid);

        String name=NGOname.getText().toString();

        String address=NGOaddress.getText().toString();
        String contact=NGOcontact.getText().toString();
        String lat=NGOlat.getText().toString();
        String lon=NGOlon.getText().toString();
        String fssai=NGOdomain.getText().toString();


        HashMap<String,String> VolunteersMap= new HashMap<>();
        VolunteersMap.put("id",NGOid);
        VolunteersMap.put("name",name);

        VolunteersMap.put("lat",lat);
        VolunteersMap.put("lon",lon);
        VolunteersMap.put("contact",contact);

        VolunteersMap.put("location",address);
        VolunteersMap.put("food","");
        VolunteersMap.put("fssai",fssai);



        //VolunteersMap.put("Email_id",email);

        mDatabase.setValue(VolunteersMap);



    }





}

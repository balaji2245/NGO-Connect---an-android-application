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

public class SelectedNGO extends AppCompatActivity {


    private TextView NGOid;

    private TextView NGOname;
    private TextView NGOdomain;
    private TextView NGOdate;
    private TextView NGOvacancy;
    private TextView NGOaddress;


    private TextView DomainQuant;


    private TextView CurrrentNameUser;

    private Button applyBTN;

    private DatabaseReference mUserDatabase;

    private DatabaseReference mDatabase;
    private Task<Void> mDatabase2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_ngo);

        NGOid=(TextView)findViewById(R.id.idText);

        NGOname=(TextView)findViewById(R.id.NameText);
        NGOdomain=(TextView)findViewById(R.id.DomainText);
        NGOdate=(TextView)findViewById(R.id.DateText);
        NGOvacancy=(TextView)findViewById(R.id.VacancyText);
        NGOaddress=(TextView)findViewById(R.id.AddressText);

        DomainQuant=(TextView)findViewById(R.id.domainQuant);

        CurrrentNameUser=(TextView)findViewById(R.id.CurrentUserName);

        applyBTN=(Button) findViewById(R.id.ApplyBTN);


        Bundle bundle=getIntent().getExtras();

        String id=bundle.getString("id");

        NGOid.setText(id);


        System.out.println(id+"================================");


        mUserDatabase= FirebaseDatabase.getInstance().getReference().child("NGO_list").child(id);


        mUserDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                // Toast.makeText(MainMessActivity.this,dataSnapshot.toString(),Toast.LENGTH_LONG).show();

                String name=dataSnapshot.child("name").getValue().toString();
                String date=dataSnapshot.child("date").getValue().toString();
                String address=dataSnapshot.child("location").getValue().toString();
                String domain=dataSnapshot.child("domain").getValue().toString();
                String vacancy=dataSnapshot.child("vacancy").getValue().toString();

                //  String image=dataSnapshot.child("image").getValue().toString();

                NGOname.setText(name);
                NGOdomain.setText(domain);
                NGOdate.setText(date);
                NGOvacancy.setText(vacancy);
                NGOaddress.setText(address);

                //for image uploading
                //  Picasso.with(AmbegaoActivity.this).load(image).into(mImage1);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });

        FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();

        String uid=current_user.getUid();           //id of applied volunteer



        mUserDatabase= FirebaseDatabase.getInstance().getReference().child("Volunteers").child(uid);


        mUserDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                // Toast.makeText(MainMessActivity.this,dataSnapshot.toString(),Toast.LENGTH_LONG).show();

                String name=dataSnapshot.child("Name").getValue().toString();

                CurrrentNameUser.setText(name);

                //  String image=dataSnapshot.child("image").getValue().toString();
                //for image uploading
                //  Picasso.with(AmbegaoActivity.this).load(image).into(mImage1);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });





        applyBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String id=NGOid.getText().toString();
                String domain=NGOdomain.getText().toString();

                applyUser(id,domain);    //sending id of the tapped NGO



            }
        });





    }

    private void applyUser(final String NGOid, final String NGOdom) {

        FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();

        final String uid=current_user.getUid();           //id of applied volunteer


int flag=0;

//
//        FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
//
//                    String uid=current_user.getUid();
                    mDatabase=FirebaseDatabase.getInstance().getReference().child("Applied_volunteers").child(NGOid).child(uid);

        String volunteer_name=CurrrentNameUser.getText().toString();

                    HashMap<String,String> VolunteersMap= new HashMap<>();
                    VolunteersMap.put("id",uid);
                    VolunteersMap.put("name",volunteer_name);
                    //VolunteersMap.put("Email_id",email);

                    mDatabase.setValue(VolunteersMap);

///////////////////////////////////////////////////////////////////////


        mUserDatabase= FirebaseDatabase.getInstance().getReference().child("Volunteers").child(uid).child("Recommend");


        mUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {

            public void onDataChange(DataSnapshot dataSnapshot) {

                // Toast.makeText(MainMessActivity.this,dataSnapshot.toString(),Toast.LENGTH_LONG).show();


                int domainQuant=Integer.parseInt(dataSnapshot.child(NGOdom).getValue().toString());
//                int soc=Integer.parseInt(dataSnapshot.child("social").getValue().toString());
//                int fem=Integer.parseInt(dataSnapshot.child("female").getValue().toString());
//                int trib=Integer.parseInt(dataSnapshot.child("tribal").getValue().toString());
//                int you=Integer.parseInt(dataSnapshot.child("youth").getValue().toString());

                //  String image=dataSnapshot.child("image").getValue().toString();

                System.out.println(domainQuant+" ++++++++++++++++++++++++++++++++++++++++++++");
                System.out.println(DomainQuant.getText()+" bbbbbbbbbb");

                domainQuant=domainQuant+1;
                DomainQuant.setText(domainQuant+"");

                System.out.println(domainQuant+" }}}}}}}}}");
//
//                int max=edu;
//                String max1="education";
//
//                if(soc>max){
//                    max=soc;
//                    max1="social";
//                }
//
//                if(fem>max){
//                    max=fem;
//                    max1="female";
//                }
//                if(trib>max){
//                    max=trib;
//                    max1="tribal";
//                }
//                if(you>max){
//                    max=trib;
//                    max1="youth";
//                }
//

                //for image uploading
                //  Picasso.with(AmbegaoActivity.this).load(image).into(mImage1);
                UpdateQuant(uid,NGOdom);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });





    }

private void UpdateQuant(String uid,String NGOdom){
    mDatabase2=FirebaseDatabase.getInstance().getReference().child("Volunteers").child(uid).child("Recommend").child(NGOdom).setValue(DomainQuant.getText());
    System.out.println(DomainQuant.getText()+" {{{{{{{{");


}


}

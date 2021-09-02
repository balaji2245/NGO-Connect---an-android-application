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

import org.w3c.dom.Text;

import java.util.HashMap;

public class SelectedREST extends AppCompatActivity {

    private TextView RESTid;
    private TextView RESTname;
    private TextView RESTadd;
    private TextView RESTfood;
    private TextView CurrrentNameUser;

    private Button acceptButton;

    private Task<Void> mPostReference;

    private DatabaseReference mUserDatabase;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_rest);


        RESTid=(TextView)findViewById(R.id.restIdText);

        RESTname=(TextView)findViewById(R.id.nameText);

        RESTadd=(TextView)findViewById(R.id.addText);
        RESTfood=(TextView)findViewById(R.id.foodText);
        acceptButton=(Button) findViewById(R.id.acceptBTN);

        CurrrentNameUser=(TextView)findViewById(R.id.CurrentUserName);



        Bundle bundle=getIntent().getExtras();

        String id=bundle.getString("id");

        System.out.println(id+"?????????????????????");

        RESTid.setText(id);



        mUserDatabase= FirebaseDatabase.getInstance().getReference().child("REST_list").child(id);


        mUserDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                // Toast.makeText(MainMessActivity.this,dataSnapshot.toString(),Toast.LENGTH_LONG).show();

                String name=dataSnapshot.child("name").getValue().toString();
                String food=dataSnapshot.child("food").getValue().toString();
                String address=dataSnapshot.child("location").getValue().toString();
                String id=dataSnapshot.child("id").getValue().toString();


                //  String image=dataSnapshot.child("image").getValue().toString();

                RESTname.setText(name);
                RESTadd.setText(address);
                RESTfood.setText(food);
                RESTid.setText(id);


                //for image uploading
                //  Picasso.with(AmbegaoActivity.this).load(image).into(mImage1);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });



        FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();

        String uid=current_user.getUid();           //id of applied volunteer


        mUserDatabase= FirebaseDatabase.getInstance().getReference().child("NGO_list").child(uid);


        mUserDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                // Toast.makeText(MainMessActivity.this,dataSnapshot.toString(),Toast.LENGTH_LONG).show();

                String name=dataSnapshot.child("name").getValue().toString();

                CurrrentNameUser.setText(name);

                //  String image=dataSnapshot.child("image").getValue().toString();
                //for image uploading
                //  Picasso.with(AmbegaoActivity.this).load(image).into(mImage1);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String id=RESTid.getText().toString();


                applyUser(id);    //sending id of the tapped NGO

                mPostReference=FirebaseDatabase.getInstance().getReference().child("REST_list").child(RESTid.getText().toString()).child("food").setValue("");
              //  mPostReference = FirebaseDatabase.getInstance().getReference().child("REST_list").child(RESTid.getText().toString()).child("food").setValue("");
               // mPostReference.removeValue();


            }
        });




    }

    private void applyUser(final String RESTid) {

        FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();

        String uid=current_user.getUid();           //id of applied volunteer




//
//        FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
//
//                    String uid=current_user.getUid();
        mDatabase=FirebaseDatabase.getInstance().getReference().child("Accepted_food").child(RESTid).child(uid);

        String Rest_name=CurrrentNameUser.getText().toString();

        HashMap<String,String> VolunteersMap= new HashMap<>();
        VolunteersMap.put("id",uid);
        VolunteersMap.put("name",Rest_name);
        //VolunteersMap.put("Email_id",email);

        mDatabase.setValue(VolunteersMap);

    }




}

package com.example.bmrd.stesbuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class WaitingActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private DatabaseReference mUserDatabase;
    private FirebaseUser mCurrentUser;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting);


        Bundle bundle=getIntent().getExtras();

        String choice=bundle.getString("option");

        mAuth = FirebaseAuth.getInstance();

        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();

        String current_uid = mCurrentUser.getUid();



        if(choice.equals("Vol")) {
        try {
            mUserDatabase = FirebaseDatabase.getInstance().getReference().child("Volunteers").child(current_uid);


            mUserDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    // Toast.makeText(MainMessActivity.this,dataSnapshot.toString(),Toast.LENGTH_LONG).show();

                    String name = "";
                    name = dataSnapshot.child("Name").getValue().toString();

                    if (!name.equals("")) {


                        System.out.println(name + "}}}}}}}}}}}}}}}}}}}}}}}}}}}}}");
                        Intent mainIntent = new Intent(WaitingActivity.this, HomVolActivity.class);
                        mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(mainIntent);
                        finish();

                    }


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        } catch (Exception e) {


        }
    }


       else if(choice.equals("Rest")) {

            try {
                mUserDatabase = FirebaseDatabase.getInstance().getReference().child("REST_list").child(current_uid);


                mUserDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        // Toast.makeText(MainMessActivity.this,dataSnapshot.toString(),Toast.LENGTH_LONG).show();

                        String name = "";
                        name = dataSnapshot.child("id").getValue().toString();

                        if (!name.equals("")) {


                            System.out.println(name + "}}}}}}}}}}}}}}}}}}}}}}}}}}}}}");
                            Intent mainIntent = new Intent(WaitingActivity.this, MainRESTActivity.class);
                            mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(mainIntent);
                            finish();

                        }


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            } catch (Exception e) {


            }
        }

        else if(choice.equals("NGO")) {

            try {
                mUserDatabase = FirebaseDatabase.getInstance().getReference().child("NGO_list").child(current_uid);


                mUserDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        // Toast.makeText(MainMessActivity.this,dataSnapshot.toString(),Toast.LENGTH_LONG).show();

                        String name = "";
                        name = dataSnapshot.child("id").getValue().toString();

                        if (!name.equals("")) {


                            System.out.println(name + "}}}}}}}}}}}}}}}}}}}}}}}}}}}}}");
                            Intent mainIntent = new Intent(WaitingActivity.this, MainNGOActivity.class);
                            mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(mainIntent);
                            finish();

                        }


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            } catch (Exception e) {


            }
        }
        else if(choice.equals("Admin")) {

            try {
                mUserDatabase = FirebaseDatabase.getInstance().getReference().child("admin").child(current_uid);


                mUserDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        // Toast.makeText(MainMessActivity.this,dataSnapshot.toString(),Toast.LENGTH_LONG).show();

                        String name = "";
                        name = dataSnapshot.child("name").getValue().toString();

                        if (!name.equals("")) {


                            System.out.println(name + "}}}}}}}}}}}}}}}}}}}}}}}}}}}}}");
                            Intent mainIntent = new Intent(WaitingActivity.this, AdminLoginActivity.class);
                            mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(mainIntent);
                            finish();

                        }


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            } catch (Exception e) {


            }
        }



    }




}

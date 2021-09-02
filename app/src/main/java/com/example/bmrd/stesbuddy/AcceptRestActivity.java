package com.example.bmrd.stesbuddy;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class AcceptRestActivity extends AppCompatActivity {

    private DatabaseReference mPostReference;

    private DatabaseReference mUserDatabase;
    private FirebaseUser mCurrentUser;
    private String current_uid;

    private Button mNearByOnly;

    private TextView mId;

    private TextView mContact;

    private RecyclerView mNGOlist;

    private DatabaseReference mNGODatabase;
    private Task<Void> mNGODatabase2;
    private FirebaseAuth mAuth;

    // private String  IdForApplyVol;

    private Toolbar mToolbar;
    private DatabaseReference mDatabase;

    private DatabaseReference mDatabase2;

    private ProgressDialog mRegProgress;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accept_rest);

        mNGODatabase= FirebaseDatabase.getInstance().getReference().child("future_rest");

        mNGODatabase2= FirebaseDatabase.getInstance().getReference().child("future_rest2").removeValue();

        mNearByOnly=(Button)findViewById(R.id.viewNearByBTN);

        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();






        mNGOlist=(RecyclerView) findViewById(R.id.NGO_list);
        mNGOlist.setHasFixedSize(true);
        mNGOlist.setLayoutManager(new LinearLayoutManager(this));



        mNearByOnly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent login_intent=new Intent(AcceptRestActivity.this,AcceptRestActivity2.class);        ///dummy right now
                startActivity(login_intent);

            }
        });




    }

    protected void onStart(){
        super.onStart();

        FirebaseRecyclerAdapter<REST2, NGOViewHolder> firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<REST2, NGOViewHolder>(
                REST2.class,
                R.layout.single_rest_layout,
                NGOViewHolder.class,
                mNGODatabase


        ) {
            @Override
            protected void populateViewHolder(final NGOViewHolder viewHolder, final REST2 model, int position) {


                // String IdForApplyVol=model.getId();

                viewHolder.parent_layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(AcceptRestActivity.this,"You clicked on"+viewHolder.getPosition(),Toast.LENGTH_LONG).show();

                        Intent i= new Intent(AcceptRestActivity.this,SelectedREST2.class);

                        String IdForApplyVol=model.getId();

                        Bundle bundle=new Bundle();
                        bundle.putString("id",IdForApplyVol);
                        i.putExtras(bundle);
                        startActivity(i);

                    }
                });


                if(model.getStatus().equals("wait")){
                    addVolunteer(model.getId());

                }


                viewHolder.setName(model.getName());

                viewHolder.setTextContact("Contact NO:");
                viewHolder.setLocation(model.getLocation());
                viewHolder.setContact(model.getContact());
                //  viewHolder.setVacancy(model.getVacancy());
                viewHolder.setId(model.getId());



            }
        };


        mNGOlist.smoothScrollToPosition(4);
        mNGOlist.setAdapter(firebaseRecyclerAdapter);
        //System.out.println(mNGOlist.getAdapter().getItemCount()-1+"//////////////////////////******/*/**/*/*/*/*/*");


    }

    public static class NGOViewHolder extends RecyclerView.ViewHolder{

        View mView;

        RelativeLayout parent_layout;

        public NGOViewHolder(View itemView) {
            super(itemView);

            mView=itemView;


            parent_layout=itemView.findViewById(R.id.single_layout);


        }

        public void setName(String name){
            TextView NGONameView =(TextView) mView.findViewById(R.id.RESTnameText);
            NGONameView.setText(name);

        }

        public void setTextContact(String name){
            TextView NGONameView =(TextView) mView.findViewById(R.id.textView28);
            NGONameView.setText(name);

        }



        public void setLocation(String location){
            TextView NGOLocationView =(TextView) mView.findViewById(R.id.addText);
            NGOLocationView.setText(location);

        }


        public void setContact(String contact){
            TextView NGOLocationView =(TextView) mView.findViewById(R.id.foodText);
            NGOLocationView.setText(contact);

        }

        public void setId(String id){
            TextView NGOIdView =(TextView) mView.findViewById(R.id.RESTidText);
            NGOIdView.setText(id);

        }






    }

    private void addVolunteer(final String id) {


        mUserDatabase= FirebaseDatabase.getInstance().getReference().child("future_rest").child(id);


        mUserDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                // Toast.makeText(MainMessActivity.this,dataSnapshot.toString(),Toast.LENGTH_LONG).show();

                String name=dataSnapshot.child("name").getValue().toString();
                String address=dataSnapshot.child("location").getValue().toString();

                String contact=dataSnapshot.child("contact").getValue().toString();


                mDatabase2=FirebaseDatabase.getInstance().getReference().child("future_rest2").child(id);

                HashMap<String,String> VolunteersMap2= new HashMap<>();
                VolunteersMap2.put("name",name);
                VolunteersMap2.put("location",address);
                VolunteersMap2.put("id",id);

                VolunteersMap2.put("contact",contact);
                mDatabase2.setValue(VolunteersMap2);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });








    }




}

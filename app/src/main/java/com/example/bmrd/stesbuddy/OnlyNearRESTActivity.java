package com.example.bmrd.stesbuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class OnlyNearRESTActivity extends AppCompatActivity {

    private RecyclerView mNGOlist;


    private DatabaseReference mUserDatabase;
    private FirebaseUser mCurrentUser;
    private String current_uid;

    private DatabaseReference mNGODatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_only_near_rest);


        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();

        current_uid = mCurrentUser.getUid();


        mNGODatabase= FirebaseDatabase.getInstance().getReference().child("NearByREST").child(current_uid);


        mNGOlist=(RecyclerView) findViewById(R.id.NGO_list);
        mNGOlist.setHasFixedSize(true);
        mNGOlist.setLayoutManager(new LinearLayoutManager(this));




    }

    protected void onStart(){
        super.onStart();

        FirebaseRecyclerAdapter<REST, RESTViewHolder> firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<REST, RESTViewHolder>(
                REST.class,
                R.layout.single_rest_layout,
                RESTViewHolder.class,
                mNGODatabase


        ) {
            @Override
            protected void populateViewHolder(final RESTViewHolder viewHolder, final REST model, final int position) {

                viewHolder.parent_layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(OnlyNearRESTActivity.this,"You clicked on"+viewHolder.getPosition(),Toast.LENGTH_LONG).show();

                        Intent i= new Intent(OnlyNearRESTActivity.this,SelectedREST.class);




                        String IdForApplyVol=model.getId();
                        System.out.println(IdForApplyVol+"+++++++++++++++++----");


                        Bundle bundle=new Bundle();
                        bundle.putString("id",IdForApplyVol);
                        i.putExtras(bundle);
                        startActivity(i);

                    }
                });







                viewHolder.setName(model.getName());

                viewHolder.setLocation(model.getLocation());

                viewHolder.setFood(model.getFood());

                viewHolder.setId(model.getId());
            }
        };

        mNGOlist.setAdapter(firebaseRecyclerAdapter);

    }

    public static class RESTViewHolder extends RecyclerView.ViewHolder{

        View mView;

        RelativeLayout parent_layout;

        public RESTViewHolder(View itemView) {
            super(itemView);

            mView=itemView;

            parent_layout=itemView.findViewById(R.id.single_layout);

        }

        public void setName(String name){
            TextView NGONameView =(TextView) mView.findViewById(R.id.RESTnameText);
            NGONameView.setText(name);

        }


        public void setLocation(String location){
            TextView NGOLocationView =(TextView) mView.findViewById(R.id.addText);
            NGOLocationView.setText(location);

        }

        public void setFood(String food){
            TextView NGOFoodView =(TextView) mView.findViewById(R.id.foodText);
            NGOFoodView.setText(food);

        }


        public void setId(String id){
            TextView NGOIdView =(TextView) mView.findViewById(R.id.RESTidText);
            NGOIdView.setText(id);

        }


    }


}

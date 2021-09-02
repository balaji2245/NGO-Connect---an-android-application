package com.example.bmrd.stesbuddy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FoodAcceptedActivity extends AppCompatActivity {

    private FirebaseUser mCurrentUser;
    private String current_uid;


    private RecyclerView mVolunteerlist;

    private DatabaseReference mVolunteerDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_accepted);


        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();

        current_uid = mCurrentUser.getUid();


        mVolunteerDatabase= FirebaseDatabase.getInstance().getReference().child("Accepted_food").child(current_uid);


        mVolunteerlist=(RecyclerView) findViewById(R.id.Volunteer_list);
        mVolunteerlist.setHasFixedSize(true);
        mVolunteerlist.setLayoutManager(new LinearLayoutManager(this));




    }

    protected void onStart(){
        super.onStart();

        FirebaseRecyclerAdapter<Volunteer, VolunteerViewHolder> firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<Volunteer, VolunteerViewHolder>(
                Volunteer.class,

                R.layout.single_volunteer_layout,
                VolunteerViewHolder.class,
                mVolunteerDatabase


        ) {
            @Override
            protected void populateViewHolder(VolunteerViewHolder viewHolder, Volunteer model, int position) {

                viewHolder.setName(model.getName());
                viewHolder.setId(model.getId());

            }
        };

        mVolunteerlist.setAdapter(firebaseRecyclerAdapter);

    }

    public static class VolunteerViewHolder extends RecyclerView.ViewHolder{

        View mView;


        public VolunteerViewHolder(View itemView) {
            super(itemView);

            mView=itemView;
        }

        public void setName(String name){
            TextView VolunteerNameView =(TextView) mView.findViewById(R.id.nameTextVol);
            VolunteerNameView.setText(name);

        }

        public void setId(String id){
            TextView VolunteerIdView =(TextView) mView.findViewById(R.id.idTextVol);
            VolunteerIdView.setText(id);

        }


    }


}

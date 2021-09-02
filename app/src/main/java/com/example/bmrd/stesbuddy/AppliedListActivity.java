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

public class AppliedListActivity extends AppCompatActivity {

    private FirebaseUser mCurrentUser;
    private String current_uid;


    private RecyclerView mVolunteerlist;

    private DatabaseReference mVolunteerDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applied_list);


        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();

        current_uid = mCurrentUser.getUid();


        mVolunteerDatabase= FirebaseDatabase.getInstance().getReference().child("Applied_volunteers").child(current_uid);


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
            protected void populateViewHolder(final VolunteerViewHolder viewHolder,final Volunteer model, int position) {

                viewHolder.setName(model.getName());
                viewHolder.setId(model.getId());




                viewHolder.parent_layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(AppliedListActivity.this,"You clicked on"+viewHolder.getPosition(),Toast.LENGTH_LONG).show();

                        Intent i= new Intent(AppliedListActivity.this,SelectedVolunteer.class);

                        String IdForApplyVol=model.getId();

                        Bundle bundle=new Bundle();
                        bundle.putString("id",IdForApplyVol);
                        i.putExtras(bundle);
                        startActivity(i);

                    }
                });



            }
        };

        mVolunteerlist.setAdapter(firebaseRecyclerAdapter);

    }

    public static class VolunteerViewHolder extends RecyclerView.ViewHolder{

        View mView;

        RelativeLayout parent_layout;

        public VolunteerViewHolder(View itemView) {
            super(itemView);

            mView=itemView;
        }

        public void setName(String name){
            TextView VolunteerNameView =(TextView) mView.findViewById(R.id.nameTextVol);
            VolunteerNameView.setText(name);

            parent_layout=itemView.findViewById(R.id.single_layout);

        }

        public void setId(String id){
            TextView VolunteerIdView =(TextView) mView.findViewById(R.id.idTextVol);
            VolunteerIdView.setText(id);

        }


        /*
        public void setPhoto(String photo){
            TextView VolunteerIdView =(TextView) mView.findViewById(R.id.photoTextVol);
            VolunteerIdView.setText(photo);

        }

        public void setQual(String qual){
            TextView VolunteerIdView =(TextView) mView.findViewById(R.id.qualTextVol);
            VolunteerIdView.setText(qual);

        }
*/




    }


}

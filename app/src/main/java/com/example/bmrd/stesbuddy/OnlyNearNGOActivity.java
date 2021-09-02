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

public class OnlyNearNGOActivity extends AppCompatActivity {

    private RecyclerView mNGOlist;


    private DatabaseReference mUserDatabase;
    private FirebaseUser mCurrentUser;
    private String current_uid;

    private DatabaseReference mNGODatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_only_near_ngo);


        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();

        current_uid = mCurrentUser.getUid();


        mNGODatabase= FirebaseDatabase.getInstance().getReference().child("NearByNGO").child(current_uid);


        mNGOlist=(RecyclerView) findViewById(R.id.NGO_list);
        mNGOlist.setHasFixedSize(true);
        mNGOlist.setLayoutManager(new LinearLayoutManager(this));




    }

    protected void onStart(){
        super.onStart();

        FirebaseRecyclerAdapter<NGO, NGOViewHolder> firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<NGO, NGOViewHolder>(
                NGO.class,
                R.layout.single_ngo_layout,
                NGOViewHolder.class,
                mNGODatabase


        ) {
            @Override
            protected void populateViewHolder(final NGOViewHolder viewHolder, final NGO model, final int position) {

                viewHolder.parent_layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(OnlyNearNGOActivity.this,"You clicked on"+viewHolder.getPosition(),Toast.LENGTH_LONG).show();

                        Intent i= new Intent(OnlyNearNGOActivity.this,SelectedNGO.class);




                        String IdForApplyVol=model.getId();
                        System.out.println(IdForApplyVol+"+++++++++++++++++----");


                        Bundle bundle=new Bundle();
                        bundle.putString("id",IdForApplyVol);
                        i.putExtras(bundle);
                        startActivity(i);

                    }
                });

                viewHolder.setName(model.getName());
                viewHolder.setDomain(model.getDomain());
                viewHolder.setLocation(model.getLocation());
                viewHolder.setDate(model.getDate());

                viewHolder.setVacancy(model.getVacancy());
                viewHolder.setId(model.getId());
            }
        };

        mNGOlist.setAdapter(firebaseRecyclerAdapter);

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
            TextView NGONameView =(TextView) mView.findViewById(R.id.textName);
            NGONameView.setText(name);

        }

        public void setDomain(String domain){
            TextView NGODomainView =(TextView) mView.findViewById(R.id.textDomain);
            NGODomainView.setText(domain);

        }

        public void setLocation(String location){
            TextView NGOLocationView =(TextView) mView.findViewById(R.id.textLoc);
            NGOLocationView.setText(location);

        }

        public void setVacancy(String vacancy){
            TextView NGOVacancyView =(TextView) mView.findViewById(R.id.vacancyText);
            NGOVacancyView.setText(vacancy);

        }


        public void setDate(String date){
            TextView NGODateView =(TextView) mView.findViewById(R.id.dateText);
            NGODateView.setText(date);

        }

        public void setId(String id){
            TextView NGOIdView =(TextView) mView.findViewById(R.id.idText);
            NGOIdView.setText(id);

        }



    }


}

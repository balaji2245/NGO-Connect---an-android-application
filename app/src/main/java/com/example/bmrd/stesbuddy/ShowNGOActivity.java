package com.example.bmrd.stesbuddy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ShowNGOActivity extends AppCompatActivity {

    private RecyclerView mNGOlist;

    private DatabaseReference mNGODatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_ngo);

        mNGODatabase= FirebaseDatabase.getInstance().getReference().child("NGO_list");


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
            protected void populateViewHolder(NGOViewHolder viewHolder, NGO model, int position) {

                viewHolder.setName(model.getName());
                viewHolder.setDomain(model.getDomain());
                viewHolder.setLocation(model.getLocation());
                viewHolder.setDate(model.getDate());
                viewHolder.setId(model.getId());
                viewHolder.setVacancy(model.getVacancy());

            }
        };

        mNGOlist.setAdapter(firebaseRecyclerAdapter);

    }

    public static class NGOViewHolder extends RecyclerView.ViewHolder{

        View mView;


        public NGOViewHolder(View itemView) {
            super(itemView);

            mView=itemView;
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

        public void setId(String id){
            TextView NGOLocationView =(TextView) mView.findViewById(R.id.idText);
            NGOLocationView.setText(id);

        }



        public void setVacancy(String vacancy){
            TextView NGOVacancyView =(TextView) mView.findViewById(R.id.vacancyText);
            NGOVacancyView.setText(vacancy);

        }


        public void setDate(String date){
            TextView NGODateView =(TextView) mView.findViewById(R.id.dateText);
            NGODateView.setText(date);

        }



    }


}

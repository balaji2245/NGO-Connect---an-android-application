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

public class GotSelectedInActivity extends AppCompatActivity {



    private RecyclerView mNGOlist;

    private DatabaseReference mNGODatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_got_selected_in);

        FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();

        String current_vol=current_user.getUid();




        mNGODatabase= FirebaseDatabase.getInstance().getReference().child("i_got_selected_in").child(current_vol);


        mNGOlist=(RecyclerView) findViewById(R.id.NGO_list);
        mNGOlist.setHasFixedSize(true);
        mNGOlist.setLayoutManager(new LinearLayoutManager(this));


    }

    protected void onStart(){
        super.onStart();

        FirebaseRecyclerAdapter<NGO, GotSelectedInActivity.NGOViewHolder> firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<NGO, GotSelectedInActivity.NGOViewHolder>(
                NGO.class,
                R.layout.single_ngo2_layout,
                GotSelectedInActivity.NGOViewHolder.class,
                mNGODatabase


        ) {
            @Override
            protected void populateViewHolder(GotSelectedInActivity.NGOViewHolder viewHolder, NGO model, int position) {

                viewHolder.setName(model.getName());
                viewHolder.setId(model.getId());

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


        public void setId(String id){
            TextView NGOIdView =(TextView) mView.findViewById(R.id.NGOidText);
            NGOIdView.setText(id);

        }

        public void setName(String name){
            TextView NGONameView =(TextView) mView.findViewById(R.id.NGOnameText);
            NGONameView.setText(name);

        }



    }


}

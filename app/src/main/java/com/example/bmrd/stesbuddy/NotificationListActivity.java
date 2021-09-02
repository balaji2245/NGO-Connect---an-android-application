package com.example.bmrd.stesbuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class NotificationListActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private RecyclerView mNotificationList;
    private DatabaseReference mNotificationDatabase;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_list);

        mAuth = FirebaseAuth.getInstance();

        mToolbar=(Toolbar) findViewById(R.id.main_app_bar);                //top toolbar
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("STES buddy");

        mNotificationList = (RecyclerView) findViewById((R.id.notification_list));
        mNotificationList.setHasFixedSize(true);
        mNotificationList.setLayoutManager(new LinearLayoutManager(this));

        mNotificationDatabase= FirebaseDatabase.getInstance().getReference().child("balaji");
        System.out.println("11111111111111111111111111111111111111111111");


        mNotificationDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Toast.makeText(NotificationListActivity.this,dataSnapshot.toString()+"jjjjjjjjjj",Toast.LENGTH_LONG).show();

         //       String name=dataSnapshot.child("info").getValue().toString();
           //     String email=dataSnapshot.child("time").getValue().toString();


                System.out.println("Im inside\\\\\\\\\\\\");

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("error////////////////////////////////////////////////////");


            }
        });


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {                 //for menu of three dots
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        if(item.getItemId() == R.id.main_logout_btn){               //for signing out
            FirebaseAuth.getInstance().signOut();
            sendToStart();
        }
        return true;
    }

    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        System.out.println("2222222222222222222222222222222222222222222222222222222222222222222222");

        if ( currentUser == null ) {            //to send the user to start page if they are not already logged in
            sendToStart();
        }
    ///////////////////////////////////      bro recycler code starts here

        FirebaseRecyclerAdapter<Notifications,NotificationViewHolder> firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<Notifications, NotificationViewHolder>(
                Notifications.class,
                R.layout.notification_single_layout,
                NotificationViewHolder.class,
                mNotificationDatabase
        ) {
            @Override
            protected void populateViewHolder(NotificationViewHolder viewHolder, Notifications model, int position) {

                viewHolder.setInfo(model.getInfo());

            System.out.println("/////////////////////////////////////////////////////////////////");
            }
        };



        mNotificationList.setAdapter(firebaseRecyclerAdapter);




    }


    public static class NotificationViewHolder extends RecyclerView.ViewHolder{

        View mView;

        public NotificationViewHolder(View itemView) {
            super(itemView);

            mView=itemView;

        }

        public void setInfo(String info){
            Button notificationButton=(Button) mView.findViewById(R.id.button2);
            notificationButton.setText(info);

            System.out.println("4444444444444444444444444444444444444444444444444444444444");

        }

    }



    private void sendToStart() {
        Intent startIntent=new Intent(NotificationListActivity.this,StartActivity.class);
        startActivity(startIntent);
        finish();                           //to make sure the user dont come back to main page by pressing back button

    }







}

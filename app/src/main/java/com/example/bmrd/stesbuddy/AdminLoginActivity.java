package com.example.bmrd.stesbuddy;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
//import com.google.firebase.storage.FirebaseStorage;
//import com.google.firebase.storage.StorageReference;
//import com.google.firebase.storage.UploadTask;
//import com.squareup.picasso.Picasso;
//import com.theartofdev.edmodo.cropper.CropImage;
//import com.theartofdev.edmodo.cropper.CropImageView;


//import id.zelory.compressor.Compressor;

public class AdminLoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private DatabaseReference mPostReference;

    private Toolbar mToolbar;

    private DatabaseReference mUserDatabase;
    private FirebaseUser mCurrentUser;

    private Task<Void> mDatabase;

    //android layout

    private EditText eventDate;
    private EditText vacacyEdit;


    private TextView mName;
    //   private StorageReference mImageStorage;

    private Button mAcceptNGO;
    private Button mAcceptRest;


    private String current_uid;



    @Override
    protected void onCreate(Bundle savedInstanceState) {


            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_admin_login);




            mToolbar=(Toolbar) findViewById(R.id.main_app_bar2);                //top toolbar
            setSupportActionBar(mToolbar);
            getSupportActionBar().setTitle("Admin Panel");



            mName = (TextView) findViewById(R.id.NGOnameText);



            eventDate = (EditText) findViewById(R.id.dateTextEnter);


            mAcceptNGO = (Button) findViewById(R.id.acceptNGOBTN);
            mAcceptRest = (Button) findViewById(R.id.acceptRestBTN);

            //         mImageStorage= FirebaseStorage.getInstance().getReference();


            ////
            mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();

            current_uid = mCurrentUser.getUid();



            mAcceptRest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    Intent login_intent=new Intent(AdminLoginActivity.this,AcceptRestActivity.class);
                    startActivity(login_intent);




                }
            });



            mAcceptNGO.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    Intent login_intent=new Intent(AdminLoginActivity.this,AcceptNGOActivity.class);
                    startActivity(login_intent);




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
        else
        if(item.getItemId() == R.id.dev_info){               //developer information

            Intent login_intent=new Intent(AdminLoginActivity.this,ContactActivity.class);
            startActivity(login_intent);
        }
        return true;
    }


    private void sendToStart() {
        Intent startIntent=new Intent(AdminLoginActivity.this,StartActivity.class);
        startActivity(startIntent);
        finish();                           //to make sure the user dont come back to main page by pressing back button

    }

}

package com.example.bmrd.stesbuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class REST24Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest24);

        Intent startIntent=new Intent(REST24Activity.this,StartActivity.class);
        startActivity(startIntent);
        finish();                           //to make sure the user dont come back to main page by pressing back button




    }
}

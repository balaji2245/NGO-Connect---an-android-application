package com.example.bmrd.stesbuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DiffLoginActivity extends AppCompatActivity {

    private Button mVolLoginButton;
    private Button mRestLoginButton;
    private Button mNGOLoginButton;
    private Button mAdminLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diff_login);

        mVolLoginButton=(Button) findViewById(R.id.volLoginBTN);
        mRestLoginButton=(Button) findViewById(R.id.restaurantLoginBTN);
        mNGOLoginButton=(Button) findViewById(R.id.NGOLoginBTN);
        mAdminLoginButton=(Button) findViewById(R.id.adminLoginBTN);


        mVolLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent reg_intent=new Intent(DiffLoginActivity.this,LoginActivity.class);
                startActivity(reg_intent);

            }
        });

        mRestLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent reg_intent=new Intent(DiffLoginActivity.this,LoginRestActivity.class);
                startActivity(reg_intent);

            }
        });


        mNGOLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent reg_intent=new Intent(DiffLoginActivity.this,LoginNGOActivity.class);
                startActivity(reg_intent);

            }
        });


        mAdminLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent reg_intent=new Intent(DiffLoginActivity.this,LoginAdminActivity.class);
                startActivity(reg_intent);

            }
        });




    }
}

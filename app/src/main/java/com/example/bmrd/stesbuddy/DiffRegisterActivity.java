package com.example.bmrd.stesbuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DiffRegisterActivity extends AppCompatActivity {

    private Button mVolRegButton;
    private Button mRestRegButton;
    private Button mNGORegButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diff_register);



        mVolRegButton=(Button) findViewById(R.id.volLoginBTN);
        mRestRegButton=(Button) findViewById(R.id.restaurantLoginBTN);
        mNGORegButton=(Button) findViewById(R.id.NGOLoginBTN);


        mVolRegButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent reg_intent=new Intent(DiffRegisterActivity.this,RegisterActivity.class);
                startActivity(reg_intent);

            }
        });

        mRestRegButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent reg_intent=new Intent(DiffRegisterActivity.this,RegisterRestActivity.class);
                startActivity(reg_intent);

            }
        });


        mNGORegButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent reg_intent=new Intent(DiffRegisterActivity.this,RegisterNGOActivity.class);
                startActivity(reg_intent);

            }
        });







    }
}

package com.example.bmrd.stesbuddy;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginAdminActivity extends AppCompatActivity {

    private TextInputLayout mLoginEmail;
    private TextInputLayout mLoginPassword;
    private Button mLoginButton;

    private FirebaseAuth mAuth;

    private Toolbar mToolbar;

    private ProgressDialog mLoginProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_admin);



        //Progress dialog
        mLoginProgress=new ProgressDialog(this);

        //Firebase
        mAuth = FirebaseAuth.getInstance();

        //Android fields

        mLoginEmail=(TextInputLayout) findViewById(R.id.login_email);
        mLoginPassword=(TextInputLayout) findViewById(R.id.login_password);
        mLoginButton=(Button)findViewById(R.id.login_btn);

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email=mLoginEmail.getEditText().getText().toString();
                String password=mLoginPassword.getEditText().getText().toString();

                if(!TextUtils.isEmpty(email) || !TextUtils.isEmpty(password)) {

                    mLoginProgress.setTitle("Logging in");
                    mLoginProgress.setMessage("Breath in ...Breath out...in...out!!");
                    mLoginProgress.setCanceledOnTouchOutside(false);
                    mLoginProgress.show();


                    loginUser(email,password);
                }

            }
        });

    }

    private void loginUser(String email, String password) {                 //Signin code

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){

                    mLoginProgress.dismiss();

                    Intent i= new Intent(LoginAdminActivity.this,WaitingActivity.class);
                    Bundle bundle=new Bundle();
                    bundle.putString("option","Admin");
                    i.putExtras(bundle);
                    startActivity(i);


                }else
                {
                    mLoginProgress.hide();
                    Toast.makeText(LoginAdminActivity.this,"Oops...error...please try again",Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}

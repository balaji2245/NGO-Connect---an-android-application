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
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterRestActivity extends AppCompatActivity {

    private TextInputLayout mUserName;
    private TextInputLayout mEmail;
    private TextInputLayout mPassword;
    private EditText mAddress;
    private EditText mfssai_no;
    private EditText contactNoText;

    private Button mCreateButton;

    private FirebaseAuth mAuth;

    private Toolbar mToolbar;
    private DatabaseReference mDatabase;

    private ProgressDialog mRegProgress;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_rest);


        //Progress dialog
        mRegProgress=new ProgressDialog(this);

        //Firebase
        mAuth = FirebaseAuth.getInstance();

        //Android fields
        mUserName=(TextInputLayout) findViewById(R.id.reg_user_name);
        mEmail=(TextInputLayout) findViewById(R.id.reg_email);
        mPassword=(TextInputLayout) findViewById(R.id.reg_password);
        mCreateButton=(Button)findViewById(R.id.reg_create_btn);
        mAddress=(EditText) findViewById(R.id.addEditText);
        mfssai_no=(EditText) findViewById(R.id.fssaiText);
        contactNoText=(EditText) findViewById(R.id.contactText);


        mCreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user_name=mUserName.getEditText().getText().toString();
                String email=mEmail.getEditText().getText().toString();
                String password=mPassword.getEditText().getText().toString();
                String address=mAddress.getText().toString();
                String fssai_no=mfssai_no.getText().toString();
                String contactNo=contactNoText.getText().toString();


                if(contactNo.matches("-?\\d+") && contactNo.length()==10 || contactNo.length()==11 || contactNo.length()==13) {
                    if (!TextUtils.isEmpty(user_name) || !TextUtils.isEmpty(email) || !TextUtils.isEmpty(password) || !TextUtils.isEmpty(address) || !TextUtils.isEmpty(contactNo)) {

                        mRegProgress.setTitle("Signing Up");
                        mRegProgress.setMessage("Breath in ...Breath out...in...out!!");
                        mRegProgress.setCanceledOnTouchOutside(false);
                        mRegProgress.show();

                        register_user(user_name, email, password, address, fssai_no, contactNo);
                    }
                }
                else{
                    Toast.makeText(RegisterRestActivity.this, "Please enter correct contact number", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void register_user(final String user_name, String email, String password,final String user_add, final String fssai_no, final String contactNo) {

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){


                    FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();

                    String uid=current_user.getUid();
                    mDatabase=FirebaseDatabase.getInstance().getReference().child("future_rest").child(uid);

                    HashMap<String,String> VolunteersMap= new HashMap<>();
                    VolunteersMap.put("name",user_name);
                    VolunteersMap.put("location",user_add);
                    VolunteersMap.put("food","");
                    VolunteersMap.put("lat","");
                    VolunteersMap.put("lon","");
                    VolunteersMap.put("id",uid);
                    VolunteersMap.put("status","wait");
                    VolunteersMap.put("contact",contactNo);
                    VolunteersMap.put("status","wait");
                    VolunteersMap.put("fssai",fssai_no);
                    //VolunteersMap.put("Email_id",email);

                    mDatabase.setValue(VolunteersMap);


                    ////////////////////
                    mDatabase=FirebaseDatabase.getInstance().getReference().child("All_users").child(uid);

                    HashMap<String,String> VolunteersMap2= new HashMap<>();
                    VolunteersMap2.put("id",uid);
                    VolunteersMap2.put("type","rest");
                    //VolunteersMap.put("Email_id",email);

                    mDatabase.setValue(VolunteersMap2);




                    mRegProgress.dismiss();                                 //dismiss progress bar onn success


                    Intent login_intent=new Intent(RegisterRestActivity.this,MapsActivity2.class);
                    startActivity(login_intent);


/*
                    Intent mainIntent=new Intent(RegisterRestActivity.this,REST24Activity.class);
                    mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(mainIntent);
                    finish();
*/
                }else
                {
                    mRegProgress.hide();

                    Toast.makeText(RegisterRestActivity.this,"Oops...error...please try again",Toast.LENGTH_LONG).show();
                }


            }
        });

    }
}

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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private TextInputLayout mUserName;
    private TextInputLayout mEmail;
    private TextInputLayout mPassword;


    private EditText mContact;
    private EditText mAge;
    private EditText mQualification;

    String gender="not selected";

    private Button mCreateButton;

    private FirebaseAuth mAuth;

    private Toolbar mToolbar;
    private DatabaseReference mDatabase;

    private DatabaseReference mDatabase2;

    private ProgressDialog mRegProgress;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        Spinner genderSpinner=findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter= ArrayAdapter.createFromResource(this, R.array.Gender, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(adapter);
        genderSpinner.setOnItemSelectedListener(this);


        //Progress dialog
        mRegProgress=new ProgressDialog(this);

        //Firebase
        mAuth = FirebaseAuth.getInstance();

        //Android fields
        mUserName=(TextInputLayout) findViewById(R.id.reg_user_name);
        mEmail=(TextInputLayout) findViewById(R.id.reg_email);
        mPassword=(TextInputLayout) findViewById(R.id.reg_password);
        mCreateButton=(Button)findViewById(R.id.reg_create_btn);

        mContact=(EditText) findViewById(R.id.contactText);
        mAge=(EditText) findViewById(R.id.ageText);
        mQualification=(EditText) findViewById(R.id.qualificationText);


        mCreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user_name=mUserName.getEditText().getText().toString();
                String email=mEmail.getEditText().getText().toString();
                String password=mPassword.getEditText().getText().toString();
                String contact=mContact.getEditableText().toString();

                if(contact.matches("-?\\d+") && contact.length()==10 || contact.length()==11 || contact.length()==13) {
                    if (!TextUtils.isEmpty(user_name) || !TextUtils.isEmpty(email) || !TextUtils.isEmpty(password) || !gender.equals("not selected")) {

                        mRegProgress.setTitle("Signing Up");
                        mRegProgress.setMessage("Breath in ...Breath out...in...out!!");
                        mRegProgress.setCanceledOnTouchOutside(false);
                        mRegProgress.show();

                        register_user(user_name, email, password);
                    }
                }
                else{

                    Toast.makeText(RegisterActivity.this, "Please enter correct contact number", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void register_user(final String user_name, String email, String password) {

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){


                    FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();

                    String uid=current_user.getUid();

                    String contact=mContact.getEditableText().toString();
                    String age=mAge.getEditableText().toString();
                    String qualification=mQualification.getEditableText().toString();


                    mDatabase=FirebaseDatabase.getInstance().getReference().child("Volunteers").child(uid);

                    HashMap<String,String> VolunteersMap= new HashMap<>();
                    VolunteersMap.put("Name",user_name);
                 //   VolunteersMap.put("Recommend"," ");
                    VolunteersMap.put("contact",contact);
                    VolunteersMap.put("age",age);
                    VolunteersMap.put("qual",qualification);
                    VolunteersMap.put("gender",gender);
                    VolunteersMap.put("photo","");

                    //VolunteersMap.put("Email_id",email);

                    mDatabase.setValue(VolunteersMap);

                addRecommend(uid);


/*
                    mDatabase2=FirebaseDatabase.getInstance().getReference().child("Volunteers").child(uid);
                    mDatabase2.push().setValue("Recommend");

                    mDatabase2=FirebaseDatabase.getInstance().getReference().child("Volunteers").child(uid).child("Recommend");
                    mDatabase2.push().setValue("education");
                    mDatabase2.push().setValue("social");
                    mDatabase2.push().setValue("tribal");
                    mDatabase2.push().setValue("youth");
                    mDatabase2.push().setValue("female");
*/


//
//                    HashMap<String,String> VolunteersMap3= new HashMap<>();
//                    VolunteersMap.put("education","0");
//
//                    System.out.println("--------------------------------------------");
//
//                    VolunteersMap.put("social","0");
//                    VolunteersMap.put("youth","0");
//                    VolunteersMap.put("tribal","0");
//                    VolunteersMap.put("female","0");
//
//                    mDatabase2.setValue(VolunteersMap3);
//


                    ////////////////////
                    mDatabase=FirebaseDatabase.getInstance().getReference().child("All_users").child(uid);

                    HashMap<String,String> VolunteersMap2= new HashMap<>();
                    VolunteersMap2.put("id",uid);
                    VolunteersMap2.put("type","vol");
                    //VolunteersMap.put("Email_id",email);

                    mDatabase.setValue(VolunteersMap2);




                    mRegProgress.dismiss();                                 //dismiss progress bar onn success

                    Intent mainIntent=new Intent(RegisterActivity.this,HomVolActivity.class);
                    mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(mainIntent);
                    finish();

                }else
                {
                    mRegProgress.hide();

                    Toast.makeText(RegisterActivity.this,"Oops...error...please try again",Toast.LENGTH_LONG).show();
                }


            }
        });

    }

    public void addRecommend(String uid){


        mDatabase=FirebaseDatabase.getInstance().getReference().child("Volunteers").child(uid).child("Recommend");

        HashMap<String,String> VolunteersMap2= new HashMap<>();
        VolunteersMap2.put("education","0");
        VolunteersMap2.put("social","0");
        VolunteersMap2.put("female","0");
        VolunteersMap2.put("tribal","0");
        VolunteersMap2.put("youth","0");
        mDatabase.setValue(VolunteersMap2);

    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            gender= adapterView.getItemAtPosition(i).toString();

        //Toast.makeText(RegisterActivity.this,gender,Toast.LENGTH_LONG).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}

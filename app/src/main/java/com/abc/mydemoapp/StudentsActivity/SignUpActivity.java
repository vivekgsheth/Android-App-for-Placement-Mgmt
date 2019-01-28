package com.abc.mydemoapp.StudentsActivity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.abc.mydemoapp.MainActivity;
import com.abc.mydemoapp.R;
import com.abc.mydemoapp.SelectRoleActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {

    DatabaseReference databasestudent;
    String role;
    EditText email;
    EditText pass;
    EditText cpi,idnumber;
    FirebaseAuth mAuth;
    Button signupbtn,loginbtn;
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        loginbtn =(Button)findViewById(R.id.loginbtn);
        email = (EditText)findViewById(R.id.email);
        pass = (EditText)findViewById(R.id.pass);
        cpi = (EditText)findViewById(R.id.pass1);
        mAuth = FirebaseAuth.getInstance();
        signupbtn = (Button)findViewById(R.id.signupbtn);
        spinner = (Spinner)findViewById(R.id.spinner);
        idnumber = (EditText)findViewById(R.id.Idnumber);
        databasestudent = FirebaseDatabase.getInstance().getReference("Students");
        //If we donot pass anything in the getreference method then we will get the reference of the
        //root node but we want the reference of the Students node.


        Intent intent = getIntent();
        role = intent.getStringExtra(SelectRoleActivity.EXTRA_TEXT);
        //Here we are retrieving the user selection of the activity.

        ArrayAdapter<String> myadapter = new ArrayAdapter<String>
                (SignUpActivity.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.branchname));
        //Basically adpater is the container that will hold the values which we will read through spinner.
        myadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(myadapter);


        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //checking password and confirm password match
                //if(pass.getText().toString().equals(pass1.getText().toString()))
                //{
                if(!TextUtils.isEmpty(cpi.getText().toString())&&!TextUtils.isEmpty(idnumber.getText().toString()))
                {
                    //Toast.makeText(SignUpActivity.this,"Calling firebase methods to register users...",Toast.LENGTH_LONG).show();


                    //Verification mail code
                    mAuth.createUserWithEmailAndPassword(email.getText().toString(), pass.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                mAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful())
                                        {
                                            //if user has verified the email then register the user

                                            Toast.makeText(SignUpActivity.this, "User registered successfully please check your email for verification...",
                                                    Toast.LENGTH_LONG).show();
                                            email.setText("");
                                            pass.setText("");
                                            cpi.setText("");
                                            idnumber.setText("");
                                        }
                                        else
                                        {
                                            Toast.makeText(SignUpActivity.this,task.getException().getMessage(),
                                                    Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });

                            float cpis = Float.parseFloat(cpi.getText().toString());
                            String id = databasestudent.push().getKey();
                            Student student = new Student(id,email.getText().toString(),spinner.getSelectedItem().toString(),pass.getText().toString(),idnumber.getText().toString(),cpis,role);
                            databasestudent.child(id).setValue(student);
                            }
                            else {
                                Toast.makeText(SignUpActivity.this, task.getException().getMessage(),
                                        Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
                //if(pass.getText().toString()!=pass1.getText().toString())
                else
                {
                    Toast.makeText(SignUpActivity.this,"Fill all the fields ...",Toast.LENGTH_LONG).show();
                    return;
                }
               /* else
                {
                    Toast.makeText(SignUpActivity.this,"Password and ConfirmPassword Donot match...",Toast.LENGTH_LONG).show();
                    return;
                }*/

            }
        });
    }
}

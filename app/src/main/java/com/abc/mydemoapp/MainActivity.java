package com.abc.mydemoapp;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.abc.mydemoapp.StudentsActivity.ProfileActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth userAuth;
    EditText pass,email;
    Button forgotpasswordbtn,loginbtn,signupbtn;
    RelativeLayout rellay1,rellay2;
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {//Code for splashing in the app(i.e. For rainbow that appears at the starting and then
                                //goes away... )
            rellay1.setVisibility(View.VISIBLE);
            rellay2.setVisibility(View.VISIBLE);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pass = (EditText)findViewById(R.id.pass);
        email = (EditText)findViewById(R.id.email);
        forgotpasswordbtn = (Button)findViewById(R.id.forgotpasswordbtn);
        loginbtn = (Button)findViewById(R.id.loginbtn);
        signupbtn = (Button)findViewById(R.id.signinbtn);
        rellay1 = (RelativeLayout)findViewById(R.id.rellay1);
        rellay2 = (RelativeLayout)findViewById(R.id.rellay2);
        userAuth = FirebaseAuth.getInstance();
        handler.postDelayed(runnable,2000);//2000 is the timeout for splash

       signupbtn.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(MainActivity.this,SelectRoleActivity.class);
               startActivity(intent);
           }
       });

       loginbtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               userAuth.signInWithEmailAndPassword(email.getText().toString(),pass.getText().toString())
                       .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                           @Override
                           public void onComplete(@NonNull Task<AuthResult> task) {
                               if(task.isSuccessful())
                               {
                                   //checking if the user has verified the email or not
                                   if(userAuth.getCurrentUser().isEmailVerified())
                                   {
                                       Intent intent = new Intent(MainActivity.this,ProfileActivity.class);
                                       startActivity(intent);
                                   }
                                   else
                                   {
                                       Toast.makeText(MainActivity.this,"Please verify your email address...",Toast.LENGTH_LONG).show();
                                   }

                               }
                               else
                               {
                                   Toast.makeText(MainActivity.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                               }
                           }
                       });
           }
       });
       forgotpasswordbtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(MainActivity.this,ForgotPasswordActivity.class);
               startActivity(intent);
           }
       });
    }


    /*private void goToSignupActivity(View v)
    {

    }*/

}

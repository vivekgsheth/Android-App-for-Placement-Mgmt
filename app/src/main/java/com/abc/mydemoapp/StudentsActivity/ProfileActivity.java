package com.abc.mydemoapp.StudentsActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.abc.mydemoapp.MainActivity;
import com.abc.mydemoapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    FirebaseUser currentuser;
    TextView useremail;
    Button logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        useremail = (TextView)findViewById(R.id.tvUserEmail);
        logout = (Button)findViewById(R.id.btnsignout);

        firebaseAuth = FirebaseAuth.getInstance();
        currentuser = firebaseAuth.getCurrentUser();

        useremail.setText(currentuser.getEmail());

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(ProfileActivity.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//This line and below line are used to prevent user from going
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);// back to profileactivity by pressing back button.If the user is logged out of the app.
                startActivity(intent);
            }
        });
    }
}

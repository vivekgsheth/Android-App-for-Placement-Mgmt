package com.abc.mydemoapp.StudentsActivity;

import android.app.Activity;
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
import com.abc.mydemoapp.TPOActivity.StudentTan;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class VerifyStudentActivity extends AppCompatActivity {
    DatabaseReference databasestudenttan;
    FirebaseAuth mAuth;
   // String role;
   // private  String id;
    EditText email,studyear;
    EditText pass;
    EditText cpi,Studentname,rollno;
    Button verifybtn;
    Spinner spinner,spinner3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_student);

        verifybtn =(Button)findViewById(R.id.verifybtn);
        email = (EditText)findViewById(R.id.email);
        pass = (EditText)findViewById(R.id.pass);
        cpi = (EditText)findViewById(R.id.pass1);
        mAuth = FirebaseAuth.getInstance();
        studyear = (EditText)findViewById(R.id.studyear);
        spinner = (Spinner)findViewById(R.id.spinner);
        Studentname = (EditText)findViewById(R.id.Studentname);
        //If we donot pass anything in the getreference method then we will get the reference of the
        //root node but we want the reference of the Students node.
        rollno =  (EditText)findViewById(R.id.rollno);

        // Intent intent = getIntent();
        // role = intent.getStringExtra(SelectRoleActivity.EXTRA_TEXT);
        //role="Student";
        //Here we are retrieving the user selection of the activity.
        databasestudenttan = FirebaseDatabase.getInstance().getReference("StudentTan");

        ArrayAdapter<String> myadapter = new ArrayAdapter<String>
                (VerifyStudentActivity.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.branchname));
        //Basically adpater is the container that will hold the values which we will read through spinner.
        myadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(myadapter);




        verifybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databasestudenttan.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        verifystudentdetails(dataSnapshot);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

    }
    private void verifystudentdetails(DataSnapshot ds)
    {

        if(!TextUtils.isEmpty(studyear.getText().toString())&&!TextUtils.isEmpty(cpi.getText().toString())&&!TextUtils.isEmpty(Studentname.getText().toString()) && !TextUtils.isEmpty(rollno.getText().toString()))
        {
            //Toast.makeText(SignUpActivity.this,"Calling firebase methods to register users...",Toast.LENGTH_LONG).show();
            float temp = Float.parseFloat(cpi.getText().toString());
            if (temp < 5.0 || temp > 10.0) {
                Toast.makeText(VerifyStudentActivity.this, "Cpi must be between 5.0 to 10.0 ...", Toast.LENGTH_LONG).show();
                return;
            }

            if (Studentname.getText().toString().matches(".*\\d+.*")) {
                Toast.makeText(VerifyStudentActivity.this, "Enter valid username...", Toast.LENGTH_LONG).show();
                return;
            }
            float cpis = Float.parseFloat(cpi.getText().toString());

            String id = databasestudenttan.push().getKey();
            String role="Student";
            StudentTan s = new StudentTan(rollno.getText().toString(),email.getText().toString(),Studentname.getText().toString(),spinner.getSelectedItem().toString(),pass.getText().toString(),cpis,id,role,Integer.parseInt(studyear.getText().toString()));
            databasestudenttan.child(id).setValue(s);
            //String studentrollno, String studentemail, String studentname, String branchname, String pass, float studentcpi,String id) {

            email.setText("");
            pass.setText("");
            cpi.setText("");
            Studentname.setText("");
            rollno.setText("");
            studyear.setText("");

            Toast.makeText(VerifyStudentActivity.this,"Wait for TPO's Approval ...",Toast.LENGTH_LONG).show();

            Intent intent = new Intent(VerifyStudentActivity.this,MainActivity.class);
            startActivity(intent);
            return;
        }
        else
        {
            Toast.makeText(VerifyStudentActivity.this,"Fill all the fields ...",Toast.LENGTH_LONG).show();
            return;
        }

    }

}

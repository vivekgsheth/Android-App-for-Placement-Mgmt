package com.abc.mydemoapp.TPOActivity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.abc.mydemoapp.R;
import com.abc.mydemoapp.StudentsActivity.SignUpActivity;
import com.abc.mydemoapp.StudentsActivity.Student;
import com.abc.mydemoapp.StudentsActivity.StudentJob;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StudentDetailsActivity extends AppCompatActivity {

    DatabaseReference dbstudenttan,databasestudent, dbrejectstudent ;
    private TextView textView1,textView2,textView3,textView4,textView5,textView6;
    private Button btnapprove,btnreject;
    private String id;
    FirebaseAuth mAuth;

    String branchname,password,email,name,rollno;
    Float studentcpi;
    Integer year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);


        Intent i = getIntent();
        id = i.getStringExtra("key");
        mAuth = FirebaseAuth.getInstance();
        textView1 = (TextView)findViewById(R.id.textView1);
        textView2 = (TextView)findViewById(R.id.textView2);
        textView3 = (TextView)findViewById(R.id.textView3);
        textView4 = (TextView)findViewById(R.id.textView4);
        textView5 = (TextView)findViewById(R.id.textView5);
        textView6 = (TextView)findViewById(R.id.textView6);
        btnreject = (Button)findViewById(R.id.btnreject);
        btnapprove = (Button)findViewById(R.id.btnapprove);
        btnreject=(Button)findViewById(R.id.btnreject);
        dbrejectstudent = FirebaseDatabase.getInstance().getReference("RejectStudent");
        dbstudenttan = FirebaseDatabase.getInstance().getReference("StudentTan");
        databasestudent=FirebaseDatabase.getInstance().getReference("Student");
        dbstudenttan.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren())
                {
                    StudentTan st = ds.getValue(StudentTan.class);
                    if(st.getId().equals(id))
                    {
                        Float a = st.getStudentcpi();

                        branchname=st.getBranchname();
                        password=st.getPass();
                        email = st.getStudentemail();
                        name=st.getStudentname();
                        rollno=st.getStudentrollno();
                        studentcpi=a;
                        year = st.getYear();

                        textView1.setText(st.getBranchname());
                        textView2.setText(st.getPass());
                        textView3.setText(st.getStudentemail());
                        textView4.setText(st.getStudentname());
                        textView5.setText(st.getRole());
                        textView6.setText(a.toString());
                    }


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btnapprove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                dbstudenttan.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot ds : dataSnapshot.getChildren()) {
                            StudentTan st = ds.getValue(StudentTan.class);
                            if(st.getId().equals(id))
                            {
                                DatabaseReference dbstudenttan1 = FirebaseDatabase.getInstance().getReference("StudentTan").child(id);
                                dbstudenttan1.removeValue();
                                //Toast.makeText(context,"Student deleted successfully",Toast.LENGTH_LONG).show();
                                return;
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            mAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        //if user has verified the email then register the user

                                        Toast.makeText(StudentDetailsActivity.this, "Student approved successfully...",
                                                Toast.LENGTH_LONG).show();

                                    } else {
                                        Toast.makeText(StudentDetailsActivity.this, task.getException().getMessage(),
                                                Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                            //email_password_role
                            //float cpis = Float.parseFloat(textView6.getText().toString());

                            String id1 = databasestudent.push().getKey();
                            Student student = new Student(id1,email,branchname,password,name,studentcpi,"Student",rollno,year);
                            //String id,String emailaddress, String branchname, String password, String name, float cpi,String role,String rollno,int year


                            databasestudent.child(id1).setValue(student);
                            //(id,email.getText().toString(),spinner.getSelectedItem().toString(),pass.getText().toString(),Studentname.getText().toString(),cpis,role,rollno.getText().toString())
                        } else {
                            Toast.makeText(StudentDetailsActivity.this, task.getException().getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    }

                });
            }
        });
        btnreject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                dbstudenttan.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot ds : dataSnapshot.getChildren()) {
                            StudentTan st = ds.getValue(StudentTan.class);
                            if(st.getId().equals(id))
                            {
                                String id = dbrejectstudent.push().getKey();
                                DatabaseReference dbstudenttan1 = FirebaseDatabase.getInstance().getReference("StudentTan").child(id);
                                RejectStudent rejectStudent = new RejectStudent(rollno,email,name,branchname,password,id,"Student",true);
                                dbrejectstudent.child(id).setValue(rejectStudent);
                                //dbstudenttan1.removeValue();
                                //Toast.makeText(context,"Student deleted successfully",Toast.LENGTH_LONG).show();
                                return;
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

//                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            mAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
//                                @Override
//                                public void onComplete(@NonNull Task<Void> task) {
//                                    if (task.isSuccessful()) {
//                                        //if user has verified the email then register the user
//
//                                        Toast.makeText(StudentDetailsActivity.this, "Student approved successfully...",
//                                                Toast.LENGTH_LONG).show();
//
//                                    } else {
//                                        Toast.makeText(StudentDetailsActivity.this, task.getException().getMessage(),
//                                                Toast.LENGTH_LONG).show();
//                                    }
//                                }
//                            });
//                            //email_password_role
//                            //float cpis = Float.parseFloat(textView6.getText().toString());
//
//                            String id1 = databasestudent.push().getKey();
//                            Student student = new Student(id1,email,branchname,password,name,studentcpi,"Student",rollno,year);
//                            //String id,String emailaddress, String branchname, String password, String name, float cpi,String role,String rollno,int year
//
//
//                            databasestudent.child(id1).setValue(student);
//                            //(id,email.getText().toString(),spinner.getSelectedItem().toString(),pass.getText().toString(),Studentname.getText().toString(),cpis,role,rollno.getText().toString())
//                        } else {
//                            Toast.makeText(StudentDetailsActivity.this, task.getException().getMessage(),
//                                    Toast.LENGTH_LONG).show();
//                        }
//                    }
//
//                });
            }
        });

    }
}

package com.abc.mydemoapp.TPOActivity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.abc.mydemoapp.CompanyActivity.Company;
import com.abc.mydemoapp.R;
import com.abc.mydemoapp.StudentsActivity.Student;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CompanyDetailsActivity extends AppCompatActivity {

    DatabaseReference dbcompanytan,databasecompany, dbrejectcompany ;
    private TextView textView1,textView2,textView3,textView4,textView5,textView6;
    private Button btnapprove,btnreject;
    private String id;
    private ArrayList<String> br = new ArrayList<String>();
    FirebaseAuth mAuth;

    String cname,cpass,cemail,caddress;
    //Float studentcpi;
    //Integer year;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_details);
        


            Intent i = getIntent();
            id = i.getStringExtra("key");
            mAuth = FirebaseAuth.getInstance();
            textView1 = (TextView)findViewById(R.id.textView1);
            textView2 = (TextView)findViewById(R.id.textView2);
            textView3 = (TextView)findViewById(R.id.textView3);
            textView4 = (TextView)findViewById(R.id.textView4);
//            textView5 = (TextView)findViewById(R.id.textView5);
//            textView6 = (TextView)findViewById(R.id.textView6);
           // btnreject = (Button)findViewById(R.id.btnreject);
            btnapprove = (Button)findViewById(R.id.btnapprove);
            btnreject=(Button)findViewById(R.id.btnreject);
            dbrejectcompany = FirebaseDatabase.getInstance().getReference("RejectCompany");
            dbcompanytan = FirebaseDatabase.getInstance().getReference("Tan");
            databasecompany=FirebaseDatabase.getInstance().getReference("Company");
            dbcompanytan.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for(DataSnapshot ds : dataSnapshot.getChildren())
                    {
                        Tan st = ds.getValue(Tan.class);
                        if(st.getId().equals(id))
                        {
                            cname = st.getCompanyname();
                            caddress=st.getCompanyaddress();
                            cemail=st.getCompanyemailadress();
                            cpass=st.getPass();
                            br = st.getBranch();
                            textView1.setText(cname);
                            textView2.setText(cpass);
                            textView3.setText(cemail);
                            textView4.setText(caddress);
                           // textView5.setText(st.getRole());
                            //textView6.setText(a.toString());
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


                dbcompanytan.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot ds : dataSnapshot.getChildren()) {
                            Tan st = ds.getValue(Tan.class);
                            if(st.getId().equals(id))
                            {
                                DatabaseReference dbcompanytan1 = FirebaseDatabase.getInstance().getReference("Tan").child(id);
                                dbcompanytan1.removeValue();
                                //Toast.makeText(context,"Student deleted successfully",Toast.LENGTH_LONG).show();
                                return;
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                mAuth.createUserWithEmailAndPassword(cemail,cpass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            mAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        //if user has verified the email then register the user

                                        Toast.makeText(CompanyDetailsActivity.this, "Company approved successfully...",
                                                Toast.LENGTH_LONG).show();

                                    } else {
                                        Toast.makeText(CompanyDetailsActivity.this, task.getException().getMessage(),
                                                Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                            //email_password_role
                            //float cpis = Float.parseFloat(textView6.getText().toString());

                            String id1 = databasecompany.push().getKey();
                          //  Student student = new Student(id1,email,branchname,password,name,studentcpi,"Student",rollno,year);
                            //String id,String emailaddress, String branchname, String password, String name, float cpi,String role,String rollno,int year
                            //public Company(String id,String companyname, String address,  String emailaddress, String password,String role,ArrayList<String> branch
                            Company c = new Company(id1,cname,caddress,cemail,cpass,"Company",br);
                            databasecompany.child(id1).setValue(c);
                            //(id,email.getText().toString(),spinner.getSelectedItem().toString(),pass.getText().toString(),Studentname.getText().toString(),cpis,role,rollno.getText().toString())
                        } else {
                            Toast.makeText(CompanyDetailsActivity.this, task.getException().getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    }

                });
            }
        });
        btnreject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                dbcompanytan.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot ds : dataSnapshot.getChildren()) {
                            Tan st = ds.getValue(Tan.class);
                            if(st.getId().equals(id))
                            {
                                String id = dbrejectcompany.push().getKey();
                                DatabaseReference dbstudenttan1 = FirebaseDatabase.getInstance().getReference("Tan").child(id);
                                //public RejectCompany(String id, String companyname, String companyemailadress, String companyaddress, String pass, String role, ArrayList<String> branch,Boolean reject) {
                                RejectCompany rejectStudent = new RejectCompany(id,cname,cemail,caddress,cpass,"Company",true);
                                dbrejectcompany.child(id).setValue(rejectStudent);
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

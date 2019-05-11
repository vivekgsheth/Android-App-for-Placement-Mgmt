package com.abc.mydemoapp.CompanyActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.abc.mydemoapp.MainActivity;
import com.abc.mydemoapp.R;
import com.abc.mydemoapp.TPOActivity.Tan;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;




public class VerifyCompanyActivity extends AppCompatActivity {
DatabaseReference databasecompany;
    EditText pass,email,companyname,companyaddress;
    Button btnverify;
   // DatabaseReference databasecompany;
    FirebaseAuth mAuth;
    CheckBox checkBox1,checkBox2,checkBox3;
    //String branch = "";
    ArrayList<String> branch = new ArrayList<String>();
    ArrayList<String> l = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_company);


        pass = (EditText)findViewById(R.id.pass);
        //pass.setText(getIntent().getStringExtra("cpass"));
        //pass.setEnabled(false);
        email = (EditText)findViewById(R.id.email);
        //email.setText(getIntent().getStringExtra("cemail"));
        //email.setEnabled(false);
        companyname = (EditText)findViewById(R.id.companyname);


        companyaddress=(EditText)findViewById(R.id.companyaddress);

        checkBox1 =(CheckBox)findViewById(R.id.checkBox1);
        checkBox2 =(CheckBox)findViewById(R.id.checkBox2);
        checkBox3=(CheckBox)findViewById(R.id.checkBox3);
        btnverify = (Button)findViewById(R.id.btnverify);

        btnverify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                databasecompany=FirebaseDatabase.getInstance().getReference("Tan");
                if(checkBox1.isChecked()){
                    branch.add(checkBox1.getText().toString());
                }

                if(checkBox2.isChecked()){
                    branch.add(checkBox2.getText().toString());
                }

                if(checkBox3.isChecked()){
                    branch.add(checkBox3.getText().toString());
                }
                String id = databasecompany.push().getKey();

                Tan t = new Tan(companyname.getText().toString(),email.getText().toString(),companyaddress.getText().toString(),pass.getText().toString(),id,branch,"Company");

                databasecompany.child(id).setValue(t);
//                for(Integer i=0;i<l.size();i++){
//                    databasecompany.child(id).child("branch").child(i.toString()).setValue(l.get(i));
//                }

                Log.w("in verify",id);
                Intent intent  = new Intent(VerifyCompanyActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });



        //Company company = new Company(id,companyname.getText().toString(),companyaddress.getText().toString(),TAN.getText().toString(),email.getText().toString(),pass.getText().toString(),role,branch);
        //databasecompany.child(id).setValue(company);
        //String companyname, String companyemailadress, String companyaddress, String pass,String id,String branch,String role



    }
}

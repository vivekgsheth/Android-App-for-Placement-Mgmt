package com.abc.mydemoapp.TPOActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.abc.mydemoapp.R;

import org.w3c.dom.Text;

public class SelectedStudentDetailsActivity extends AppCompatActivity {



    private  String studentemail,studentid,jobid,jobtitle,compemail,compname;
    private TextView textView7,textView8,textView9,textView10,textView11,textView12;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_student_details);

        Intent i = getIntent();
        studentemail = i.getStringExtra("studentemail");
        //studentid = i.getStringExtra("studentid");
       // jobid = i.getStringExtra("jobid");
        jobtitle = i.getStringExtra("jobtitle");
        compemail = i.getStringExtra("compemail");
        compname = i.getStringExtra("compname");

        textView7 = (TextView) findViewById(R.id.textView7);
     //   textView8 = (TextView)findViewById(R.id.textView8);
       // textView9 = (TextView)findViewById(R.id.textView9);
        textView10 = (TextView)findViewById(R.id.textView10);
        textView11 = (TextView)findViewById(R.id.textView11);
        textView12  = (TextView)findViewById(R.id.textView12);





        textView7.setText(studentemail);
        //textView8.setText(studentid);
        //textView9.setText(jobid);
        textView10.setText(jobtitle);
        textView11.setText(compemail);
        textView12.setText(compname);
        //Log.w("Hi","Hello");

    }
}

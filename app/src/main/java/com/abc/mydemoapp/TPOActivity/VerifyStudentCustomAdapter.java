package com.abc.mydemoapp.TPOActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.abc.mydemoapp.R;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class VerifyStudentCustomAdapter extends BaseAdapter implements ListAdapter
{
    //DatabaseReference databasestudenttan = FirebaseDatabase.getInstance().getReference("StudentTan");
    //public static  final String EXTRA_TEXT = "com.abc.mydemoapp.TPOActivity.EXTRA_TEXT";
    private ArrayList<String> sname = new ArrayList<String>();
    private ArrayList<String> keylist = new ArrayList<String>();
    private Context context;
    //private String key;
    //private String email;
    //private String password;
    //private ArrayList<String> keyList = new ArrayList<>();
    int count=0;
   FirebaseAuth.AuthStateListener mAuthListener;
    FirebaseUser firebaseUser ;
    FirebaseAuth mauth = FirebaseAuth.getInstance();


    VerifyStudentCustomAdapter(ArrayList<String> sname,Context context,ArrayList<String> keylist)
    {
        this.sname=sname;

        this.context=context;
        this.keylist=keylist;
    }

    @Override
    public int getCount() {
        return sname.size();
    }

    @Override
    public Object getItem(int position) {
        return sname.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.customverifystudent, null);
        }

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                firebaseUser=FirebaseAuth.getInstance().getCurrentUser();

                if(firebaseUser!=null)
                {
                    Log.w("Congo","Your code is executing perfctly...");
                }
            }
        };

        TextView txtstudentrollno = (TextView)view.findViewById(R.id.txtstudentrollno);
        txtstudentrollno.setText(sname.get(position));

        Button btndetails = (Button)view.findViewById(R.id.btndetails);
        btndetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context,StudentDetailsActivity.class);
                i.putExtra("key",keylist.get(position));
                context.startActivity(i);
            }
        });

        return view;
    }

}

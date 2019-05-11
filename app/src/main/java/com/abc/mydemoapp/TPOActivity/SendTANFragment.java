package com.abc.mydemoapp.TPOActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.abc.mydemoapp.R;
import com.abc.mydemoapp.StudentsActivity.CustomPendingAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SendTANFragment extends Fragment {

//    private Button btnsendtan;
//    private EditText txtcmpname,txttan,txtcmpemail,txtcmpaddress,txtcmppass;
    private DatabaseReference dbtan;
    private ArrayList<String> sroll = new ArrayList<String>();
    private ArrayList<String> keylist = new ArrayList<String>();
    private ListView listView;
//    private String companyname;
//    private String tannumber;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_send_tan,container,false);
        dbtan = FirebaseDatabase.getInstance().getReference("StudentTan");
        listView = view.findViewById(R.id.listView);
        dbtan.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren())
                {
                    StudentTan st = ds.getValue(StudentTan.class);
                    sroll.add(st.getStudentrollno());
                    keylist.add(st.getId());
                    listView.setAdapter(new VerifyStudentCustomAdapter(sroll,getActivity(),keylist));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        //listviewss.setAdapter(new CustomPendingAdapter(sslist,jobtitle,getActivity(),cnamelist,studentlist));

        return view;
    }
}

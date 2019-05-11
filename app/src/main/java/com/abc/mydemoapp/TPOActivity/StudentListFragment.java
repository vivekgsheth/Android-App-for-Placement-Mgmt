package com.abc.mydemoapp.TPOActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.abc.mydemoapp.R;
import com.abc.mydemoapp.StudentsActivity.Student;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentListFragment extends Fragment {

    private FirebaseAuth mAuth;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String userID;
    ListView listviewstudent;
    Spinner branchspinner,yearspinner;
    TextView Filter,CPI,Year,Selected;
    CheckBox checkBox;
    DatabaseReference databasestudent;
    EditText cpitxt;
    Button btnsearch;
    Boolean chck;
    public ArrayList<String> studentroll = new ArrayList<String>();
    public  ArrayList<String> keyList = new ArrayList<>();
    public ArrayList<String> yearlist = new ArrayList<>();
    //public  ArrayList<String>

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_studentlist, container, false);

        listviewstudent = (ListView) view.findViewById(R.id.listviewstudent);
        branchspinner = (Spinner)view.findViewById(R.id.branchspinner);
        yearspinner = (Spinner)view.findViewById(R.id.yearspinner);
        Filter  = (TextView)view.findViewById(R.id.Filter);
        CPI  = (TextView)view.findViewById(R.id.CPI);
        Year  = (TextView)view.findViewById(R.id.Year);
        checkBox = (CheckBox)view.findViewById(R.id.checkBox);
        cpitxt = (EditText)view.findViewById(R.id.cpitxt);
        btnsearch = (Button)view.findViewById(R.id.btnsearch);



        ArrayAdapter<String> adp = new ArrayAdapter<>(this.getContext(),android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.branchname));
        adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        branchspinner.setAdapter(adp);

        ArrayAdapter<String> adp1 = new ArrayAdapter<>(this.getContext(),android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.year));

        adp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearspinner.setAdapter(adp1);

        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databasestudent = firebaseDatabase.getReference("Student");



        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

            }
        };

        btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                chck = checkBox.isChecked();


                databasestudent.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        studentroll.clear();
                        keyList.clear();
                        showData(dataSnapshot);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });







        return view;
    }




    private void showData(DataSnapshot dataSnapshot) {

        for (DataSnapshot ds : dataSnapshot.getChildren()) {
            //String key = databasestudent.push().getKey();
            //Log.w(key,"New key...");
            Student student = ds.getValue(Student.class);
            String key = student.getId();
            Log.w(key, "New key...");
            //student.setName(ds.child(key).getValue(Student.class).getName());//set the name
            //student.setBranchname(ds.child(key).getValue(Student.class).getBranchname());
            //student.setCpi(ds.child(key).getValue(Student.class).getCpi());
            //student.setEmailaddress(ds.child(key).getValue(Student.class).getEmailaddress());
            //student.setId(ds.child(key).getValue(Student.class).getId());
            //student.setPassword(ds.child(key).getValue(Student.class).getPassword());
            //student.setRole(ds.child(key).getValue(Student.class).getRole());

            Log.w(student.getName(), "Student name...");

            if(((Float.compare(student.getCpi(),Float.parseFloat(cpitxt.getText().toString()))) >= 0) && (student.getBranchname().equals(branchspinner.getSelectedItem().toString())) && (student.getYear() == Integer.parseInt(yearspinner.getSelectedItem().toString()) && (chck.compareTo(student.isSelected())) == 0) ) {
                //Do this for roll number also.
                keyList.add(student.getId());
                Log.w(student.getEmailaddress().toString(),"Student");

                studentroll.add(student.getRollno());
                listviewstudent.setAdapter(new ListViewAdapter(studentroll, keyList, this.getActivity(), key, student.getEmailaddress(), student.getPassword()));
            }
        }
    }
}
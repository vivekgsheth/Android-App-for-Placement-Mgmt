package com.abc.mydemoapp.TPOActivity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.abc.mydemoapp.R;
import com.abc.mydemoapp.StudentsActivity.Student;
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

    Student student;
    public List<String> studentname = new ArrayList<>();
    public List<String> allelements = new ArrayList<>();
    public Map<String, List<String>> StudentDetails = new HashMap<>();
    DatabaseReference databasestudent = FirebaseDatabase.getInstance().getReference("Student");

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ExpandableListAdapter listAdapter;
        View view = inflater.inflate(R.layout.fragment_studentlist, container, false);
        ExpandableListView listView = (ExpandableListView) view.findViewById(R.id.listview);

        databasestudent.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren())
                {
                    if(dataSnapshot.exists())
                    {
                        student = snapshot.getValue(Student.class);
                        studentname.add(student.getName());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        if(studentname.size()>0) {
            intializeHashmap();
        }
        listAdapter = new MyExpandableListAdapter(getActivity(), studentname, StudentDetails);
        listView.setAdapter(listAdapter);
        return view;
    }


    public void intializeHashmap()
    {
        databasestudent.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (int i = 0; i < studentname.size(); i++) {
                    if (dataSnapshot.exists()) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            float cpi;
                            student = snapshot.getValue(Student.class);
                            allelements.add(student.getName().toString());
                            allelements.add(student.getBranchname().toString());
                            cpi = student.getCpi();
                            allelements.add(Float.toString(cpi));
                            allelements.add(student.getEmailaddress().toString());
                            allelements.add(student.getPassword().toString());
                            StudentDetails.put(studentname.get(i), allelements);
                        }
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
}
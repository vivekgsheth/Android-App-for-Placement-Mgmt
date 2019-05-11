package com.abc.mydemoapp.TPOActivity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.abc.mydemoapp.CompanyActivity.Company;
import com.abc.mydemoapp.R;
import com.abc.mydemoapp.StudentsActivity.Student;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SendStudentTANFragment extends Fragment {

    private DatabaseReference dbcompanytan;
    private ArrayList<String> companyname = new ArrayList<String>();
    private ArrayList<String> keylist = new ArrayList<String>();
    private ListView listView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_studenttan, container, false);
        //RelativeLayout rl = (RelativeLayout) inflater.inflate(R.layout.fragment_profile,container,false);

        dbcompanytan = FirebaseDatabase.getInstance().getReference("Tan");
        listView = (ListView)view.findViewById(R.id.listView);

        dbcompanytan.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren())
                {
                    Tan ctan = ds.getValue(Tan.class);
                    companyname.add(ctan.getCompanyname());
                    keylist.add(ctan.getId());

                    listView.setAdapter(new CustomVerifyCompanyAdapter(companyname,getActivity(),keylist));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




//        dbcompanytan = FirebaseDatabase.getInstance().getReference("Tan");
//        listView = view.findViewById(R.id.listView);
//        dbcompanytan.addValueEventListener(new ValueEventListener() {
//
//
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                String id1 = dbcompanytan.push().getKey();
//                Tan student = new Tan("abc","def","gfrtd","123456",id1,"ce","Company");
//                //String id,String emailaddress, String branchname, String password, String name, float cpi,String role,String rollno,int year
//
//                DatabaseReference dbstudenttan1 = FirebaseDatabase.getInstance().getReference("Tan").child(id1);
//                dbstudenttan1.removeValue();
//                dbcompanytan.child(id1).setValue(student);
//
//                for(DataSnapshot ds : dataSnapshot.getChildren())
//                {
//                    Tan ctan = ds.getValue(Tan.class);
//                    companyname.add(ctan.getCompanyname());
//                    keylist.add(ctan.getId());
//                    listView.setAdapter(new CustomVerifyCompanyAdapter (companyname,getActivity(),keylist));
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

        return view;
    }
}

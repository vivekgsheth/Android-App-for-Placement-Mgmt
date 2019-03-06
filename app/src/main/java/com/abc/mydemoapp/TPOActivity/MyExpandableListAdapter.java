package com.abc.mydemoapp.TPOActivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.abc.mydemoapp.R;

import java.util.List;
import java.util.Map;

public class MyExpandableListAdapter extends BaseExpandableListAdapter
{

    Context context;
    List<String> studentname;
    Map<String,List<String>> StudentDetails;

    public MyExpandableListAdapter(Context context,List<String> studentname,Map<String,List<String>> StudentDetails)
    {
        this.context = context;
        this.studentname = studentname;
        this.StudentDetails = StudentDetails;
    }




    @Override
    public int getGroupCount() {
        return studentname.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return StudentDetails.get(studentname.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return studentname.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return StudentDetails.get(studentname.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    //This is the main method.
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        String sname = (String)getGroup(groupPosition);

        if(convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_parent,null);
        }

        TextView txtparent = (TextView)convertView.findViewById(R.id.txtParent);
        Button btndelete = (Button)convertView.findViewById(R.id.btndelete);

        txtparent.setText(sname);

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        return convertView;
    }
    //This is the other main method.
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        String sdetails = (String)getChild(groupPosition,childPosition);

        if(convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_child,null);
        }

        TextView txtChild = (TextView)convertView.findViewById(R.id.txtChild);
        Button btnupdate = (Button)convertView.findViewById(R.id.btnupdate);

        txtChild.setText(sdetails);

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}

package com.abc.mydemoapp.StudentsActivity;

public class Student {
    String id,emailaddress,branchname,password,idnumber,role;
    float cpi;
//This is Student class empty constructor
    public  Student()
    {

    }

    public Student(String id,String emailaddress, String branchname, String password, String idnumber, float cpi,String role) {
        this.emailaddress = emailaddress;
        this.branchname = branchname;
        this.password = password;
        this.idnumber = idnumber;
        this.cpi = cpi;
        this.role = role;
        this.id = id;
    }

    public String getEmailaddress() {
        return emailaddress;
    }

    public String getBranchname() {
        return branchname;
    }

    public String getId() {
        return id;
    }
    public String getPassword() {
        return password;
    }

    public String getIdnumber() {
        return idnumber;
    }
    public String getRole() {
        return role;
    }
    public float getCpi() {
        return cpi;
    }
}

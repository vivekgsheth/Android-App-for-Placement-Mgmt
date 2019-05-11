package com.abc.mydemoapp.CompanyActivity;

import java.util.ArrayList;

public class Company {
    public String id,companyname,address,emailaddress,password,role;
    public ArrayList<String> branch = new ArrayList<String>();

    public Company()
    {

    }

    public Company(String id,String companyname, String address,  String emailaddress, String password,String role,ArrayList<String> branch) {
        this.companyname = companyname;
        this.address = address;

        this.emailaddress = emailaddress;
        this.password = password;
        this.role=role;
        this.id = id;
        this.branch=branch;
    }

    public ArrayList<String> getBranch() {
        return branch;
    }

    public void setBranch(ArrayList<String> branch) {
        this.branch = branch;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public void setEmailaddress(String emailaddress) {
        this.emailaddress = emailaddress;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public String getCompanyname() {
        return companyname;
    }

    public String getAddress() {
        return address;
    }



    public String getEmailaddress() {
        return emailaddress;
    }

    public String getEmailaddresscompany() {
        return emailaddress;
    }

    public String getPassword() {
        return password;
    }
}

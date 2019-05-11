package com.abc.mydemoapp.TPOActivity;

import java.util.ArrayList;

public class RejectCompany {
    String id,companyname,companyemailadress,companyaddress,pass,role;
    //String branch;
    //ArrayList<String> branch;
    Boolean reject;

    public RejectCompany()
    {

    }


    public RejectCompany(String id, String companyname, String companyemailadress, String companyaddress, String pass, String role,Boolean reject) {
        this.id = id;
        this.companyname = companyname;
        this.companyemailadress = companyemailadress;
        this.companyaddress = companyaddress;
        this.pass = pass;
        this.role = role;
      //  this.branch = branch;
        this.reject = reject;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getCompanyemailadress() {
        return companyemailadress;
    }

    public void setCompanyemailadress(String companyemailadress) {
        this.companyemailadress = companyemailadress;
    }

    public String getCompanyaddress() {
        return companyaddress;
    }

    public void setCompanyaddress(String companyaddress) {
        this.companyaddress = companyaddress;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

//    public ArrayList<String> getBranch() {
//        return branch;
//    }
//
//    public void setBranch(ArrayList<String> branch) {
//        this.branch = branch;
//    }

    public Boolean getReject() {
        return reject;
    }

    public void setReject(Boolean reject) {
        this.reject = reject;
    }
}

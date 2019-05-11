package com.abc.mydemoapp.TPOActivity;

import java.util.ArrayList;

public class Tan {
    String id,companyname,companyemailadress,companyaddress,pass,role;
    //String branch;
    ArrayList<String> branch;
    public Tan()
    {

    }

    public Tan(String companyname, String companyemailadress, String companyaddress, String pass,String id,ArrayList<String> branch,String role) {
        this.companyname = companyname;

        this.companyemailadress = companyemailadress;
        this.companyaddress = companyaddress;
        this.pass = pass;
        this.id=id;
        this.branch=branch;
        this.role=role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public ArrayList<String>  getBranch() {
        return branch;
    }

    public void setBranch(ArrayList<String> branch) {
        this.branch = branch;
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
}

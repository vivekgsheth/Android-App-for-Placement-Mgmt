package com.abc.mydemoapp.CompanyActivity;

public class Company {
    public String id,companyname,address,tan,emailaddress,password,role;

    public Company()
    {

    }

    public Company(String id,String companyname, String address, String tan, String emailaddress, String password,String role) {
        this.companyname = companyname;
        this.address = address;
        this.tan = tan;
        this.emailaddress = emailaddress;
        this.password = password;
        this.role=role;
        this.id = id;
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

    public String getTan() {
        return tan;
    }

    public String getEmailaddresscompany() {
        return emailaddress;
    }

    public String getPassword() {
        return password;
    }
}

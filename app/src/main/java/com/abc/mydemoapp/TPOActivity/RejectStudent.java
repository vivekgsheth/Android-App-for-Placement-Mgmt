package com.abc.mydemoapp.TPOActivity;

public class RejectStudent {
    String studentrollno,studentemail,studentname,branchname,pass,studenttanid;
    //float studentcpi;
    String role;
    Boolean reject;
    public RejectStudent()
    {

    }
    public RejectStudent(String studentrollno, String studentemail, String studentname, String branchname, String pass, String studenttanid, String role, Boolean reject) {
        this.studentrollno = studentrollno;
        this.studentemail = studentemail;
        this.studentname = studentname;
        this.branchname = branchname;
        this.pass = pass;
        this.studenttanid = studenttanid;
        this.role = role;
        this.reject = reject;
    }

    public String getStudentrollno() {
        return studentrollno;
    }

    public void setStudentrollno(String studentrollno) {
        this.studentrollno = studentrollno;
    }

    public String getStudentemail() {
        return studentemail;
    }

    public void setStudentemail(String studentemail) {
        this.studentemail = studentemail;
    }

    public String getStudentname() {
        return studentname;
    }

    public void setStudentname(String studentname) {
        this.studentname = studentname;
    }

    public String getBranchname() {
        return branchname;
    }

    public void setBranchname(String branchname) {
        this.branchname = branchname;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getStudenttanid() {
        return studenttanid;
    }

    public void setStudenttanid(String studenttanid) {
        this.studenttanid = studenttanid;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Boolean getReject() {
        return reject;
    }

    public void setReject(Boolean reject) {
        this.reject = reject;
    }
}

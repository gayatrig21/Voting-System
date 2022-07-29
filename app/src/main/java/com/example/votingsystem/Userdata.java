package com.example.votingsystem;

public class Userdata {
   public String name,mobno,dob,add,uid,pass,canset;

    public Userdata() {

    }

    public Userdata(String name, String mobno, String dob, String add, String uid, String pass, String canset) {
        this.name = name;
        this.mobno = mobno;
        this.dob = dob;
        this.add = add;
        this.uid = uid;
        this.pass = pass;
        this.canset=canset;
    }

    public String getCanset() {
        return canset;
    }

    public void setCanset(String canset) {
        this.canset = canset;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobno() {
        return mobno;
    }

    public void setMobno(String mobno) {
        this.mobno = mobno;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAdd() {
        return add;
    }

    public void setAdd(String add) {
        this.add = add;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}

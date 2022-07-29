package com.example.votingsystem;

public class Candidatedataholder {
    String cname,cadd,cmob,cdesc,grpid;

    public Candidatedataholder() {
    }

    public Candidatedataholder(String cname, String cadd, String cmob, String cdesc, String grpid) {
        this.cname = cname;
        this.cadd = cadd;
        this.cmob = cmob;
        this.cdesc = cdesc;
        this.grpid = grpid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCadd() {
        return cadd;
    }

    public void setCadd(String cadd) {
        this.cadd = cadd;
    }

    public String getCmob() {
        return cmob;
    }

    public void setCmob(String cmob) {
        this.cmob = cmob;
    }

    public String getCdesc() {
        return cdesc;
    }

    public void setCdesc(String cdesc) {
        this.cdesc = cdesc;
    }

    public String getGrpid() {
        return grpid;
    }

    public void setGrpid(String grpid) {
        this.grpid = grpid;
    }
}

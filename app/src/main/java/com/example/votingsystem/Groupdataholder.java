package com.example.votingsystem;

public class Groupdataholder {
    String name,desc;

    public Groupdataholder() {
    }

    public Groupdataholder(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}

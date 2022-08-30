package com.student.manage;

public class Student {
    private int sid;
    private String sname;
    private String sphone;
    private String scity;

    public Student(int sid, String sname, String sphone, String scity) {
        this.sid = sid;
        this.sname = sname;
        this.sphone = sphone;
        this.scity = scity;
    }

    public Student(String sname, String sphone, String scity) {
        this.sname = sname;
        this.sphone = sphone;
        this.scity = scity;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSphone() {
        return sphone;
    }

    public void setSphone(String sphone) {
        this.sphone = sphone;
    }

    public String getScity() {
        return scity;
    }

    public void setScity(String scity) {
        this.scity = scity;
    }

    @Override
    public String toString() {
        return "Student{" +
                "sid=" + sid +
                ", sname='" + sname + '\'' +
                ", sphone='" + sphone + '\'' +
                ", scity='" + scity + '\'' +
                '}';
    }
}

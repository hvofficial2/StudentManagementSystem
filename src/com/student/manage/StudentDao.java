package com.student.manage;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

public class StudentDao {

    private static boolean searchStudent(String name, String Phone) throws IOException,SQLException{
        Connection con = ConnectionProvider.getCon();
        String q = "select COUNT(*) from Student where sname=? and sphone=?";
        PreparedStatement pstmt = con.prepareStatement(q);
        pstmt.setString(1,name);
        pstmt.setString(2,Phone);
        ResultSet set = pstmt.executeQuery();
        set.next();
        if(set.getInt(1)==1)
            return true;
        return false;
    }

//    private static boolean searchStudentByIdName(int id, String name) throws IOException,SQLException{
//        Connection con = ConnectionProvider.getCon();
//        String q = "select COUNT(*) from Student where sid=(?) and sname=(?)";
//        PreparedStatement pstmt = con.prepareStatement(q);
//        pstmt.setInt(1,id);
//        pstmt.setString(2,name);
//        ResultSet set = pstmt.executeQuery();
//        set.next();
//        if(set.getInt(1)==1)
//            return true;
//        return false;
//    }

    private static boolean searchStudentById(int id) throws IOException,SQLException{
        Connection con = ConnectionProvider.getCon();
        String q = "select COUNT(*) from Student where sid=(?)";
        PreparedStatement pstmt = con.prepareStatement(q);
        pstmt.setInt(1,id);
        ResultSet set = pstmt.executeQuery();
        set.next();
        if(set.getInt(1)==1)
            return true;
        return false;
    }

    public static int addStudent(Student s) throws IOException,SQLException{
        Connection con = ConnectionProvider.getCon();
        int sid=0;
        if(!searchStudent(s.getSname(),s.getSphone())){
            String q = "insert into Student (sname,sphone,scity) values(?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(q);
            pstmt.setString(1,s.getSname());
            pstmt.setString(2,s.getSphone());
            pstmt.setString(3,s.getScity());
            pstmt.executeUpdate();

            ResultSet set = con.createStatement().executeQuery("select sid from Student order by sid desc limit 1");
            set.next();
            sid = set.getInt(1);
        }
            return sid;
    }

    public static void displayAll() throws SQLException, IOException {

//        System.out.println(System.getProperty("user.dir"));

        Connection con = ConnectionProvider.getCon();
        Statement stmt = con.createStatement();
        ResultSet set = stmt.executeQuery("select * from student");
        while(set.next()){
            System.out.println("#--------------------------------------------------------#");
            System.out.println("Student ID          :"+set.getInt("sid"));
            System.out.println("Student Name        :"+set.getString("sname"));
            System.out.println("Student PhoneNo.    :"+set.getString("sphone"));
            System.out.println("Student City        :"+set.getString("scity"));
        }
        set.close();
    }

    public static boolean updateStudent(int id) throws IOException, SQLException{
        if(searchStudentById(id)){
            Connection con = ConnectionProvider.getCon();
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter Student's New name: ");
            String newName = br.readLine();
            System.out.print("Enter Student's New PhoneNo.: ");
            String newPhone = br.readLine();
            System.out.print("Enter Student's New City: ");
            String newCity = br.readLine();
            String q2 = "Update Student set sname = (?),sphone=(?),scity=(?) where sid = (?)";
            PreparedStatement pstmt2 = con.prepareStatement(q2);
            pstmt2.setString(1,newName);
            pstmt2.setString(2,newPhone);
            pstmt2.setString(3,newCity);
            pstmt2.setInt(4,id);
            pstmt2.executeUpdate();
            return true;
        }
            return false;
    }

    public static boolean display(int id) throws IOException,SQLException{
        if(searchStudentById(id)){
            Connection con = ConnectionProvider.getCon();
            String q = "select * from student where sid=?";
            PreparedStatement pstmt = con.prepareStatement(q);
            pstmt.setInt(1,id);
            ResultSet set = pstmt.executeQuery();
            set.next();
            System.out.println("#--------------------------------------------------------#");
            System.out.println("Student ID          :"+set.getInt("sid"));
            System.out.println("Student Name        :"+set.getString("sname"));
            System.out.println("Student PhoneNo.    :"+set.getString("sphone"));
            System.out.println("Student City        :"+set.getString("scity"));
            set.close();
            return true;
        }
        return false;
    }

    public static boolean delete(int id) throws IOException,SQLException{
        if(searchStudentById(id)){
            Connection con = ConnectionProvider.getCon();
            String q = "delete from student where sid=?";
            PreparedStatement pstmt = con.prepareStatement(q);
            pstmt.setInt(1,id);
            pstmt.execute();
            return true;
        }
        return false;
    }
}

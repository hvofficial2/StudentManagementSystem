package com.student.manage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class Start {
    public static void main(String...args) throws IOException, SQLException {
        System.out.println("Welcome to our Student management System");
        l1:{
        while(true){
            System.out.println();
            System.out.println("PRESS 1 to ADD a new Student");
            System.out.println("PRESS 2 to Delete a Student");
            System.out.println("PRESS 3 to DISPLAY a Student");
            System.out.println("PRESS 4 to UPDATE Student's info");
            System.out.println("PRESS 5 to DISPLAY details all Students");
            System.out.println("PRESS 6 to Exit");

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println();
            System.out.print("Please enter your choice: ");
            int choice = Integer.parseInt(br.readLine());

            switch(choice){
                case 1:{
                        System.out.print("Enter the student's name: ");
                        String name = br.readLine();
                        System.out.print("Enter the student's phoneNo.: ");
                        String phone = br.readLine();
                        System.out.print("Enter the student's city: ");
                        String city = br.readLine();
                        Student s = new Student(name,phone,city);
                        int id = StudentDao.addStudent(s);
                        if(id>0)
                            System.out.println("Student added with id: "+id);
                        else
                            System.out.println("Student already present in Database!");
                        System.out.println();
                }
                        break;
                case 2:{
                    System.out.print("Enter Student's id to be deleted: ");
                    int id = Integer.parseInt(br.readLine());
                    if(StudentDao.delete(id))
                        System.out.println("#----------------Student Details Deleted-----------------#");
                    else
                        System.out.println("Student Not Found!, please try again.");
                }
                    break;
                case 3:{
                    System.out.print("Enter Student's id: ");
                    int id = Integer.parseInt(br.readLine());
                    if(StudentDao.display(id))
                        System.out.println("#----------------Student Details End-----------------#");
                    else
                        System.out.println("Student Not Found!, please try again.");
                }
                    break;
                case 4: {
                    System.out.print("Enter Student's id: ");
                    int id = Integer.parseInt(br.readLine());
                    if(StudentDao.updateStudent(id))
                        System.out.println("Student Details Updated!");
                    else
                        System.out.println("Student Not Found!, please try again.");
                }
                break;
                case 5: StudentDao.displayAll();
                    break;
                case 6: break l1;
                default:
                    System.out.println("Incorrect Input! Please try again.");
            }
        }
        }
        ConnectionProvider.cleanup();
        System.out.println("Thank you for using Management System, Hope to see you again.");
    }
}

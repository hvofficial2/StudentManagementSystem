package com.student.manage;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionProvider {
    private static Connection con;
    public static Connection getCon() throws IOException, SQLException {
        if(con==null){
            Properties p = new Properties();
            FileInputStream fis = new FileInputStream("src/studentdb.PROPERTIES");
            p.load(fis);
            con = DriverManager.getConnection(p.getProperty("URL"),p.getProperty("USERNAME"),p.getProperty("PASSWORD"));
            fis.close();
        }
        return con;
    }

    public static void cleanup() throws SQLException {
        if(con!=null)
            con.close();
    }
}

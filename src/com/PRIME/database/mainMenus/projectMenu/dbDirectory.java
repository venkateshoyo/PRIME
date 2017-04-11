package com.PRIME.database.mainMenus.projectMenu;

import com.PRIME.database.utils.DBUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class dbDirectory {

    public static String databaseDirectory(){
        Connection con;
        Statement smt ;
        ResultSet rs ;

        String query = "SELECT link from defaults WHERE `parameter name`='database directory'";

        try {
            con= DBUtils.getConnection();
            smt = con.createStatement();
            rs = smt.executeQuery(query);

            rs.next();
            return rs.getString("link");

        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }
}
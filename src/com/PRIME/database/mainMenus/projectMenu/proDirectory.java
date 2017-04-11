package com.PRIME.database.mainMenus.projectMenu;

import com.PRIME.database.utils.DBUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class proDirectory {

    public static String proDirectory(){

        Connection con ;
        Statement smt ;
        ResultSet rs ;

        String query = "SELECT link from defaults WHERE `parameter name`='project directory'";

        try {
            con = DBUtils.getConnection();
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
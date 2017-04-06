package com.PNRPM.database.menubar;

import com.PNRPM.database.utils.DBUtils;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class recentprojects {

    public static ResultSet recentprojects() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        String query = "SELECT * from projects";
        try{
            con = DBUtils.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            return rs;
        }
    }
}

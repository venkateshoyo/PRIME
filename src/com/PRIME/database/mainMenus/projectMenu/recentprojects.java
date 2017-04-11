package com.PRIME.database.mainMenus.projectMenu;

import com.PRIME.database.utils.DBUtils;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class recentprojects {

    public static ResultSet recentprojects() {
        Connection con;
        Statement stmt;
        ResultSet rs = null;

        String query = "SELECT * from projects order by time desc";
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

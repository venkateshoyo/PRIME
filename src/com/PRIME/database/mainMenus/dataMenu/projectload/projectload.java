package com.PRIME.database.mainMenus.dataMenu.projectload;

import com.PRIME.database.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class projectload {

    public static void projectload(String name){

        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String TABNAME = "`prime`.`projects`";
        String query = DBUtils.prepareInsertQuery(TABNAME, "`Project Name`, `time`", "?, ?");
        try {
            con = DBUtils.getConnection();
            stmt = con.prepareStatement(query);
            stmt.setString(1,name);
            stmt.setString(2,new Timestamp(System.currentTimeMillis())+"");
            stmt.executeUpdate();
        } catch (SQLException sql) {
            sql.printStackTrace();
        } finally {
            DBUtils.closeAll(rs, stmt, con);
        }
    }
}
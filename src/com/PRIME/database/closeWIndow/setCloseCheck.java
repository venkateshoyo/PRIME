package com.PRIME.database.closeWIndow;

import com.PRIME.database.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class setCloseCheck {

    public static void setCloseCheck(){
        Connection conn = null;
        PreparedStatement stmtt = null;

        try {
            conn = DBUtils.getConnection();
            stmtt = conn.prepareStatement("UPDATE `prime`.`defaults` SET `link`='1' WHERE `parameter name`='close check';");
            stmtt.executeUpdate();
        } catch (SQLException sql) {
            sql.printStackTrace();
        } finally {
            DBUtils.closeStatement(stmtt);
            DBUtils.closeConnection(conn);
        }
    }

}
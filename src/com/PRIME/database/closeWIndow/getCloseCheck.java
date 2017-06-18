package com.PRIME.database.closeWIndow;

import com.PRIME.database.utils.DBUtils;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class getCloseCheck {

    public static int getCloseCheck(){
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            con = DBUtils.getConnection();
            stmt = con.prepareStatement("SELECT link from defaults WHERE `parameter name`='close check'");
            rs = stmt.executeQuery();
            rs.next();
            if(rs.getString("link").equals("1"))
                return 1;
            else
                return 0;
        } catch (SQLException sql) {
            sql.printStackTrace();
        } finally {
            DBUtils.closeAll(rs, stmt, con);
        }
        return 0;
    }
}

package com.PRIME.database.mainMenus.dataMenu.wellload;

import com.PRIME.database.utils.DBUtils;
import javafx.scene.control.ComboBox;

import java.sql.*;

public class fetchWellDir {

        public static ComboBox<String> fetchWellDir(){
            final ComboBox<String> combo = new ComboBox<String>();

            Connection con = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;

            try {
                con = DBUtils.getConnection();
                stmt = con.prepareStatement("SELECT link from defaults WHERE `parameter name`='well directory'");
                rs = stmt.executeQuery();
                rs.next();
                String links[] = rs.getString("link").split("\\?");
                int length = links.length;
                int i=0;
                while(i<length && i<8){
                    combo.getItems().add(links[i]);
                    ++i;
                }
            } catch (SQLException sql) {
                sql.printStackTrace();
            } finally {
                DBUtils.closeAll(rs, stmt, con);
            }

            return combo;
        }
}
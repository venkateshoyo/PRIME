package com.PRIME.main.operations.mainMenus.projectMenu;

import com.PRIME.database.menubar.recentprojects;
import com.PRIME.database.utils.DBUtils;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

import java.sql.ResultSet;
import java.sql.SQLException;

public class recentProject {

    public static Menu recentProject(){
        Menu menus = new Menu("_Recent Project");

        ResultSet rs = null;
        try{
            rs = recentprojects.recentprojects();
            while (rs.next()){
                String title = rs.getString("Project Name");
                MenuItem menu = new MenuItem(title);
                menus.getItems().add(menu);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            DBUtils.closeResultSet(rs);
        }
        return menus;
    }
}
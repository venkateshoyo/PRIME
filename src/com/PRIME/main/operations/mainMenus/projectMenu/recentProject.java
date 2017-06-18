package com.PRIME.main.operations.mainMenus.projectMenu;

import com.PRIME.database.mainMenus.projectMenu.recentprojects;
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
            //Collecting recent project results and stored in rs.
            rs = recentprojects.recentprojects();

            while (rs.next()){

                //Adding all Recent projects to Menu as MenuItems.
                //Remember project fetching is already being done in decreasing order

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

        //returning recent menus list fetched from DB
        return menus;
    }
}
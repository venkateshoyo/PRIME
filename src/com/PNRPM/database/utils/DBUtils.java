package com.PNRPM.database.utils;

import com.PNRPM.utils.property;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBUtils {
    private static Properties properties = null;
    private static final String DRIVER = property.JDBC_DRIVER;

    private static final String BACKUP_URL = property.DB_URL;
    private static final String BACKUP_DB = property.DB_DEFAULT_DB;
    private static final String BACKUP_USERNAME = property.DB_USERNAME;
    private static final String BACKUP_PASSWORD = property.DB_PASSWORD;

    static{
        //setCredentials();
    }
    public static Connection getConnection() throws SQLException{

        if(properties != null){
            try{
                Class.forName(DRIVER);
                return DriverManager.getConnection(properties.getProperty(property.DB_URL) + properties.getProperty(property.DB_DEFAULT_DB),
                        properties.getProperty(property.DB_USERNAME), properties.getProperty(property.DB_PASSWORD,""));
                //return DriverManager.getConnection(URL + DEFAULT_DB,
                //	USERNAME, PASSWORD);
            }catch (ClassNotFoundException e) {
                //
                throw new SQLException(e);
            }
        }else{
            throw new SQLException("Unable to load properties");
        }
    }

    public static Connection getConnection(String dataBase) throws SQLException{
        if(properties != null){
            try{
                Class.forName(DRIVER);
                return DriverManager.getConnection(properties.getProperty(property.DB_URL) + dataBase,
                        properties.getProperty(property.DB_USERNAME), properties.getProperty(property.DB_PASSWORD));
            }catch (ClassNotFoundException e) {
                //
                throw new SQLException(e);
            }
        }else{
            throw new SQLException("Unable to load properties");
        }
    }

    public static Connection getBackupConnection() throws SQLException{
        try{
            Class.forName(DRIVER);
            return DriverManager.getConnection(BACKUP_URL + BACKUP_DB,
                    BACKUP_USERNAME, BACKUP_PASSWORD);
        }catch (ClassNotFoundException e) {
           //
            throw new SQLException(e);
        }
    }

    public static void closeConnection(Connection con){
        try{
            if(con != null && !con.isClosed()){
                con.close();
            }
        }catch(Exception e){
            //
        }
    }

    public static void closeStatement(Statement stmt){
        try{
            if(stmt != null && !stmt.isClosed()){
                stmt.close();
            }
        }catch(Exception e){
            //
        }
    }

    public static void closeResultSet(ResultSet rs){
        try{
            if(rs != null && !rs.isClosed()){
                rs.close();
            }
        }catch(Exception e){
            //
        }
    }

    public static void closeAll(ResultSet rs, PreparedStatement stmt, Connection con){
        DBUtils.closeResultSet(rs);
        DBUtils.closeStatement(stmt);
        DBUtils.closeConnection(con);
    }

    public static int insertQuery(String query) throws SQLException{
        if(query != null && query.isEmpty()){
            Connection con = null;
            Statement stmt = null;
            try{
                con = getConnection();
                stmt = con.createStatement();
                return stmt.executeUpdate(query);
            }finally{
                closeConnection(con);
                closeStatement(stmt);
            }
        }
        return -1;
    }

    public static int insertQuery(Connection con, String query) throws SQLException{
        if(query != null && query.isEmpty()){
            Statement stmt = null;
            try{
                stmt = con.createStatement();
                return stmt.executeUpdate(query);
            }finally{
                closeStatement(stmt);
            }
        }
        return -1;
    }

    public static int updateQuery(String query) throws SQLException{
        if(query != null && query.isEmpty()){
            Connection con = null;
            Statement stmt = null;
            try{
                con = getConnection();
                stmt = con.createStatement();
                return stmt.executeUpdate(query);
            }finally{
                closeConnection(con);
                closeStatement(stmt);
            }
        }
        return -1;
    }

    public static String prepareSelectQuery(String columns,String tableName, String whereClause){
        if(columns.isEmpty())
            columns = "*";
        if(whereClause.isEmpty())
            whereClause = "";
        else
            whereClause = " WHERE " + whereClause;
        return "SELECT " + columns + " FROM " + tableName + whereClause;
    }

    public static String prepareSelectQuery(String columns,String tableName, String whereClause, String otherClause){
        if(columns.isEmpty())
            columns = "*";
        if(whereClause.isEmpty())
            whereClause = "";
        else
            whereClause = " WHERE " + whereClause;
        return "SELECT " + columns + " FROM " + tableName + whereClause + " " + otherClause;
    }

    public static String prepareInsertQuery(String tableName, String columns, String values){
        if(columns.isEmpty())
            columns = " ";
        else
            columns = " (" + columns + ") ";
        if(values.isEmpty())
            values = "";
        else
            values = " VALUES (" + values + ")";
        return "INSERT INTO " + tableName + columns + values;
    }

    public static String prepareUpdateQuery(String tableName, String setCondition, String whereClause){
        if(setCondition.isEmpty())
            setCondition = "";
        if(whereClause.isEmpty())
            whereClause = "";
        else
            whereClause = " WHERE " + whereClause;
        return "UPDATE " + tableName + " SET " + setCondition + whereClause;
    }

    public static void performAction(String action){
        Connection con = null;
        try{
            con = getConnection();
            Statement stmt = con.createStatement();
            stmt.execute(action);
        }catch(Exception e){}finally{
            closeConnection(con);
        }
    }
}
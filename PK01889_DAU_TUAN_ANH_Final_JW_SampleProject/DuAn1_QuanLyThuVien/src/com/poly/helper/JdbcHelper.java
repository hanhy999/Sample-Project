/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poly.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author PC
 */
public class JdbcHelper {

    private static String Driver ="com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static String dburl = "jdbc:sqlserver://localhost:1433;databaseName=SampleProject1";
    private static String username = "sa";
    private static String password = "123";

    static {
        try {
            Class.forName(Driver);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static PreparedStatement prepareStatement(String sql, Object... args) throws SQLException {
        Connection con = DriverManager.getConnection(dburl, username, password);
        PreparedStatement pst = null;
        if (sql.trim().startsWith("{")) {
            pst = con.prepareCall(sql);
        } else {
            pst = con.prepareStatement(sql);
        }
        for (int i = 0; i < args.length; i++) {
            pst.setObject(i + 1, args[i]);
        }
        return pst;
    }

    public static void executeUpdate(String sql, Object... args) {

        try {
            PreparedStatement stm = prepareStatement(sql, args);
            try {
                stm.executeUpdate();
            } finally {
                stm.getConnection().close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public static ResultSet executeQuery(String sql, Object... args) {
        try {
            PreparedStatement stm = prepareStatement(sql, args);
            return stm.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

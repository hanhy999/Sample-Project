/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poly.dao;

import com.poly.helper.JdbcHelper;
import com.poly.model.NguoiDung;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Q5
 */
public class NguoiDungDAO {

    public void insert(NguoiDung model) {
        String sql = "INSERT INTO NGUOIDUNG VALUES(?,?,?,?)";
        JdbcHelper.executeUpdate(sql,
                model.getMaND(),
                model.getUser(),
                model.getPassword(),
                model.isVaiTro());
    }

    public void update(NguoiDung model) {
        String sql = "UPDATE NGUOIDUNG SET TENND=?,MATKHAU=?,VAITRO=? WHERE MAND=?";
        JdbcHelper.executeUpdate(sql,
                model.getUser(),
                model.getPassword(),
                model.isVaiTro(),
                model.getMaND());
    }

    public void delete(String MaND) {
        String sql = "DELETE FROM NGUOIDUNG WHERE MAND=?";
        JdbcHelper.executeUpdate(sql, MaND);
    }

    public List<NguoiDung> select() {
        String sql = "SELECT * FROM NGUOIDUNG";
        return select(sql);
    }

    public NguoiDung findById(String MaND) {
        String sql = "SELECT * FROM NGUOIDUNG WHERE MaND=?";
        List<NguoiDung> list = select(sql, MaND);
        return list.size() > 0 ? list.get(0) : null;
    }

    private List<NguoiDung> select(String sql, Object... args) {
        List<NguoiDung> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.executeQuery(sql, args);
                while (rs.next()) {
                    NguoiDung model = readFromResultSet(rs);
                    list.add(model);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }

    private NguoiDung readFromResultSet(ResultSet rs) throws SQLException {
        NguoiDung model = new NguoiDung();
        model.setMaND(rs.getString("MaND"));
        model.setUser(rs.getString("TenND"));
        model.setPassword(rs.getString("MatKhau"));        
        model.setVaiTro(rs.getBoolean("VaiTro"));
        return model;
    }
}

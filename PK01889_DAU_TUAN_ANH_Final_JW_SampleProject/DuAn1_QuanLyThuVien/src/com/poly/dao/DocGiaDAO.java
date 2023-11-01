/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poly.dao;

import com.poly.helper.JdbcHelper;
import com.poly.model.DocGia;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Q5
 */
public class DocGiaDAO {
    public void insert(DocGia model){
        String sql="INSERT INTO DOCGIA VALUES(?,?,?,?,?)";
        JdbcHelper.executeUpdate(sql,
                model.getMaDG(),
                model.getTenDG(),
                model.getDiaChi(),
                model.isGioiTinh(),
                model.getDienThoai());
    }
     public void update(DocGia model){
        String sql="UPDATE DOCGIA SET TENDG=?,DIACHI=?,GIOITINH=?,DIENTHOAI=? WHERE MADG=?";
        JdbcHelper.executeUpdate(sql,               
                model.getTenDG(),
                model.getDiaChi(),
                model.isGioiTinh(),
                model.getDienThoai(),
                model.getMaDG());
    }
    public void delete(String MaDG){
        String sql="DELETE FROM DOCGIA WHERE MADG=?";
        JdbcHelper.executeUpdate(sql, MaDG);
    }
        public List<DocGia> select() {
        String sql = "SELECT * FROM DOCGIA";
        return select(sql);
    }

    public DocGia findById(String MaDG) {
        String sql = "SELECT * FROM DOCGIA WHERE MaDG=?";
        List<DocGia> list = select(sql, MaDG);
        return list.size() > 0 ? list.get(0) : null;
    }

    private List<DocGia> select(String sql, Object... args) {
        List<DocGia> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.executeQuery(sql, args);
                while (rs.next()) {
                    DocGia model = readFromResultSet(rs);
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

    private DocGia readFromResultSet(ResultSet rs) throws SQLException {
        DocGia model = new DocGia();
        model.setMaDG(rs.getString("MaDG"));
        model.setTenDG(rs.getString("TenDG"));
        model.setDiaChi(rs.getString("DiaChi"));
        model.setGioiTinh(rs.getBoolean("GioiTinh"));        
        model.setDienThoai(rs.getString("DienThoai"));
        return model;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poly.dao;

import com.poly.helper.JdbcHelper;
import com.poly.model.Sach;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Q5
 */
public class SachDAO {

    public void insert(Sach model) {
        String sql = "INSERT INTO SACH VALUES(?,?,?,?,?,?,?,?)";
        JdbcHelper.executeUpdate(sql,
                model.getMaSach(),
                model.getTenSach(),
                model.getTacGia(),
                model.getNhaXB(),
                model.getSoLuong(),
                model.getSoLuongCon(),
                model.getNgayNhap(),
                model.getGiaBia());
    }

    public void update(Sach model) {
        String sql = "UPDATE SACH SET TENSACH=?,TACGIA=?,NHAXB=?,SOLUONG=?,SOLUONGCON=?,NGAYNHAP=?,GIABIA=? WHERE MASACH=?";
        JdbcHelper.executeUpdate(sql,
                model.getTenSach(),
                model.getTacGia(),
                model.getNhaXB(),
                model.getSoLuong(),
                model.getSoLuongCon(),
                model.getNgayNhap(),
                model.getGiaBia(),
                model.getMaSach());
    }
    public void delete(String MaSach){
        String sql="DELETE FROM SACH WHERE MASACH=?";
        JdbcHelper.executeUpdate(sql,MaSach);
    }
    public List<Sach> select() {
        String sql = "SELECT * FROM Sach";
        return select(sql);
    }

    public Sach findById(String MaSach) {
        String sql = "SELECT * FROM Sach WHERE MaSach=?";
        List<Sach> list = select(sql, MaSach);
        return list.size() > 0 ? list.get(0) : null;
    }
    private List<Sach> select(String sql, Object... args) {
        List<Sach> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.executeQuery(sql, args);
                while (rs.next()) {
                    Sach model = readFromResultSet(rs);
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

    private Sach readFromResultSet(ResultSet rs) throws SQLException {
        Sach model = new Sach();
        model.setMaSach(rs.getString("MaSach"));
        model.setTenSach(rs.getString("TenSach"));
        model.setTacGia(rs.getString("TacGia"));        
        model.setNhaXB(rs.getString("NhaXB"));
        model.setSoLuong(rs.getInt("SoLuong"));
        model.setSoLuongCon(rs.getInt("SoLuongCon"));
        model.setNgayNhap(rs.getDate("NgayNhap"));
        model.setGiaBia(rs.getInt("GiaBia"));
        return model;
    }
}

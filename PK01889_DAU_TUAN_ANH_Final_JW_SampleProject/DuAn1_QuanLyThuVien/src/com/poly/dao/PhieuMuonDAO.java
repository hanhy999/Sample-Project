/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poly.dao;

import com.poly.helper.JdbcHelper;
import com.poly.model.PhieuMuon;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Q5
 */
public class PhieuMuonDAO {

    public void insert(PhieuMuon model) {
        String sql = "INSERT INTO PHIEUMUON VALUES(?,?,?,?,?,?)";
        JdbcHelper.executeUpdate(sql,
                model.getMaPM(),
                model.getMaDG(),
                model.getSoluongmuon(),
                model.getTienCoc(),
                model.getNgayMuon(),
                model.getNgayHenTra());
    }

    public void update(PhieuMuon model) {
        String sql = "UPDATE PHIEUMUON SET MADG=?,TIENCOC=?,NGAYMUON=?,NGAYHENTRA=? WHERE MAPM=?";
        JdbcHelper.executeUpdate(sql,
                model.getMaDG(),
                model.getTienCoc(),
                model.getNgayMuon(),
                model.getNgayHenTra(),
                model.getMaPM());
    }

    public void delete(String MaPM) {
        String sql = "DELETE FROM PHIEUMUON WHERE MAPM=?";
        JdbcHelper.executeUpdate(sql, MaPM);
    }

    public List<PhieuMuon> select() {
        String sql = "SELECT * FROM PHIEUMUON";
        return select(sql);
    }

    
    public PhieuMuon findById(String MaPM) {
        String sql = "SELECT * FROM PhieuMuon WHERE MaPM=?";
        List<PhieuMuon> list = select(sql, MaPM);
        return list.size() > 0 ? list.get(0) : null;
    }

    private List<PhieuMuon> select(String sql, Object... args) {
        List<PhieuMuon> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.executeQuery(sql, args);
                while (rs.next()) {
                    PhieuMuon model = readFromResultSet(rs);
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

    private PhieuMuon readFromResultSet(ResultSet rs) throws SQLException {
        PhieuMuon model = new PhieuMuon();
        model.setMaPM(rs.getString("MaPM"));
        model.setMaDG(rs.getString("MaDG"));
        model.setSoluongmuon(rs.getInt("SoLuongMuon"));
        model.setTienCoc(rs.getFloat("TienCoc"));
        model.setNgayMuon(rs.getDate("NgayMuon"));
        model.setNgayHenTra(rs.getDate("NgayHenTra"));
        return model;
    }
}

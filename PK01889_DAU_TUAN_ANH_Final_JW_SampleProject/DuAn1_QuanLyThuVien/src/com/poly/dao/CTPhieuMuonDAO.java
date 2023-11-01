/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poly.dao;
import com.poly.model.CTPhieuMuon;
import com.poly.helper.JdbcHelper;
import com.poly.model.PhieuMuon;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC
 */
public class CTPhieuMuonDAO {
     public void insert(CTPhieuMuon model){
        String sql="INSERT INTO CTPhieuMuon VALUES(?,?,?)";
        JdbcHelper.executeUpdate(sql,
                model.getMaPM(),
                model.getMaSach(),
                model.getNgayTra());
    }
     public void update(CTPhieuMuon model){
        String sql="UPDATE CTPhieuMuon SET NgayTra=? where MaPM=? and MaSach=?";
        JdbcHelper.executeUpdate(sql,               
                model.getNgayTra(),
                model.getMaPM(),
                model.getMaSach());
    }
    public void delete(String maPM,String MaSach){
        String sql="DELETE FROM CTPhieuMuon WHERE MaPM=? and MaSach=?";
        JdbcHelper.executeUpdate(sql, maPM,MaSach);
    }
        public List<CTPhieuMuon> select() {
        String sql = "SELECT * FROM CTPhieuMuon";
        return select(sql);
    }
    public CTPhieuMuon findById(String maPM) {
        String sql = "SELECT * FROM CTPhieuMuon WHERE MaPM=?";
        List<CTPhieuMuon> list = select(sql, maPM);
        return list.size() > 0 ? list.get(0) : null;
    }
    public CTPhieuMuon findBy2Id(String maPM,String MaSach) {
        String sql = "SELECT * FROM CTPhieuMuon WHERE MaPM=? and MaSach=?";
        List<CTPhieuMuon> list = select(sql, maPM,MaSach);
        return list.size() > 0 ? list.get(0) : null;
    }

    private List<CTPhieuMuon> select(String sql, Object... args) {
        List<CTPhieuMuon> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.executeQuery(sql, args);
                while (rs.next()) {
                    CTPhieuMuon model = readFromResultSet(rs);
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

    private CTPhieuMuon readFromResultSet(ResultSet rs) throws SQLException {
        CTPhieuMuon model = new CTPhieuMuon();
        model.setMaPM(rs.getString("MaPM"));
        model.setMaSach(rs.getString("MaSach"));
        model.setngayTra(rs.getDate("NgayTra"));
        return model;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poly.model;

import com.poly.helper.DateHelper;
import java.util.Date;

/**
 *
 * @author Q5
 */
public class Sach {
    private String MaSach;
    private String TenSach;
    private String TacGia;
    private String NhaXB;
    private int SoLuong;
    private int SoLuongCon;
    private Date NgayNhap=DateHelper.now();
    private int GiaBia;

    public Sach(String MaSach ,String TenSach,String TacGia,String NhaXB,int SoLuong,int SoLuongCon, Date NgayNhap,int GiaBia){
    this.MaSach=MaSach;
    this.TenSach=TenSach;
    this.NhaXB=NhaXB;
    this.SoLuong=SoLuongCon;
    this.NgayNhap=NgayNhap;
    this.GiaBia=GiaBia;    
}
    public Sach(){
        
    }
    public String getMaSach() {
        return MaSach;
    }

    public void setMaSach(String MaSach) {
        this.MaSach = MaSach;
    }

    public String getTenSach() {
        return TenSach;
    }

    public void setTenSach(String TenSach) {
        this.TenSach = TenSach;
    }

    public String getTacGia() {
        return TacGia;
    }

    public void setTacGia(String TacGia) {
        this.TacGia = TacGia;
    }

    public String getNhaXB() {
        return NhaXB;
    }

    public void setNhaXB(String NhaXB) {
        this.NhaXB = NhaXB;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public int getSoLuongCon() {
        return SoLuongCon;
    }

    public void setSoLuongCon(int SoLuongCon) {
        this.SoLuongCon = SoLuongCon;
    }

    public Date getNgayNhap() {
        return NgayNhap;
    }

    public void setNgayNhap(Date NgayNhap) {
        this.NgayNhap = NgayNhap;
    }

    public int getGiaBia() {
        return GiaBia;
    }

    public void setGiaBia(int GiaBia) {
        this.GiaBia = GiaBia;
    }
  
}

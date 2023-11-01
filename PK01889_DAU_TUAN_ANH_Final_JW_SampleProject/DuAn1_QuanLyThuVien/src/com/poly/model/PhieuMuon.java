/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poly.model;

import java.util.Date;

/**
 *
 * @author Q5
 */
public class PhieuMuon {
    private String MaPM;
    private String MaDG;
    private int soluongmuon;
    private float TienCoc;
    private Date NgayMuon;
    private Date NgayHenTra;

    public PhieuMuon() {
    }

    public PhieuMuon(String MaPM, String MaDG,int soluongmuon, float TienCoc, Date NgayMuon, Date NgayHenTra) {
        this.MaPM = MaPM;
        this.MaDG = MaDG;
         this.soluongmuon=soluongmuon;
        this.TienCoc = TienCoc;
        this.NgayMuon = NgayMuon;
        this.NgayHenTra = NgayHenTra;
    }

    public String getMaPM() {
        return MaPM;
    }

    public void setMaPM(String MaPM) {
        this.MaPM = MaPM;
    }

    public String getMaDG() {
        return MaDG;
    }

    public void setMaDG(String MaDG) {
        this.MaDG = MaDG;
    }

    public float getTienCoc() {
        return TienCoc;
    }

    public void setTienCoc(float TienCoc) {
        this.TienCoc = TienCoc;
    }

    public Date getNgayMuon() {
        return NgayMuon;
    }

    public void setNgayMuon(Date NgayMuon) {
        this.NgayMuon = NgayMuon;
    }

    public Date getNgayHenTra() {
        return NgayHenTra;
    }

    public void setNgayHenTra(Date NgayHenTra) {
        this.NgayHenTra = NgayHenTra;
    }

    public int getSoluongmuon() {
        return soluongmuon;
    }

    public void setSoluongmuon(int soluongmuon) {
        this.soluongmuon = soluongmuon;
    }

    
}

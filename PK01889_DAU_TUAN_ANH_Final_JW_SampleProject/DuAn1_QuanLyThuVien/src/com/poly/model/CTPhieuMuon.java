/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poly.model;

import java.util.Date;

/**
 *
 * @author PC
 */
public class CTPhieuMuon {

    private String maPM;
    private String maSach;
    private Date ngayTra;

    public String getMaPM() {
        return maPM;
    }

    public void setMaPM(String maPM) {
        this.maPM = maPM;
    }

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public Date getNgayTra() {
        return ngayTra;
    }

    public void setngayTra(Date ngayDK) {
        this.ngayTra = ngayDK;
    }

}

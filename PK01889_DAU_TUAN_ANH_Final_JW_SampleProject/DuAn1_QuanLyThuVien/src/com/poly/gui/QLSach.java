/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poly.gui;

import com.poly.dao.SachDAO;
import com.poly.helper.DateHelper;
import com.poly.helper.DialogHelper;
import com.poly.helper.ShareHelper;
import com.poly.model.Sach;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author PC
 */
public class QLSach extends javax.swing.JFrame {

    /**
     * Creates new form QLSach
     */
    SachDAO sDAO = new SachDAO();
    int index = 0;

    public QLSach() {
        initComponents();
        this.setResizable(false);//Khoa khong cho phong to
        this.init();
    }

    void init() {
        setIconImage(ShareHelper.APP_ICON);
        setLocationRelativeTo(null);
      //  txtMaSach.setText("S");
    }

    void load() {
        DefaultTableModel model = (DefaultTableModel) tblQuanLySach.getModel();
        model.setRowCount(0);
        try {
            List<Sach> list = sDAO.select();
            for (Sach s : list) {
                model.addRow(new Object[]{
                    s.getMaSach(),
                    s.getTenSach(),
                    s.getTacGia(),
                    s.getNhaXB(),
                    s.getSoLuong(),
                    s.getSoLuongCon(),
                    s.getNgayNhap(),
                    s.getGiaBia()});
            }
        } catch (Exception e) {
            e.printStackTrace();
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    void setModel(Sach model) {
        txtGiaBia.setText(String.valueOf(model.getGiaBia()));
        txtMaSach.setText(String.valueOf(model.getMaSach()));
        txtNgayNhap.setText(DateHelper.toString(model.getNgayNhap()));
        txtNhaXuatBan.setText(model.getNhaXB());
        txtSoLuong.setText(String.valueOf(model.getSoLuong()));
        txtSoLuongCon.setText(String.valueOf(model.getSoLuongCon()));
        txtTacGia.setText(model.getTacGia());
        txtTenSach.setText(model.getTenSach());
    }

//    Sach getModel() {
//        Sach model = new Sach();
//        model.setGiaBia(Integer.valueOf(txtGiaBia.getText()));
//        model.setMaSach(txtMaSach.getText());
//        model.setNgayNhap(DateHelper.toDate(txtNgayNhap.getText()));
//        model.setNhaXB(txtNhaXuatBan.getText());
//        model.setSoLuong(Integer.valueOf(txtSoLuong.getText()));
//        model.setSoLuongCon(Integer.valueOf(txtSoLuongCon.getText()));
//        model.setTacGia(txtTacGia.getText());
//        model.setTenSach(txtTenSach.getText());
//        return model;
//    }

    void insert(Sach model) {   
        String MaSach = txtMaSach.getText();
        String TenSach = txtTenSach.getText();
        String TacGia = txtTacGia.getText();
        String NhaXB = txtNhaXuatBan.getText();
        int SoLuong = Integer.parseInt(txtSoLuong.getText());
        int SoLuongCon = Integer.parseInt(txtSoLuongCon.getText());
        String NgayNhap = txtNgayNhap.getText();
        int GiaBia = Integer.parseInt(txtGiaBia.getText());    
        
        
        Sach sach = sDAO.findById(MaSach);
        if (MaSach.length() <= 0 || MaSach.length() > 8) {
            DialogHelper.alert(this, "Mã sách phải nằm trong khoảng từ 1 đến 8 ký tự");
            txtMaSach.requestFocus();
        }else if(TenSach.equals("")){
            DialogHelper.alert(this, " Tên sách không được để trống");
            txtTenSach.requestFocus();
        } else if (TenSach.matches("[^\\p{L}\\s]")) {
            DialogHelper.alert(this, " Tên sách chỉ được chứa alphabet và kí tự trắng");
            txtTenSach.requestFocus();
        }else if(TacGia.equals("")){
         DialogHelper.alert(this, " Tên tác giả không được để trống");
            txtTacGia.requestFocus();   
        } else if (TacGia.matches("[^\\p{L}\\s]")) {
            DialogHelper.alert(this, " Tên tác giả chỉ được chứa alphabet và kí tự trắng");
            txtTacGia.requestFocus();
         }else if(NhaXB.equals("")){
            DialogHelper.alert(this, " Tên NXB không được để trống");
            txtNhaXuatBan.requestFocus();
        } else if (NhaXB.matches("[^\\p{L}\\s]")) {
            DialogHelper.alert(this, " Tên NXB chỉ được chứa alphabet và kí tự trắng");
            txtNhaXuatBan.requestFocus();
        } else if (SoLuong < 1 && !(txtSoLuong.getText()).matches("[0-9]")) {
            DialogHelper.alert(this, "Số lượng phải lớn hơn hoặc bằng 1");
            txtSoLuong.requestFocus();
        } else if (SoLuong < SoLuongCon || SoLuongCon < 0 && !(txtSoLuongCon.getText()).matches("[0-9]")) {
            DialogHelper.alert(this, "Số lượng còn phải nhỏ hơn hoặc bằng tổng số lượng");
            txtSoLuongCon.requestFocus();
        } else if (!NgayNhap.matches("^\\d{1,2}[/][\\d]{1,2}[/][\\d]{4}$")) {
            DialogHelper.alert(this, "Ngày nhập chưa đúng định dạng (Gợi ý: mm/dd/yyyy)");
            txtNgayNhap.requestFocus();
        } else if (GiaBia <= 0 || !(txtGiaBia.getText()).matches("[0-9]{1,9}")) {
            DialogHelper.alert(this, "Giá bìa phải lớn hơn 0");
            txtGiaBia.requestFocus();
        } else if (sach != null) {
            DialogHelper.alert(this, "Mã sách đã tồn tại");
            txtMaSach.requestFocus();
        } else {
            try {
              //  Sach model = getModel();
                sDAO.insert(model);
                this.load();
                this.clear();
                DialogHelper.alert(this, "Thêm mới thành công");
            } catch (Exception e) {
                e.printStackTrace();
                DialogHelper.alert(this, "Thêm mới thất bại");
            }
        }
    }

    void update(Sach model) {
        String MaSach = txtMaSach.getText();
        String TenSach = txtTenSach.getText();
        String TacGia = txtTacGia.getText();
        String NhaXB = txtNhaXuatBan.getText();
        int SoLuong = Integer.parseInt(txtSoLuong.getText());
        int SoLuongCon = Integer.parseInt(txtSoLuongCon.getText());
        String NgayNhap = txtNgayNhap.getText();
        int GiaBia = Integer.parseInt(txtGiaBia.getText());
        if (MaSach.length() <= 0 || MaSach.length() > 8) {
            DialogHelper.alert(this, "Mã sách phải nằm trong khoảng từ 1 đến 8 ký tự");
            txtMaSach.requestFocus();
        }else if(TenSach.equals("")){
            DialogHelper.alert(this, " Tên sách không được để trống");
            txtTenSach.requestFocus();
        } else if (TenSach.matches("[^\\p{L}\\s]")) {
            DialogHelper.alert(this, " Tên sách chỉ được chứa alphabet và kí tự trắng");
            txtTenSach.requestFocus();
        }else if(TacGia.equals("")){
         DialogHelper.alert(this, " Tên tác giả không được để trống");
            txtTacGia.requestFocus();   
        } else if (TacGia.matches("[^\\p{L}\\s]")) {
            DialogHelper.alert(this, " Tên tác giả chỉ được chứa alphabet và kí tự trắng");
            txtTacGia.requestFocus();
         }else if(NhaXB.equals("")){
            DialogHelper.alert(this, " Tên NXB không được để trống");
            txtNhaXuatBan.requestFocus();
        } else if (NhaXB.matches("[^\\p{L}\\s]")) {
            DialogHelper.alert(this, " Tên NXB chỉ được chứa alphabet và kí tự trắng");
            txtNhaXuatBan.requestFocus();
        
        } else if (SoLuong < 0 && !(txtSoLuong.getText()).matches("[0-9]")) {
            DialogHelper.alert(this, "Số lượng phải lớn hơn hoặc bằng 0");
            txtSoLuong.requestFocus();
        } else if (SoLuong < SoLuongCon || SoLuongCon < 0 && !(txtSoLuongCon.getText()).matches("[0-9]")) {
            DialogHelper.alert(this, "Số lượng còn phải nhỏ hơn hoặc bằng tổng số lượng");
            txtSoLuongCon.requestFocus();
        } else if (!NgayNhap.matches("^\\d{1,2}[/][\\d]{1,2}[/][\\d]{4}$")) {
            DialogHelper.alert(this, "Ngày nhập chưa đúng định dạng (Gợi ý: mm/dd/yyyy)");
            txtNgayNhap.requestFocus();
        } else if (GiaBia <= 0 || !(txtGiaBia.getText()).matches("[0-9]{1,9}")) {
            DialogHelper.alert(this, "Giá bìa phải lớn hơn 0");
            txtGiaBia.requestFocus();
        } else {
            try {
               // Sach model = getModel();
                sDAO.update(model);
                this.load();
                DialogHelper.alert(this, "Cập nhật thành công");
            } catch (Exception e) {
                e.printStackTrace();
                DialogHelper.alert(this, "Cập nhật thất bại");
            }
        }
    }

    void setStatus(boolean insertable) {
        btnThem.setEnabled(insertable);
        btnSua.setEnabled(!insertable);
        btnXoa.setEnabled(!insertable);
        txtMaSach.setEditable(insertable);
        boolean first = this.index > 0;
        boolean last = this.index < tblQuanLySach.getRowCount() - 1;
        btnFirst.setEnabled(!insertable && first);
        btnBack.setEnabled(!insertable && first);
        btnLast.setEnabled(!insertable && last);
        btnNext.setEnabled(!insertable && last);
    }

    void clear() {
        this.setModel(new Sach());
        this.setStatus(true);
    }

    void delete() {
        if (DialogHelper.confirm(this, "Bạn có muốn xóa quyển sách này không ?")) {
            try {
                String MaSach = txtMaSach.getText();
                sDAO.delete(MaSach);
                this.load();
                this.clear();
                DialogHelper.alert(this, "Xóa thành công!");
            } catch (Exception e) {
                e.printStackTrace();
                DialogHelper.alert(this, "Xóa thất bại!");
            }
        }
    }

    void edit() {
        try {
            String MaSach = (String) tblQuanLySach.getValueAt(this.index, 0);
            Sach model = sDAO.findById(MaSach);
            if (model != null) {
                this.setModel(model);
                this.setStatus(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        tabQuanLySach = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtMaSach = new javax.swing.JTextField();
        txtTenSach = new javax.swing.JTextField();
        txtTacGia = new javax.swing.JTextField();
        txtNhaXuatBan = new javax.swing.JTextField();
        txtSoLuong = new javax.swing.JTextField();
        txtSoLuongCon = new javax.swing.JTextField();
        txtNgayNhap = new javax.swing.JTextField();
        txtGiaBia = new javax.swing.JTextField();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnMoi = new javax.swing.JButton();
        btnTrangCHu = new javax.swing.JButton();
        btnFirst = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblQuanLySach = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Quản lý sách");
        setSize(new java.awt.Dimension(865, 627));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel3.setBackground(new java.awt.Color(0, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(153, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("QUẢN LÝ SÁCH");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(102, 102, 102));
        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel4.setForeground(new java.awt.Color(255, 255, 255));

        tabQuanLySach.setBackground(new java.awt.Color(153, 153, 153));

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Mã Sách:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Tên Sách:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Tác Giả:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Nhà Xuất Bản:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Số Lượng:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Số Lượng Còn:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Ngày Nhập:");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Giá Bìa:");

        txtMaSach.setBackground(new java.awt.Color(51, 255, 51));
        txtMaSach.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtTenSach.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtTacGia.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtNhaXuatBan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtSoLuong.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtSoLuongCon.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtNgayNhap.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtGiaBia.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        btnThem.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/poly/icon/Add.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/poly/icon/Edit.png"))); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/poly/icon/Delete.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnMoi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/poly/icon/Document.png"))); // NOI18N
        btnMoi.setText("Mới");
        btnMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoiActionPerformed(evt);
            }
        });

        btnTrangCHu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnTrangCHu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/poly/icon/Home.png"))); // NOI18N
        btnTrangCHu.setText("Quay Lại Trang Chủ");
        btnTrangCHu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTrangCHuActionPerformed(evt);
            }
        });

        btnFirst.setText("|<");
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });

        btnNext.setText(">>");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnBack.setText("<<");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        btnLast.setText(">|");
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });

        jLabel11.setForeground(new java.awt.Color(255, 0, 0));
        jLabel11.setText("*");

        jLabel12.setForeground(new java.awt.Color(255, 0, 0));
        jLabel12.setText("*");

        jLabel13.setForeground(new java.awt.Color(255, 0, 0));
        jLabel13.setText("*");

        jLabel14.setForeground(new java.awt.Color(255, 0, 0));
        jLabel14.setText("*");

        jLabel15.setForeground(new java.awt.Color(255, 0, 0));
        jLabel15.setText("*");

        jLabel16.setForeground(new java.awt.Color(255, 0, 0));
        jLabel16.setText("*");

        jLabel17.setForeground(new java.awt.Color(255, 0, 0));
        jLabel17.setText("*");

        jLabel18.setForeground(new java.awt.Color(255, 0, 0));
        jLabel18.setText("*");

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 0, 0));
        jLabel19.setText("Lưu ý: Dấu \" * \" bắt buộc phải nhập");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel8))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addGap(29, 29, 29)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtGiaBia)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(btnFirst)
                        .addGap(40, 40, 40)
                        .addComponent(btnBack)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNext)
                        .addGap(41, 41, 41)
                        .addComponent(btnLast))
                    .addComponent(txtSoLuong)
                    .addComponent(txtNhaXuatBan)
                    .addComponent(txtTacGia)
                    .addComponent(txtTenSach)
                    .addComponent(txtMaSach)
                    .addComponent(txtSoLuongCon)
                    .addComponent(txtNgayNhap))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14)
                            .addComponent(jLabel15))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnTrangCHu)
                            .addComponent(btnMoi)
                            .addComponent(btnXoa)
                            .addComponent(btnSua)
                            .addComponent(btnThem)))
                    .addComponent(jLabel16)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnMoi, btnSua, btnThem, btnTrangCHu, btnXoa});

        jPanel5Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnBack, btnFirst, btnLast, btnNext});

        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(btnThem)
                        .addGap(25, 25, 25)
                        .addComponent(btnSua)
                        .addGap(25, 25, 25))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMaSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel11))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtTenSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addGap(18, 18, 18)))
                .addGap(6, 6, 6)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtTacGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa)
                    .addComponent(jLabel13))
                .addGap(27, 27, 27)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtNhaXuatBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMoi)
                    .addComponent(jLabel14))
                .addGap(25, 25, 25)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(btnTrangCHu)
                    .addComponent(jLabel15))
                .addGap(24, 24, 24)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSoLuongCon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel16))
                .addGap(26, 26, 26)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNgayNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel17))
                .addGap(32, 32, 32)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGiaBia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel18))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnFirst)
                            .addComponent(btnNext)
                            .addComponent(btnLast)
                            .addComponent(btnBack)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel19)))
                .addGap(0, 61, Short.MAX_VALUE))
        );

        tabQuanLySach.addTab("Cập Nhật", jPanel5);

        jPanel6.setBackground(new java.awt.Color(153, 153, 153));

        tblQuanLySach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Sách", "Tên Sách", "Tác Giả", "Nhà Xuất Bản", "Số Lượng", "SL Còn", "Ngày Nhập", "Giá Bìa"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblQuanLySach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblQuanLySachMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblQuanLySach);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 922, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
        );

        tabQuanLySach.addTab("Danh sách", jPanel6);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabQuanLySach)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(tabQuanLySach)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTrangCHuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrangCHuActionPerformed
        // TODO add your handling code here:
        if (ShareHelper.authenticated()) {
           // new QLThuVienMain().setVisible(true);
            this.dispose();
        } else {
            DialogHelper.alert(this, "Vui lòng đăng nhập");
            new DangNhap().setVisible(true);
        }
    }//GEN-LAST:event_btnTrangCHuActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        this.load();
        this.setStatus(true);
    }//GEN-LAST:event_formWindowOpened

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        // TODO add your handling code here:
        this.clear();
    }//GEN-LAST:event_btnMoiActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        try {
        String Soluong = txtSoLuong.getText();
        String Soluongcon = txtSoLuongCon.getText();
        String Giabia = txtGiaBia.getText();
        if (!Soluong.equals("") && !Soluongcon.equals("") && !Giabia.equals("")) {
         Sach model = new Sach();
        model.setGiaBia(Integer.valueOf(txtGiaBia.getText()));
        model.setMaSach(txtMaSach.getText());
        model.setNgayNhap(DateHelper.toDate(txtNgayNhap.getText()));
        model.setNhaXB(txtNhaXuatBan.getText());
        model.setSoLuong(Integer.valueOf(txtSoLuong.getText()));
        model.setSoLuongCon(Integer.valueOf(txtSoLuongCon.getText()));
        model.setTacGia(txtTacGia.getText());
        model.setTenSach(txtTenSach.getText());
        this.insert(model);
        } else {
            DialogHelper.alert(this, "Không để trống số lượng, số lượng còn hoặc giá bìa");
        }
        } catch (Exception e) {
            DialogHelper.alert(this, "giá bìa không hợp lệ");
        }
        
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        Sach model = new Sach();
        model.setGiaBia(Integer.valueOf(txtGiaBia.getText()));
        model.setMaSach(txtMaSach.getText());
        model.setNgayNhap(DateHelper.toDate(txtNgayNhap.getText()));
        model.setNhaXB(txtNhaXuatBan.getText());
        model.setSoLuong(Integer.valueOf(txtSoLuong.getText()));
        model.setSoLuongCon(Integer.valueOf(txtSoLuongCon.getText()));
        model.setTacGia(txtTacGia.getText());
        model.setTenSach(txtTenSach.getText());
        this.update(model);
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        this.delete();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void tblQuanLySachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblQuanLySachMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            this.index = tblQuanLySach.rowAtPoint(evt.getPoint());
            if (this.index >= 0) {
                this.edit();
                tabQuanLySach.setSelectedIndex(0);
            }
        }
    }//GEN-LAST:event_tblQuanLySachMouseClicked

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        // TODO add your handling code here:
        this.index = 0;
        this.edit();
    }//GEN-LAST:event_btnFirstActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
        this.index--;
        this.edit();
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:
        this.index++;
        this.edit();
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        // TODO add your handling code here:
        this.index = tblQuanLySach.getRowCount() - 1;
        this.edit();
    }//GEN-LAST:event_btnLastActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(QLSach.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QLSach.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QLSach.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QLSach.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QLSach().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnMoi;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTrangCHu;
    private javax.swing.JButton btnXoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane tabQuanLySach;
    private javax.swing.JTable tblQuanLySach;
    private javax.swing.JTextField txtGiaBia;
    private javax.swing.JTextField txtMaSach;
    private javax.swing.JTextField txtNgayNhap;
    private javax.swing.JTextField txtNhaXuatBan;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtSoLuongCon;
    private javax.swing.JTextField txtTacGia;
    private javax.swing.JTextField txtTenSach;
    // End of variables declaration//GEN-END:variables
}

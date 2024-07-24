/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.pms.ui;

import com.pms.dao.History_import_exportDAO;
import com.pms.dao.UsersDAO;
import com.pms.entity.History_import_export;
import com.pms.entity.Users;
import com.pms.utils.CustomListCellRender;
import com.pms.utils.XImage;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.File;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author MSI
 */
public final class ThongKeThang extends javax.swing.JFrame {
    File file;
    Boolean truongPhong;
    String maNV, tenNV, urlAnh, nguoiNhapDangThaoTac;
    History_import_exportDAO tkdao = new History_import_exportDAO();
    History_import_export tk = new History_import_export();
    Users user = new Users();
    UsersDAO userDao=new UsersDAO();
    
    
    /**
     * Creates new form ThongKeThang
     */
    public ThongKeThang() {
       initComponents();
       fillComboBoxMaHang();
       fillTableThongKe();
       fillComboBoxNhanVien();
       fillTableTaiKhoan(); 
       setLayoutTable();
       setLocationRelativeTo(null);
       cboTimNhanVien.setRenderer(new CustomListCellRender());
       comboTimMH.setRenderer(new CustomListCellRender());
       lblTongNV.setText(String.valueOf(userDao.selectTongNhanVien()));
       lblSPKho.setText(String.valueOf(tkdao.selectSPKho()));
       lblSLBD.setText(String.valueOf(tkdao.selectSLBD()));
       lblSLTK.setText(String.valueOf(tkdao.selectSLTK()));
       this.setIconImage(XImage.getAppIcon());
    }
    
    public void get_Data_From_DifFrame(Boolean tp, String manv, String tennv, String urlanh){
        truongPhong = tp;
        maNV = manv;
        tenNV = tennv;
        urlAnh = urlanh;
    }
    
    public void setLayoutTable(){
        //Thêm màu
        tblthongke.setGridColor(new Color(227,227,227));
        //Định dạng dòng tiêu đề (font chữ, chiều cao)
        JTableHeader titleHeader = tblthongke.getTableHeader();
        titleHeader.setFont(new Font("Montserrat", Font.BOLD, 14));
        titleHeader.setPreferredSize(new Dimension(titleHeader.getWidth(), 40));
        tblTaiKhoan.setGridColor(new Color(227,227,227));
        //Định dạng dòng tiêu đề (font chữ, chiều cao)
        JTableHeader titleHeader1 = tblTaiKhoan.getTableHeader();
        titleHeader1.setFont(new Font("Montserrat", Font.BOLD, 14));
        titleHeader1.setPreferredSize(new Dimension(titleHeader.getWidth(), 40));
    }
    
    public void fillTableTaiKhoan() {
        
         DefaultTableModel model = (DefaultTableModel) tblTaiKhoan.getModel() ;
         model.setRowCount(0);
         try {
             List<Users> list = userDao.selectAllThongKe();
             for(Users us : list){
                  Object[] row = {
                  us.getMaNV(),
                  us.getMatkhau(),
                  us.getTenNV()
                  };
                model.addRow(row);
             }
             
         } catch (Exception e) {
             System.out.println("Lỗi truy vân dữ liệu!");             
         }
    }
    void fillComboBoxNhanVien(){ 
        DefaultComboBoxModel model=(DefaultComboBoxModel)cboTimNhanVien.getModel();
        model.removeAllElements();
        try {
           List<Users> list = userDao.selectAllThongKe();
            for(Users us: list){
            model.addElement(us);
            }
        } catch (Exception e) {
                        System.out.println("chuyde"+e);
        } 
    }
     public void fillTableThongKe(){
         DefaultTableModel model = (DefaultTableModel) tblthongke.getModel() ;
         model.setRowCount(0) ;
         try {
             List<History_import_export> list = tkdao.selectAll();
             for(History_import_export tk : list){
                  Object[] row = {
                  tk.getID(),
                  tk.getMaHang(),
                  tk.getTenhang(),
                  tk.getSoluongbandau(),
                  tk.getSoluongtonkho()
                  };
                model.addRow(row);
             }
             
         } catch (Exception e) {
             System.out.println("Lỗi truy vân dữ liệu!");             
         }
     }
     void fillComboBoxMaHang(){ 
        DefaultComboBoxModel model=(DefaultComboBoxModel)comboTimMH.getModel();
        model.removeAllElements();
        try {
           List<History_import_export> list = tkdao.selectAll();
            for(History_import_export tk: list){
            model.addElement(tk);
            }
        } catch (Exception e) {
                        System.out.println("chuyde"+e);
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

        jMenu1 = new javax.swing.JMenu();
        tabs = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        comboTimMH = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblthongke = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        lblTongNV = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblSPKho = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lblSLTK = new javax.swing.JLabel();
        lblSLBD = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cboTimNhanVien = new javax.swing.JComboBox<>();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblTaiKhoan = new javax.swing.JTable();
        btnTrangChu1 = new javax.swing.JButton();

        jMenu1.setText("jMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Thống kê tháng");
        setResizable(false);

        tabs.setBackground(new java.awt.Color(255, 255, 255));
        tabs.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tabs.setOpaque(true);

        jPanel4.setBackground(new java.awt.Color(228, 228, 228));

        jLabel2.setFont(new java.awt.Font("Montserrat", 1, 12)); // NOI18N
        jLabel2.setText("TÌM KIẾM MÃ HÀNG :  ");

        comboTimMH.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        comboTimMH.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả" }));
        comboTimMH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTimMHActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comboTimMH, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboTimMH, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setText("TỔNG XUẤT :");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 51, 255));
        jLabel8.setText("10");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 0, 0));
        jLabel10.setText("10");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setText("TỔNG NHẬP :");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        tblthongke.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        tblthongke.setForeground(new java.awt.Color(72, 72, 72));
        tblthongke.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "STT", "MÃ HÀNG ", "TÊN HÀNG", "SỐ LƯỢNG BAN ĐẦU ", "SỐ LƯỢNG TỒN KHO"
            }
        ));
        tblthongke.setRowHeight(35);
        tblthongke.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                tblthongkeMouseMoved(evt);
            }
        });
        tblthongke.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblthongkeMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblthongkeMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblthongke);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/pms/icon/group_2_line.png"))); // NOI18N
        jLabel4.setText("Tài khoản người dùng :");

        lblTongNV.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTongNV.setForeground(new java.awt.Color(255, 0, 51));
        lblTongNV.setText("?");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/pms/icon/edit_4_line.png"))); // NOI18N
        jLabel3.setText("Sản phẩm trong kho : ");

        lblSPKho.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblSPKho.setForeground(new java.awt.Color(255, 0, 51));
        lblSPKho.setText("?");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/pms/icon/package_line.png"))); // NOI18N
        jLabel11.setText("Số lượng ban đầu trong kho : ");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/pms/icon/package_2_line.png"))); // NOI18N
        jLabel12.setText("Số lượng tồn trong kho : ");

        lblSLTK.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblSLTK.setForeground(new java.awt.Color(255, 0, 51));
        lblSLTK.setText("?");

        lblSLBD.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblSLBD.setForeground(new java.awt.Color(255, 0, 51));
        lblSLBD.setText("?");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel7)
                .addGap(31, 31, 31)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63)
                .addComponent(jLabel9)
                .addGap(30, 30, 30)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTongNV, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(96, 96, 96)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblSPKho, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(93, 93, 93)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblSLBD, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblSLTK, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(131, 131, 131)
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(1003, 1003, 1003))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel12)
                                .addComponent(lblSLTK))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4)
                                .addComponent(jLabel3)
                                .addComponent(lblTongNV)
                                .addComponent(lblSPKho)
                                .addComponent(jLabel11)
                                .addComponent(lblSLBD)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(jLabel10))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(jLabel8)))
                .addGap(21, 21, 21))
        );

        tabs.addTab("Sản Phẩm", jPanel1);

        jPanel2.setBackground(new java.awt.Color(228, 228, 228));

        jLabel1.setFont(new java.awt.Font("Montserrat", 1, 12)); // NOI18N
        jLabel1.setText("TÌM KIẾM MÃ NHÂN VIÊN :");

        cboTimNhanVien.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        cboTimNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTimNhanVienActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cboTimNhanVien, 0, 431, Short.MAX_VALUE)
                    .addComponent(jLabel1))
                .addGap(22, 22, 22))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboTimNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        tblTaiKhoan.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        tblTaiKhoan.setForeground(new java.awt.Color(0, 0, 255));
        tblTaiKhoan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "TÊN ĐĂNG NHẬP", "MẬT KHẨU", "HỌ VÀ TÊN"
            }
        ));
        tblTaiKhoan.setRowHeight(35);
        jScrollPane2.setViewportView(tblTaiKhoan);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 487, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabs.addTab("Tài Khoản", jPanel5);

        btnTrangChu1.setBackground(new java.awt.Color(202, 233, 255));
        btnTrangChu1.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        btnTrangChu1.setForeground(new java.awt.Color(0, 102, 204));
        btnTrangChu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/pms/icon/home_3_line.png"))); // NOI18N
        btnTrangChu1.setText("Trang chủ");
        btnTrangChu1.setBorder(null);
        btnTrangChu1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTrangChu1.setIconTextGap(10);
        btnTrangChu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTrangChu1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tabs, javax.swing.GroupLayout.PREFERRED_SIZE, 1268, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTrangChu1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnTrangChu1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(tabs, javax.swing.GroupLayout.PREFERRED_SIZE, 662, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblthongkeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblthongkeMousePressed
        //        // TODO add your handling code here:
    }//GEN-LAST:event_tblthongkeMousePressed

    private void tblthongkeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblthongkeMouseClicked

    }//GEN-LAST:event_tblthongkeMouseClicked

    private void tblthongkeMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblthongkeMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_tblthongkeMouseMoved

    private void comboTimMHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboTimMHActionPerformed
        // TODO add your handling code here:
        String tenHang=String.valueOf(comboTimMH.getSelectedItem());
        DefaultTableModel model = (DefaultTableModel) tblthongke.getModel() ;
        model.setRowCount(0) ;
        try {
            List<History_import_export> list = tkdao.selectByTenHang(tenHang);
            for(History_import_export tk : list){
                Object[] row = {
                    tk.getID(),
                    tk.getMaHang(),
                    tk.getTenhang(),
                    tk.getSoluongbandau(),
                    tk.getSoluongtonkho()
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            System.out.println("Lỗi truy vân dữ liệu!");
        }
    }//GEN-LAST:event_comboTimMHActionPerformed

    private void cboTimNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTimNhanVienActionPerformed
        // TODO add your handling code here:
        String maNV=String.valueOf(cboTimNhanVien.getSelectedItem());
        DefaultTableModel model = (DefaultTableModel) tblTaiKhoan.getModel() ;
        model.setRowCount(0) ;
        try {
            List<Users> list = userDao.selectByMaNhanVien(maNV);
            for(Users us : list){
                Object[] row = {
                  us.getMaNV(),
                  us.getMatkhau(),
                  us.getTenNV() 
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            System.out.println("Lỗi truy vân dữ liệu!");
        }
    }//GEN-LAST:event_cboTimNhanVienActionPerformed

    private void btnTrangChu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrangChu1ActionPerformed
        // TODO add your handling code here:
        dispose();
        TrangChu trangChuJFrame = new TrangChu();
        trangChuJFrame.get_Data_From_DifFrame(truongPhong, maNV, tenNV, urlAnh);
        trangChuJFrame.setVisible(true);                                   
                                        
    }//GEN-LAST:event_btnTrangChu1ActionPerformed

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
            java.util.logging.Logger.getLogger(ThongKeThang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThongKeThang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThongKeThang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThongKeThang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ThongKeThang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTrangChu1;
    private javax.swing.JComboBox<String> cboTimNhanVien;
    private javax.swing.JComboBox<String> comboTimMH;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblSLBD;
    private javax.swing.JLabel lblSLTK;
    private javax.swing.JLabel lblSPKho;
    private javax.swing.JLabel lblTongNV;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JTable tblTaiKhoan;
    private javax.swing.JTable tblthongke;
    // End of variables declaration//GEN-END:variables

    
}

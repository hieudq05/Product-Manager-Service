/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.pms.ui;

import com.pms.dao.History_Import_Export_DAO;
import com.pms.entity.History_IP_EP;
import com.pms.utils.CustomListCellRender;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.File;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author MSI
 */
public class LichSuXuatNhap extends javax.swing.JFrame {

    History_Import_Export_DAO dao = new History_Import_Export_DAO();
    Boolean truongPhong;
    String maNV, tenNV, urlAnh;
    DefaultTableModel model;
    File file;

    /**
     * Creates new form LichSuXuatNhap
     */
    public LichSuXuatNhap() {
        initComponents();
        setLocationRelativeTo(null);
        setLayoutTable();
        setLayoutListNhanVien();
        filltable();
        fillList();
        setLocationRelativeTo(null);
        this.getContentPane().setBackground(new Color(255,255,255));
    }

    public void setLayoutTable() {
        tblLichSuNhapXuat.setShowGrid(true);
        tblLichSuNhapXuat.setGridColor(new Color(227, 227, 227));
        JTableHeader titleHeader = tblLichSuNhapXuat.getTableHeader();
        titleHeader.setFont(new Font("Montserrat", Font.BOLD, 12));
        titleHeader.setPreferredSize(new Dimension(titleHeader.getWidth(), 40));
    }

    public void setLayoutListNhanVien() {
        cboNhanVien.setRenderer(new CustomListCellRender());
        cboMaHang.setRenderer(new CustomListCellRender());
        cboNgayThaoTac.setRenderer(new CustomListCellRender());
    }

    public void filltable() {
        model = (DefaultTableModel) tblLichSuNhapXuat.getModel();
        model.setRowCount(0);
        try {
            List<History_IP_EP> list = dao.selectAll();
            for (History_IP_EP ls : list) {
                Object[] row = {
                    ls.getID(),
                    ls.getNgayThaoTac(),
                    ls.getThaoTac(),
                    ls.getMaHang(),
                    ls.getTenHang(),
                    ls.getDonGia(),
                    ls.getSoLuong(),
                    ls.getNguoiThaoTac(),
                    ls.getSoLuongBanDau(),
                    ls.getSoLuongConLai()
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            System.out.println("filtable:" + e.toString());
        }
    }
    
    public void fillList(){
        List<String> listNguoiThaoTac = new History_Import_Export_DAO().select("nguoithaotac");
        for (String maNguoiThaoTac : listNguoiThaoTac) {
            cboNhanVien.addItem(maNguoiThaoTac);
        }
        
        List<String> listMaHang = new History_Import_Export_DAO().select("mahang");
        for (String maHang : listMaHang) {
            cboMaHang.addItem(maHang);
        }
        
        List<String> listNgayThaoTac = new History_Import_Export_DAO().select("ngaythaotac");
        for (String ngayThaoTac : listNgayThaoTac) {
            cboNgayThaoTac.addItem(ngayThaoTac);
        }
    }

    public void get_Data_From_DifFrame(Boolean tp, String manv, String tennv, String urlanh) {
        truongPhong = tp;
        maNV = manv;
        tenNV = tennv;
        urlAnh = urlanh;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblLichSuNhapXuat = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        cboNhanVien = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        cboMaHang = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cboNgayThaoTac = new javax.swing.JComboBox<>();
        ExportFile = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        btnTrangChu = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Lịch sử xuất nhập hàng");

        tblLichSuNhapXuat.setAutoCreateRowSorter(true);
        tblLichSuNhapXuat.setFont(new java.awt.Font("Montserrat", 1, 12)); // NOI18N
        tblLichSuNhapXuat.setForeground(new java.awt.Color(0, 102, 204));
        tblLichSuNhapXuat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"1", "30/06/2024", "Xuất hàng", "PR01", "Bì bắp", "2", null, "Dương Quốc Hiếu", "20", "18"}
            },
            new String [] {
                "ID", "Ngày thao tác", "Thao tác", "Mã hàng", "Tên hàng", "Số lượng", "Đơn giá", "Người thao tác", "Số lượng ban đầu", "Số lượng còn lại"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblLichSuNhapXuat.setRowHeight(35);
        tblLichSuNhapXuat.setSelectionBackground(new java.awt.Color(207, 231, 255));
        tblLichSuNhapXuat.setSelectionForeground(new java.awt.Color(0, 102, 204));
        tblLichSuNhapXuat.setShowGrid(true);
        jScrollPane1.setViewportView(tblLichSuNhapXuat);
        if (tblLichSuNhapXuat.getColumnModel().getColumnCount() > 0) {
            tblLichSuNhapXuat.getColumnModel().getColumn(0).setPreferredWidth(10);
        }

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        cboNhanVien.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        cboNhanVien.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả" }));
        cboNhanVien.setOpaque(true);
        cboNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboNhanVienMouseClicked(evt);
            }
        });
        cboNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboNhanVienActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 204));
        jLabel1.setText("Nhân viên:");

        cboMaHang.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        cboMaHang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả" }));
        cboMaHang.setOpaque(true);
        cboMaHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboMaHangMouseClicked(evt);
            }
        });
        cboMaHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboMaHangActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 102, 204));
        jLabel3.setText("Mã hàng:");

        jLabel4.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 102, 204));
        jLabel4.setText("Ngày thao tác:");

        cboNgayThaoTac.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        cboNgayThaoTac.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả" }));
        cboNgayThaoTac.setOpaque(true);
        cboNgayThaoTac.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboNgayThaoTacMouseClicked(evt);
            }
        });
        cboNgayThaoTac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboNgayThaoTacActionPerformed(evt);
            }
        });

        ExportFile.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        ExportFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/pms/icon/task_2_line.png"))); // NOI18N
        ExportFile.setText("Xuất file Excel");
        ExportFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExportFileActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(cboNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 462, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(cboMaHang, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(79, 79, 79)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cboNgayThaoTac, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 285, Short.MAX_VALUE)
                        .addComponent(ExportFile, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(91, 91, 91))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboMaHang, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboNgayThaoTac, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ExportFile, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Montserrat", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Bộ lọc");

        btnTrangChu.setBackground(new java.awt.Color(225, 242, 255));
        btnTrangChu.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        btnTrangChu.setForeground(new java.awt.Color(0, 102, 204));
        btnTrangChu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/pms/icon/home_3_line.png"))); // NOI18N
        btnTrangChu.setText("Trang chủ");
        btnTrangChu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 2));
        btnTrangChu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTrangChu.setIconTextGap(10);
        btnTrangChu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTrangChuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(60, 60, 60))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnTrangChu, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(0, 1705, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(btnTrangChu, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 451, Short.MAX_VALUE)
                .addGap(69, 69, 69))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cboNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboNhanVienMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cboNhanVienMouseClicked

    private void cboNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboNhanVienActionPerformed
        List<History_IP_EP> list = new History_Import_Export_DAO().selectHistory(cboMaHang.getSelectedIndex()==0?null:cboMaHang.getSelectedItem().toString()
                , cboNgayThaoTac.getSelectedIndex()==0?null:cboNgayThaoTac.getSelectedItem().toString()
                , cboNhanVien.getSelectedIndex()==0?null:cboNhanVien.getSelectedItem().toString());
        model.setRowCount(0);
        for (History_IP_EP history_IP_EP : list) {
            Object[] row = {
                history_IP_EP.getID(),
                history_IP_EP.getNgayThaoTac(),
                history_IP_EP.getThaoTac(),
                history_IP_EP.getMaHang(),
                history_IP_EP.getTenHang(),
                history_IP_EP.getSoLuong(),
                history_IP_EP.getNguoiThaoTac(),
                history_IP_EP.getSoLuongBanDau(),
                history_IP_EP.getSoLuongConLai()
            };
            model.addRow(row);
        }
        
    }//GEN-LAST:event_cboNhanVienActionPerformed

    private void cboMaHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboMaHangMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cboMaHangMouseClicked

    private void cboMaHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboMaHangActionPerformed
        List<History_IP_EP> list = new History_Import_Export_DAO().selectHistory(cboMaHang.getSelectedIndex()==0?null:cboMaHang.getSelectedItem().toString()
                , cboNgayThaoTac.getSelectedIndex()==0?null:cboNgayThaoTac.getSelectedItem().toString()
                , cboNhanVien.getSelectedIndex()==0?null:cboNhanVien.getSelectedItem().toString());
        model.setRowCount(0);
        for (History_IP_EP history_IP_EP : list) {
            Object[] row = {
                history_IP_EP.getID(),
                history_IP_EP.getNgayThaoTac(),
                history_IP_EP.getThaoTac(),
                history_IP_EP.getMaHang(),
                history_IP_EP.getTenHang(),
                history_IP_EP.getSoLuong(),
                history_IP_EP.getNguoiThaoTac(),
                history_IP_EP.getSoLuongBanDau(),
                history_IP_EP.getSoLuongConLai()
            };
            model.addRow(row);
        }
    }//GEN-LAST:event_cboMaHangActionPerformed

    private void cboNgayThaoTacMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboNgayThaoTacMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cboNgayThaoTacMouseClicked

    private void cboNgayThaoTacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboNgayThaoTacActionPerformed
        List<History_IP_EP> list = new History_Import_Export_DAO().selectHistory(cboMaHang.getSelectedIndex()==0?null:cboMaHang.getSelectedItem().toString()
                , cboNgayThaoTac.getSelectedIndex()==0?null:cboNgayThaoTac.getSelectedItem().toString()
                , cboNhanVien.getSelectedIndex()==0?null:cboNhanVien.getSelectedItem().toString());
                //sdf.format(cboNgayThaoTac.getSelectedItem().toString()
        model.setRowCount(0);
        for (History_IP_EP history_IP_EP : list) {
            Object[] row = {
                history_IP_EP.getID(),
                history_IP_EP.getNgayThaoTac(),
                history_IP_EP.getThaoTac(),
                history_IP_EP.getMaHang(),
                history_IP_EP.getTenHang(),
                history_IP_EP.getSoLuong(),
                history_IP_EP.getNguoiThaoTac(),
                history_IP_EP.getSoLuongBanDau(),
                history_IP_EP.getSoLuongConLai()
            };
            model.addRow(row);
        }
    }//GEN-LAST:event_cboNgayThaoTacActionPerformed

    private void btnTrangChuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrangChuActionPerformed
        dispose();
        TrangChu trangChuJFrame = new TrangChu();
        trangChuJFrame.get_Data_From_DifFrame(truongPhong, maNV, tenNV, urlAnh);
        trangChuJFrame.setVisible(true);
    }//GEN-LAST:event_btnTrangChuActionPerformed

    private void ExportFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExportFileActionPerformed
        
    }//GEN-LAST:event_ExportFileActionPerformed

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LichSuXuatNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LichSuXuatNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LichSuXuatNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LichSuXuatNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LichSuXuatNhap().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ExportFile;
    private javax.swing.JButton btnTrangChu;
    private javax.swing.JComboBox<String> cboMaHang;
    private javax.swing.JComboBox<String> cboNgayThaoTac;
    private javax.swing.JComboBox<String> cboNhanVien;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblLichSuNhapXuat;
    // End of variables declaration//GEN-END:variables

}

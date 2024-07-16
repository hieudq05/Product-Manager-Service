/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.pms.ui;

import com.pms.dao.UsersDAO;
import com.pms.entity.Users;
import java.awt.Color;
import java.awt.Image;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author phongnguyen
 */
public class DoiMatkhau extends javax.swing.JFrame {

    int countShowPasswordOld, countShowPasswordNew;
    File file;
    Boolean truongPhong;
    String maNV, tenNV, urlAnh;
    /**
     * Creates new form DoiMatkhau
     */
    public DoiMatkhau() {
        initComponents();
        setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.WHITE);
    }
    
    public void get_Data_From_DifFrame(Boolean tp, String manv, String tennv, String urlanh){
        truongPhong = tp;
        maNV = manv;
        tenNV = tennv;
        urlAnh = urlanh;
        lblVaiTro.setText(tp==true?"Trưởng phòng":"Nhân viên");
        lblTenNhanVien.setText(tennv);
        int newHeight = (lblAnhNhanVien.getWidth() * new ImageIcon(urlanh).getImage().getHeight(null)) / new ImageIcon(urlanh).getImage().getWidth(null);
        Image img = new ImageIcon(urlanh).getImage().getScaledInstance(lblAnhNhanVien.getWidth(), newHeight, Image.SCALE_SMOOTH);
        lblAnhNhanVien.setIcon(new ImageIcon(img));
    }
    
    public Boolean checkNaN(){
        return txtUsername.getText().isEmpty() || txtOldPassword.getText().isEmpty() || txtEnterNewPassword.getText().isEmpty() || txtReEnterNewPassword.getText().isEmpty();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnContinue = new javax.swing.JButton();
        btnShowPasswordOld = new javax.swing.JButton();
        btnShowPasswordNew = new javax.swing.JButton();
        txtEnterNewPassword = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblAnhNhanVien = new javax.swing.JLabel();
        txtReEnterNewPassword = new javax.swing.JPasswordField();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnReturn2 = new javax.swing.JButton();
        txtOldPassword = new javax.swing.JPasswordField();
        lblTenNhanVien = new javax.swing.JLabel();
        lblVaiTro = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnContinue.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        btnContinue.setText("Tiếp tục");
        btnContinue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnContinueActionPerformed(evt);
            }
        });

        btnShowPasswordOld.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        btnShowPasswordOld.setText("Show");
        btnShowPasswordOld.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowPasswordOldActionPerformed(evt);
            }
        });

        btnShowPasswordNew.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        btnShowPasswordNew.setText("Show");
        btnShowPasswordNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowPasswordNewActionPerformed(evt);
            }
        });

        txtEnterNewPassword.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        jLabel3.setText("Mật khẩu mới");

        jLabel4.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        jLabel4.setText("Nhập lại mật khẩu");

        lblAnhNhanVien.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        lblAnhNhanVien.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAnhNhanVien.setText("Image");

        txtReEnterNewPassword.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtReEnterNewPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtReEnterNewPasswordActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 0, 0));
        jLabel7.setText("*");

        jLabel1.setFont(new java.awt.Font("Montserrat", 2, 12)); // NOI18N
        jLabel1.setText("Mã nhân viên:");

        jLabel6.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 0, 0));
        jLabel6.setText("*");

        txtUsername.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        txtUsername.setText("1");
        txtUsername.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        txtUsername.setEnabled(false);

        jLabel5.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 0, 0));
        jLabel5.setText("*");

        jLabel2.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        jLabel2.setText("Mật khẩu cũ");

        btnReturn2.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        btnReturn2.setText("Quay lại");
        btnReturn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReturn2ActionPerformed(evt);
            }
        });

        txtOldPassword.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N

        lblTenNhanVien.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        lblTenNhanVien.setText("Dương Quốc Hiếu");

        lblVaiTro.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        lblVaiTro.setText("Trưởng phòng");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(6, 6, 6)
                        .addComponent(jLabel5))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtOldPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(btnShowPasswordOld, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(6, 6, 6)
                        .addComponent(jLabel6))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtEnterNewPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(btnShowPasswordNew, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(6, 6, 6)
                        .addComponent(jLabel7))
                    .addComponent(txtReEnterNewPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnReturn2, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(btnContinue, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 107, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(lblVaiTro)
                        .addComponent(lblAnhNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.CENTER, layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(lblTenNhanVien)
                        .addGap(37, 37, 37)))
                .addGap(92, 92, 92))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel1)
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(btnShowPasswordOld, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtOldPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel6))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(btnShowPasswordNew, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEnterNewPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel7))
                        .addGap(6, 6, 6)
                        .addComponent(txtReEnterNewPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnReturn2)
                            .addComponent(btnContinue)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblAnhNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTenNhanVien)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblVaiTro)))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnContinueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContinueActionPerformed
        if(checkNaN()==true){
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đủ dữ liệu", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } else {
            Users nv = new Users();
            nv = new UsersDAO().selectByID(txtUsername.getText());
            if(txtOldPassword.getText().equals(nv.getMatkhau())){
                if(txtEnterNewPassword.getText().equals(txtReEnterNewPassword.getText())){
                    nv.setMatkhau(txtReEnterNewPassword.getText());
                    new UsersDAO().update(nv);
                    JOptionPane.showMessageDialog(this, "Đổi mật khẩu thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                    TrangChu trangchu = new TrangChu();
                    trangchu.get_Data_From_DifFrame(truongPhong, maNV, tenNV, urlAnh);
                    trangchu.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, "Mật khẩu không trùng khớp!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Mật khẩu cũ không chính xác", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnContinueActionPerformed

    private void btnShowPasswordOldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowPasswordOldActionPerformed
        countShowPasswordOld++;
        if(countShowPasswordOld%2!=0){
            txtOldPassword.setEchoChar((char) 0);
            btnShowPasswordOld.setText("Hide");
        } else {
            txtOldPassword.setEchoChar('\u25CF');
            btnShowPasswordOld.setText("Show");
        }
    }//GEN-LAST:event_btnShowPasswordOldActionPerformed

    private void btnShowPasswordNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowPasswordNewActionPerformed
        countShowPasswordNew++;
        if(countShowPasswordNew%2!=0){
            txtEnterNewPassword.setEchoChar((char) 0);
            txtReEnterNewPassword.setEchoChar((char) 0);
            btnShowPasswordNew.setText("Hide");
        } else {
            txtEnterNewPassword.setEchoChar('\u25CF');
            txtReEnterNewPassword.setEchoChar('\u25CF');
            btnShowPasswordNew.setText("Show");
        }
    }//GEN-LAST:event_btnShowPasswordNewActionPerformed

    private void txtReEnterNewPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtReEnterNewPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtReEnterNewPasswordActionPerformed

    private void btnReturn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReturn2ActionPerformed
        dispose();
        TrangChu trangChuJFrame = new TrangChu();
        trangChuJFrame.get_Data_From_DifFrame(truongPhong, maNV, tenNV, urlAnh);
        trangChuJFrame.setVisible(true);
    }//GEN-LAST:event_btnReturn2ActionPerformed

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
            java.util.logging.Logger.getLogger(DoiMatkhau.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DoiMatkhau.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DoiMatkhau.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DoiMatkhau.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DoiMatkhau().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnContinue;
    private javax.swing.JButton btnReturn2;
    private javax.swing.JButton btnShowPasswordNew;
    private javax.swing.JButton btnShowPasswordOld;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel lblAnhNhanVien;
    private javax.swing.JLabel lblTenNhanVien;
    private javax.swing.JLabel lblVaiTro;
    private javax.swing.JPasswordField txtEnterNewPassword;
    private javax.swing.JPasswordField txtOldPassword;
    private javax.swing.JPasswordField txtReEnterNewPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.pms.ui;

import com.pms.dao.ProductsDAO;
import com.pms.entity.Products;
import com.pms.utils.MsgBox;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.util.List;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.JTableHeader;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author MSI
 */
public class QuanLyDanhSachHang extends javax.swing.JFrame {
    ProductsDAO dao = new ProductsDAO();
    int row = 0;
    File file;
    Integer rowSellected = null;
    /**
     * Creates new form Products_Manager
     */
    public QuanLyDanhSachHang() {
        initComponents();
        this.getContentPane().setBackground(new Color(255,255,255));
        setLocationRelativeTo(null);
        setLayoutTable();
        fillTable();
    }
    void fillTable(){
        DefaultTableModel model = (DefaultTableModel) tblHangHoa.getModel();
        model.setRowCount(0);
        try {
            List<Products> list = dao.selectAll();
            for (Products pd : list) {
                Object[] row = {
                    pd.getMaHang(),
                    pd.getTenHang(),
                    pd.getSoLuong(),
                    pd.getDonGia(),
                    pd.getHinhAnh(),
                    pd.getGhiChu()
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy cập dữ liệu...<:>");
        }
    }
    
    Products getForm(){
        Products model = new Products();
        model.setMaHang(txtMaHang.getText());
        model.setTenHang(txtTenHang.getText());
        model.setSoLuong((Integer) spnSoLuong.getValue());
        model.setHinhAnh(file.getAbsolutePath());
        model.setGhiChu(txtghichu.getText());
        model.setDonGia(Integer.valueOf(txtDonGia.getText()));
        return model;
        
    }
    void edit(){
        rowSellected = tblHangHoa.getSelectedRow();
        file = new File(tblHangHoa.getValueAt(rowSellected, 4).toString());
        txtMaHang.setText(tblHangHoa.getValueAt(rowSellected, 0).toString());
        txtTenHang.setText(tblHangHoa.getValueAt(rowSellected, 1).toString());
        txtghichu.setText(tblHangHoa.getValueAt(rowSellected, 5).toString());
        txtDonGia.setText(tblHangHoa.getValueAt(rowSellected, 3).toString());
        spnSoLuong.setValue(tblHangHoa.getValueAt(rowSellected, 2));
        lblImage.setText("");
        int newHeight = (lblImage.getWidth() * new ImageIcon(tblHangHoa.getValueAt(rowSellected, 4).toString()).getImage().getHeight(null)) / new ImageIcon(tblHangHoa.getValueAt(rowSellected, 4).toString()).getImage().getWidth(null);
        Image img = new ImageIcon(tblHangHoa.getValueAt(rowSellected, 4).toString()).getImage().getScaledInstance(lblImage.getWidth(), newHeight, Image.SCALE_SMOOTH);
        lblImage.setIcon(new ImageIcon(img));
        lblMaHangDangThaoTac.setText(tblHangHoa.getValueAt(rowSellected, 0).toString());
        file = new File(tblHangHoa.getValueAt(rowSellected, 4).toString());
        spnSoLuong.setEnabled(false);
    }
    
    void clearForm(){
        txtMaHang.setText("");
        txtDonGia.setText("");
        txtTenHang.setText("");
        txtghichu.setText("");
        lblImage.setText("Click here to add photo");
        lblImage.setIcon(null);
        lblMaHangDangThaoTac.setText("<Mã hàng>");
        spnSoLuong.setValue(0);
        file = null;
        spnSoLuong.setEnabled(true);
    }
    
    public void setLayoutTable(){
        tblHangHoa.setShowGrid(true);
        tblHangHoa.setGridColor(new Color(227,227,227));
        JTableHeader titleHeader = tblHangHoa.getTableHeader();
        titleHeader.setFont(new Font("Montserrat", Font.BOLD, 14));
        titleHeader.setPreferredSize(new Dimension(titleHeader.getWidth(), 40));
    }
    
    public void get_Data_From_DifFrame(Boolean tp, String manv, String tennv, String urlanh){
        truongPhong = tp;
        maNV = manv;
        tenNV = tennv;
        urlAnh = urlanh;
    }
    
    void insert(){
        Products model = getForm();
        try {
            dao.insert(model);
            this.fillTable();
            MsgBox.alert(this, "Thêm mới thành công!");
        } catch (Exception e) {
            MsgBox.alert(this, "Thêm mới thất bại!");
        }
    }
    
    void update() {
        Products model = getForm();
        try {
            model.setMaHang(lblMaHangDangThaoTac.getText());
            dao.update(model);
            fillTable();
            MsgBox.alert(this, "Cập nhật thành công!");
        } catch (Exception e) {
            MsgBox.alert(this, "Cập nhật thất bại!");
        }
    }
    void delete() {
        if(lblMaHangDangThaoTac.getText().equals("<Mã hàng>")==false){
            if (MsgBox.confirm(this, "Bạn có chắc muốn xóa sản phẩm này không?")) {
                String MaHang = lblMaHangDangThaoTac.getText();
                try {
                    dao.delete(MaHang);
                    fillTable();
                    MsgBox.alert(this, "Xóa thành công!");
                } catch (Exception e) {
                    MsgBox.alert(this, "Xóa thất bại!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm muốn xóa!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public Boolean checkNaN(){
        return txtMaHang.getText().isEmpty() || txtTenHang.getText().isEmpty() || txtDonGia.getText().isEmpty() || lblImage.getIcon() == null;
    }
    
    Boolean truongPhong;
    String maNV, tenNV, urlAnh;
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabs = new javax.swing.JScrollPane();
        tblHangHoa = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        lblImage = new javax.swing.JLabel();
        lblMaHang = new javax.swing.JLabel();
        txtMaHang = new javax.swing.JTextField();
        txtTenHang = new javax.swing.JTextField();
        lblTenHang = new javax.swing.JLabel();
        lblSoLuongBanDau = new javax.swing.JLabel();
        txtDonGia = new javax.swing.JTextField();
        btnNhapHangSubmit = new javax.swing.JButton();
        btnXoaHang = new javax.swing.JButton();
        btnSuaThongTin = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        lblSoLuongBanDau1 = new javax.swing.JLabel();
        txtghichu = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        lblMaHangDangThaoTac = new javax.swing.JLabel();
        spnSoLuong = new javax.swing.JSpinner();
        lblSoLuongBanDau2 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnTrangChu = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Quản lý danh sách hàng hóa");
        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);

        tblHangHoa.setAutoCreateRowSorter(true);
        tblHangHoa.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        tblHangHoa.setForeground(new java.awt.Color(51, 51, 51));
        tblHangHoa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã hàng", "Tên hàng", "Số lượng tồn kho", "Đơn giá", "Hình ảnh", "Ghi chú"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHangHoa.setGridColor(new java.awt.Color(204, 204, 204));
        tblHangHoa.setRowHeight(35);
        tblHangHoa.setSelectionBackground(new java.awt.Color(207, 226, 255));
        tblHangHoa.setSelectionForeground(new java.awt.Color(0, 0, 102));
        tblHangHoa.setShowGrid(true);
        tblHangHoa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHangHoaMouseClicked(evt);
            }
        });
        tabs.setViewportView(tblHangHoa);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        lblImage.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        lblImage.setForeground(new java.awt.Color(51, 51, 51));
        lblImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblImage.setText("Click here to add photo");
        lblImage.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 204), 3));
        lblImage.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblImage.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblImage.setIconTextGap(10);
        lblImage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblImageMouseClicked(evt);
            }
        });

        lblMaHang.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        lblMaHang.setForeground(new java.awt.Color(0, 102, 204));
        lblMaHang.setText("Mã hàng:");

        txtMaHang.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        txtMaHang.setMargin(new java.awt.Insets(2, 16, 2, 16));
        txtMaHang.setOpaque(true);

        txtTenHang.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        txtTenHang.setMargin(new java.awt.Insets(2, 16, 2, 16));
        txtTenHang.setOpaque(true);
        txtTenHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenHangActionPerformed(evt);
            }
        });

        lblTenHang.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        lblTenHang.setForeground(new java.awt.Color(0, 102, 204));
        lblTenHang.setText("Tên hàng:");

        lblSoLuongBanDau.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        lblSoLuongBanDau.setForeground(new java.awt.Color(0, 102, 204));
        lblSoLuongBanDau.setText("Số lượng:");

        txtDonGia.setFont(new java.awt.Font("Montserrat", 1, 36)); // NOI18N
        txtDonGia.setForeground(new java.awt.Color(0, 51, 153));
        txtDonGia.setMargin(new java.awt.Insets(12, 16, 12, 16));
        txtDonGia.setOpaque(true);
        txtDonGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDonGiaActionPerformed(evt);
            }
        });

        btnNhapHangSubmit.setBackground(new java.awt.Color(202, 233, 255));
        btnNhapHangSubmit.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        btnNhapHangSubmit.setForeground(new java.awt.Color(0, 102, 204));
        btnNhapHangSubmit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/pms/icon/download_line.png"))); // NOI18N
        btnNhapHangSubmit.setText("Nhập hàng vào kho");
        btnNhapHangSubmit.setBorder(null);
        btnNhapHangSubmit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNhapHangSubmit.setIconTextGap(10);
        btnNhapHangSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhapHangSubmitActionPerformed(evt);
            }
        });

        btnXoaHang.setBackground(new java.awt.Color(202, 233, 255));
        btnXoaHang.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        btnXoaHang.setForeground(new java.awt.Color(0, 102, 204));
        btnXoaHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/pms/icon/folder_delete_line.png"))); // NOI18N
        btnXoaHang.setText("Xóa hàng khỏi kho");
        btnXoaHang.setBorder(null);
        btnXoaHang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnXoaHang.setIconTextGap(10);
        btnXoaHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaHangActionPerformed(evt);
            }
        });

        btnSuaThongTin.setBackground(new java.awt.Color(202, 233, 255));
        btnSuaThongTin.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        btnSuaThongTin.setForeground(new java.awt.Color(0, 102, 204));
        btnSuaThongTin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/pms/icon/edit_2_line.png"))); // NOI18N
        btnSuaThongTin.setText("Sửa thông tin hàng");
        btnSuaThongTin.setBorder(null);
        btnSuaThongTin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSuaThongTin.setIconTextGap(10);
        btnSuaThongTin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaThongTinActionPerformed(evt);
            }
        });

        btnLamMoi.setBackground(new java.awt.Color(202, 233, 255));
        btnLamMoi.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        btnLamMoi.setForeground(new java.awt.Color(0, 102, 204));
        btnLamMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/pms/icon/refresh_4_line.png"))); // NOI18N
        btnLamMoi.setText("Làm mới");
        btnLamMoi.setBorder(null);
        btnLamMoi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLamMoi.setIconTextGap(10);
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        lblSoLuongBanDau1.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        lblSoLuongBanDau1.setForeground(new java.awt.Color(0, 102, 204));
        lblSoLuongBanDau1.setText("Ghi chú:");

        txtghichu.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        txtghichu.setMargin(new java.awt.Insets(2, 16, 2, 16));
        txtghichu.setOpaque(true);
        txtghichu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtghichuActionPerformed(evt);
            }
        });

        jLabel1.setText("Mã hàng đang thao tác:");

        lblMaHangDangThaoTac.setText("<Mã hàng>");

        spnSoLuong.setFont(new java.awt.Font("Montserrat", 1, 36)); // NOI18N
        spnSoLuong.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        spnSoLuong.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnSoLuongStateChanged(evt);
            }
        });

        lblSoLuongBanDau2.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        lblSoLuongBanDau2.setForeground(new java.awt.Color(0, 102, 204));
        lblSoLuongBanDau2.setText("Đơn giá:");

        jLabel2.setFont(new java.awt.Font("Montserrat", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 153));
        jLabel2.setText("VNĐ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblMaHangDangThaoTac))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblImage, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnXoaHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblMaHang)
                                    .addComponent(lblTenHang)
                                    .addComponent(lblSoLuongBanDau)
                                    .addComponent(lblSoLuongBanDau1)
                                    .addComponent(lblSoLuongBanDau2))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTenHang, javax.swing.GroupLayout.PREFERRED_SIZE, 434, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtMaHang, javax.swing.GroupLayout.PREFERRED_SIZE, 434, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel2))
                                    .addComponent(txtghichu, javax.swing.GroupLayout.PREFERRED_SIZE, 434, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(spnSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnSuaThongTin, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(btnNhapHangSubmit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblMaHang)
                            .addComponent(txtMaHang, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTenHang)
                            .addComponent(txtTenHang, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(spnSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblSoLuongBanDau))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblSoLuongBanDau2))
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblSoLuongBanDau1)
                            .addComponent(txtghichu, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lblImage, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                .addComponent(btnNhapHangSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btnSuaThongTin, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoaHang, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblMaHangDangThaoTac))
                .addGap(78, 78, 78))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnLamMoi, btnSuaThongTin, btnXoaHang});

        btnTrangChu.setBackground(new java.awt.Color(202, 233, 255));
        btnTrangChu.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        btnTrangChu.setForeground(new java.awt.Color(0, 102, 204));
        btnTrangChu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/pms/icon/home_3_line.png"))); // NOI18N
        btnTrangChu.setText("Trang chủ");
        btnTrangChu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTrangChu.setIconTextGap(10);
        btnTrangChu.setOpaque(true);
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
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnTrangChu, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tabs, javax.swing.GroupLayout.PREFERRED_SIZE, 646, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(btnTrangChu, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tabs))
                .addGap(41, 41, 41))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblImageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImageMouseClicked
        JFileChooser jfc = new JFileChooser();
        int choose = jfc.showOpenDialog(this);
        if(choose==JFileChooser.APPROVE_OPTION){
            file = jfc.getSelectedFile();
        }
        lblImage.setText("");
        int newHeight = (lblImage.getWidth() * new ImageIcon(file.getAbsolutePath()).getImage().getHeight(null)) / new ImageIcon(file.getAbsolutePath()).getImage().getWidth(null);
        Image img = new ImageIcon(file.getAbsolutePath()).getImage().getScaledInstance(lblImage.getWidth(), newHeight, Image.SCALE_SMOOTH);
        lblImage.setIcon(new ImageIcon(img));
    }//GEN-LAST:event_lblImageMouseClicked

    private void txtTenHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenHangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenHangActionPerformed

    private void btnTrangChuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrangChuActionPerformed
        dispose();
        TrangChu trangChuJFrame = new TrangChu();
        trangChuJFrame.get_Data_From_DifFrame(truongPhong, maNV, tenNV, urlAnh);
        trangChuJFrame.setVisible(true);
    }//GEN-LAST:event_btnTrangChuActionPerformed

    private void txtDonGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDonGiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDonGiaActionPerformed

    private void btnNhapHangSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhapHangSubmitActionPerformed
        // TODO add your handling code here:
        if(lblMaHangDangThaoTac.getText().equals("<Mã hàng>")==false){
            JOptionPane.showMessageDialog(this, "Làm mới Form trước khi thêm!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            if(checkNaN()){
                JOptionPane.showMessageDialog(this, "Nhập đủ thông tin hàng!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } else {
                boolean checkExist = false;
                for(int i = 0 ; i < tblHangHoa.getRowCount() ; i++){
                    if(txtMaHang.getText().equals(tblHangHoa.getValueAt(i, 0).toString())){
                        checkExist=true;
                    }
                }
                if(checkExist==true){
                    JOptionPane.showMessageDialog(this, "Đã tồn tại mã hàng!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    insert();
                    clearForm();
                }
            }
        }
    }//GEN-LAST:event_btnNhapHangSubmitActionPerformed

    private void txtghichuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtghichuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtghichuActionPerformed

    private void tblHangHoaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHangHoaMouseClicked
        edit();
    }//GEN-LAST:event_tblHangHoaMouseClicked

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        // TODO add your handling code here:
        clearForm();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnXoaHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaHangActionPerformed
        try {
            delete();
            clearForm();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Không thể");
        }
        
    }//GEN-LAST:event_btnXoaHangActionPerformed

    private void btnSuaThongTinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaThongTinActionPerformed
        if(lblMaHangDangThaoTac.getText().equals("<Mã hàng>")==false){
            if(checkNaN()){
                JOptionPane.showMessageDialog(this, "Nhập đủ thông tin hàng!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } else {
                update();
                clearForm();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm muốn sửa!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnSuaThongTinActionPerformed

    private void spnSoLuongStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnSoLuongStateChanged
        
    }//GEN-LAST:event_spnSoLuongStateChanged

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuanLyDanhSachHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuanLyDanhSachHang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnNhapHangSubmit;
    private javax.swing.JButton btnSuaThongTin;
    private javax.swing.JButton btnTrangChu;
    private javax.swing.JButton btnXoaHang;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblImage;
    private javax.swing.JLabel lblMaHang;
    private javax.swing.JLabel lblMaHangDangThaoTac;
    private javax.swing.JLabel lblSoLuongBanDau;
    private javax.swing.JLabel lblSoLuongBanDau1;
    private javax.swing.JLabel lblSoLuongBanDau2;
    private javax.swing.JLabel lblTenHang;
    private javax.swing.JSpinner spnSoLuong;
    private javax.swing.JScrollPane tabs;
    private javax.swing.JTable tblHangHoa;
    private javax.swing.JTextField txtDonGia;
    private javax.swing.JTextField txtMaHang;
    private javax.swing.JTextField txtTenHang;
    private javax.swing.JTextField txtghichu;
    // End of variables declaration//GEN-END:variables

    private String String(Integer soLuong) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

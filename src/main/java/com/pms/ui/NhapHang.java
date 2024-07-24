/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.pms.ui;

import com.pms.dao.ProductsDAO;
import com.pms.dao.Products_Import_DAO;
import com.pms.entity.Products;
import com.pms.entity.Products_Import;
import com.pms.utils.XImage;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author MSI
 */
public class NhapHang extends javax.swing.JFrame {
    
    File file;
    Boolean truongPhong;
    String maNV, tenNV, urlAnh, nguoiNhapDangThaoTac;
    ProductsDAO dao=new ProductsDAO();
    Products_Import_DAO importdao=new Products_Import_DAO();
    JFileChooser filechooser = new JFileChooser();
    int row=0;
    int roww=0;
    int rowHangHoaSellected;
    Date ngayNhapDangThaoTac;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    
    /**
     * Creates new form Products_Manager
     */
    
    void init() {
        setLocationRelativeTo(null);
        filltable();
    }
    
    public NhapHang() {
        initComponents();
        setLayoutTable();
        init();
        setLocationRelativeTo(null);
        this.getContentPane().setBackground(new Color(255,255,255));
        this.setIconImage(XImage.getAppIcon());
    }
    
    public void setLayoutTable(){
        tblHangHoa.setShowGrid(true);
        tblHangHoa.setGridColor(new Color(227,227,227));
        JTableHeader titleHeader = tblHangHoa.getTableHeader();
        titleHeader.setFont(new Font("Montserrat", Font.BOLD, 13));
        titleHeader.setPreferredSize(new Dimension(titleHeader.getWidth(), 40));
        tblLichSu.setShowGrid(true);
        tblLichSu.setGridColor(new Color(227,227,227));
        JTableHeader titleHeader1 = tblLichSu.getTableHeader();
        titleHeader1.setFont(new Font("Montserrat", Font.BOLD, 13));
        titleHeader1.setPreferredSize(new Dimension(titleHeader1.getWidth(), 40));
    }
    
    public void get_Data_From_DifFrame(Boolean tp, String manv, String tennv, String urlanh){
        truongPhong = tp;
        maNV = manv;
        tenNV = tennv;
        urlAnh = urlanh;
        fillLichSuTable();
    }
    
    void chonanh() {
        if (filechooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = filechooser.getSelectedFile();
            XImage.save(file);
            ImageIcon icon = XImage.read(file.getName());

            // Lấy kích thước của JLabel (khung ảnh)
            int labelWidth = lblImage.getWidth();
            int labelHeight = lblImage.getHeight();

            // Lấy hình ảnh từ ImageIcon và chỉnh kích thước
            Image img = icon.getImage();
            Image scaledImg = img.getScaledInstance(labelWidth, labelHeight, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImg);

            // Thiết lập hình ảnh đã chỉnh kích thước vào JLabel
            lblImage.setIcon(scaledIcon);
            lblImage.setToolTipText(file.getName());
        }
    }
    
    Products getform(){
        Products pd=new Products();
        pd.setMaHang(lblMaHang.getText());
        pd.setTenHang(lblTenHang.getText());
        pd.setSoLuong(Integer.valueOf(txtSoLuongNhap.getText()));
        pd.setHinhAnh(file.getAbsolutePath());
        pd.setGhiChu(txtGhiChu.getText());
        return pd;
    }
    
    void setfrom(Products model) {
        lblMaHang.setText(model.getMaHang());
        lblTenHang.setText(model.getTenHang());
        txtGhiChu.setText(model.getGhiChu());
        if (!model.getHinhAnh().equals("")) {
            lblImage.setIcon(XImage.read(model.getHinhAnh()));
            lblImage.setToolTipText(model.getHinhAnh());
        }
    }
    
    void edit() {
        rowHangHoaSellected = tblHangHoa.getSelectedRow();
        file = new File(tblHangHoa.getValueAt(rowHangHoaSellected, 3).toString());
        lblMaHang.setText(tblHangHoa.getValueAt(rowHangHoaSellected, 0).toString());
        lblTenHang.setText(tblHangHoa.getValueAt(rowHangHoaSellected, 1).toString());
        lblImage.setText("");
        int newHeight = (lblImage.getWidth() * new ImageIcon(tblHangHoa.getValueAt(rowHangHoaSellected, 3).toString()).getImage().getHeight(null)) / new ImageIcon(tblHangHoa.getValueAt(rowHangHoaSellected, 3).toString()).getImage().getWidth(null);
        Image img = new ImageIcon(tblHangHoa.getValueAt(rowHangHoaSellected, 3).toString()).getImage().getScaledInstance(lblImage.getWidth(), newHeight, Image.SCALE_SMOOTH);
        lblImage.setIcon(new ImageIcon(img));
    }
    
    void insert() {
        Products model = getform();
        try {
            dao.insert(model);
            this.filltable();      
            chonanh();
        } catch (Exception e) {
        }
    }
    
    void filltable() {
        DefaultTableModel model = (DefaultTableModel) tblHangHoa.getModel();
        model.setRowCount(0);
        try {
            List<Products> list = dao.selectAll();
            for (Products pd : list) {
                Object[] row = {
                    pd.getMaHang(),
                    pd.getTenHang(),
                    pd.getSoLuong(),
                    pd.getHinhAnh(),
                    pd.getGhiChu()
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            System.out.println("filtable:"+e.toString());
        }
    }
    
    void fillLichSuTable() {
    DefaultTableModel modell = (DefaultTableModel) tblLichSu.getModel();
    modell.setRowCount(0);
    try {
        List<Products_Import> list = importdao.selectAll(maNV);
        for (Products_Import pi : list) {
            Object[] roww = {
                pi.getID(),
                pi.getMaHang(),
                sdf.format(pi.getNgayNhap()),
                pi.getNguoiNhap(),
                pi.getSoLuong(),
                pi.getGhiChu()
            };
            modell.addRow(roww);
        }
    } catch (Exception e) {
        System.out.println("fillLichSuTable: " + e.toString());
    }
}
    
    void nhaphang(){
            // Thêm bản ghi lịch sử nhập hàng
            Products_Import importHistory = new Products_Import();
            importHistory.setMaHang(lblMaHang.getText());
            importHistory.setNgayNhap(new Date()); // Lấy thời gian hiện tại
            importHistory.setSoLuong(Integer.valueOf(txtSoLuongNhap.getText()));
            importHistory.setNguoiNhap(maNV); // Giả định người nhập hàng
            importHistory.setGhiChu(txtGhiChu.getText());
            importdao.insert(importHistory); // Lưu vào database
            fillLichSuTable(); // Cập nhật dữ liệu vào JTable lịch sử nhập hàng
            filltable(); // Cập nhật dữ liệu vào JTable danh sách hàng
    }

    void clearform() {
        lblMaHang.setText("<Mã hàng>");
        lblTenHang.setText("<Tên hàng>");
        txtGhiChu.setText("");
        this.row = -1;
        lblImage.setText("<Photo of product>");
        lblImage.setIcon(null);
        txtSoLuongNhap.setText("");
        lblIdDangThaoTac.setText("<ID>");
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblHangHoa = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        lblImage = new javax.swing.JLabel();
        Jlabel = new javax.swing.JLabel();
        lblSoLuongBanDau = new javax.swing.JLabel();
        txtSoLuongNhap = new javax.swing.JTextField();
        btnNhapHangSubmit = new javax.swing.JButton();
        btnSuaThongTin = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        lblTenHang = new javax.swing.JLabel();
        Jlabel1 = new javax.swing.JLabel();
        lblMaHang = new javax.swing.JLabel();
        lblSoLuongBanDau1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtGhiChu = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        lblIdDangThaoTac = new javax.swing.JLabel();
        btnTrangChu = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblLichSu = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Nhập hàng");
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
                "Mã hàng", "Tên Hàng", "Số lượng", "Hình ảnh", "Ghi chú"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
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
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblHangHoaMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblHangHoa);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        lblImage.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        lblImage.setForeground(new java.awt.Color(51, 51, 51));
        lblImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblImage.setText("<Photo of product>");
        lblImage.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 204), 3));
        lblImage.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblImage.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblImage.setIconTextGap(10);
        lblImage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblImageMouseClicked(evt);
            }
        });

        Jlabel.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        Jlabel.setForeground(new java.awt.Color(0, 102, 204));
        Jlabel.setText("Tên hàng:");

        lblSoLuongBanDau.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        lblSoLuongBanDau.setForeground(new java.awt.Color(0, 102, 204));
        lblSoLuongBanDau.setText("Số lượng:");

        txtSoLuongNhap.setFont(new java.awt.Font("Montserrat", 1, 36)); // NOI18N
        txtSoLuongNhap.setForeground(new java.awt.Color(0, 51, 153));
        txtSoLuongNhap.setMargin(new java.awt.Insets(0, 16, 0, 16));
        txtSoLuongNhap.setOpaque(true);

        btnNhapHangSubmit.setBackground(new java.awt.Color(202, 233, 255));
        btnNhapHangSubmit.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        btnNhapHangSubmit.setForeground(new java.awt.Color(0, 102, 204));
        btnNhapHangSubmit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/pms/icon/download_line.png"))); // NOI18N
        btnNhapHangSubmit.setText("Nhập hàng");
        btnNhapHangSubmit.setBorder(null);
        btnNhapHangSubmit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNhapHangSubmit.setIconTextGap(10);
        btnNhapHangSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhapHangSubmitActionPerformed(evt);
            }
        });

        btnSuaThongTin.setBackground(new java.awt.Color(202, 233, 255));
        btnSuaThongTin.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        btnSuaThongTin.setForeground(new java.awt.Color(0, 102, 204));
        btnSuaThongTin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/pms/icon/edit_2_line.png"))); // NOI18N
        btnSuaThongTin.setText("Sửa thông tin nhập hàng");
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

        lblTenHang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblTenHang.setText("<Tên hàng>");

        Jlabel1.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        Jlabel1.setForeground(new java.awt.Color(0, 102, 204));
        Jlabel1.setText("Mã hàng:");

        lblMaHang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblMaHang.setText("<Mã hàng>");

        lblSoLuongBanDau1.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        lblSoLuongBanDau1.setForeground(new java.awt.Color(0, 102, 204));
        lblSoLuongBanDau1.setText("Ghi chú:");

        txtGhiChu.setColumns(20);
        txtGhiChu.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        txtGhiChu.setLineWrap(true);
        txtGhiChu.setRows(3);
        txtGhiChu.setMargin(new java.awt.Insets(16, 16, 16, 16));
        jScrollPane3.setViewportView(txtGhiChu);

        jLabel3.setText("ID lịch sử đang thao tác:");

        lblIdDangThaoTac.setText("<ID>");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(btnSuaThongTin, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnLamMoi, javax.swing.GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE))
                        .addComponent(btnNhapHangSubmit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(97, 97, 97)
                                .addComponent(lblMaHang))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Jlabel1)
                                    .addComponent(Jlabel)
                                    .addComponent(lblSoLuongBanDau)
                                    .addComponent(lblSoLuongBanDau1))
                                .addGap(22, 22, 22)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblTenHang)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblIdDangThaoTac))
                                    .addComponent(txtSoLuongNhap)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(52, 52, 52)
                        .addComponent(lblImage, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(Jlabel1)
                            .addComponent(lblMaHang))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(lblTenHang)
                            .addComponent(Jlabel))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSoLuongBanDau)
                            .addComponent(txtSoLuongNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblSoLuongBanDau1))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(lblIdDangThaoTac))
                        .addGap(56, 56, 56))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblImage, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63)))
                .addComponent(btnNhapHangSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSuaThongTin)
                    .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(58, 58, 58))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnLamMoi, btnSuaThongTin});

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

        tblLichSu.setAutoCreateRowSorter(true);
        tblLichSu.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        tblLichSu.setForeground(new java.awt.Color(51, 51, 51));
        tblLichSu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Mã hàng", "Ngày nhập", "Người nhập", "Số lượng", "Ghi chú"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblLichSu.setGridColor(new java.awt.Color(204, 204, 204));
        tblLichSu.setRowHeight(35);
        tblLichSu.setSelectionBackground(new java.awt.Color(207, 226, 255));
        tblLichSu.setSelectionForeground(new java.awt.Color(0, 0, 102));
        tblLichSu.setShowGrid(true);
        tblLichSu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblLichSuMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblLichSu);

        jLabel1.setText("Danh sách hàng:");

        jLabel2.setText("Lịch sử nhập hàng:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 708, Short.MAX_VALUE)
                                .addComponent(jScrollPane1)
                                .addComponent(btnTrangChu, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(58, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(btnTrangChu, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(17, 17, 17)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(35, 35, 35))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblImageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImageMouseClicked
        
    }//GEN-LAST:event_lblImageMouseClicked

    private void btnTrangChuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrangChuActionPerformed
        dispose();
        TrangChu trangChuJFrame = new TrangChu();
        trangChuJFrame.get_Data_From_DifFrame(truongPhong, maNV, tenNV, urlAnh);
        trangChuJFrame.setVisible(true);
    }//GEN-LAST:event_btnTrangChuActionPerformed

    private void tblHangHoaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHangHoaMousePressed
        
    }//GEN-LAST:event_tblHangHoaMousePressed

    private void btnNhapHangSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhapHangSubmitActionPerformed
        if(lblIdDangThaoTac.getText().equals("<ID>")==false){
            JOptionPane.showMessageDialog(this, "Làm mới Form trước khi nhập hàng!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            if(lblMaHang.getText().equals("<Mã hàng>")){
                JOptionPane.showMessageDialog(this, "Chọn hàng cần nhập kho!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } else {
                if(txtSoLuongNhap.getText().isEmpty()){
                    JOptionPane.showMessageDialog(this, "Không để trống số lượng!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    if(txtSoLuongNhap.getText().matches("^\\d+$")==false){
                        JOptionPane.showMessageDialog(this, "Số lượng phải là số dương");
                    } else {
                        nhaphang();
                        JOptionPane.showMessageDialog(this, "Nhập hàng thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        clearform();
                    }
                }
            }
        }
    }//GEN-LAST:event_btnNhapHangSubmitActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        clearform();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void tblHangHoaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHangHoaMouseClicked
        if (evt.getClickCount() == 1) {
            this.row = tblHangHoa.rowAtPoint(evt.getPoint());
            edit();
            lblIdDangThaoTac.setText("<ID>");
        }
    }//GEN-LAST:event_tblHangHoaMouseClicked

    private void btnSuaThongTinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaThongTinActionPerformed
        Integer soLuongHang = null;
        if(lblIdDangThaoTac.getText().equals("<ID>")){
            JOptionPane.showMessageDialog(this, "Chọn thông tin nhập hàng ở bảng lịch sử để sửa!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            if(txtSoLuongNhap.getText().isEmpty()){
                JOptionPane.showMessageDialog(this, "Không để trống số lượng!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } else {
                if(txtSoLuongNhap.getText().matches("^\\d+$")==false){
                        JOptionPane.showMessageDialog(this, "Số lượng phải là số dương");
                    } else {
                    soLuongHang = Integer.valueOf(txtSoLuongNhap.getText());
                    Products_Import products_Import = new Products_Import();
                    products_Import.setID(Integer.valueOf(lblIdDangThaoTac.getText()));
                    products_Import.setMaHang(lblMaHang.getText());
                    products_Import.setNgayNhap(ngayNhapDangThaoTac);
                    products_Import.setNguoiNhap(nguoiNhapDangThaoTac);
                    products_Import.setSoLuong(soLuongHang);
                    products_Import.setGhiChu(txtGhiChu.getText());
                    importdao.update(products_Import);
                    fillLichSuTable();
                    filltable();
                    JOptionPane.showMessageDialog(this, "Sửa thông tin nhập hàng thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    clearform();
                }
            }
        }
        
    }//GEN-LAST:event_btnSuaThongTinActionPerformed

    private void tblLichSuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblLichSuMouseClicked
        int rowSellected = tblLichSu.getSelectedRow();
        String tenHang = null, urlProduct = null;
        for(int i = 0; i < tblHangHoa.getRowCount(); i++){
            if(tblHangHoa.getValueAt(i, 0).toString().equals(tblLichSu.getValueAt(rowSellected, 1))){
                tenHang = tblHangHoa.getValueAt(i, 1).toString();
                urlProduct = tblHangHoa.getValueAt(i, 3).toString();
                break;
            }
        }
        lblMaHang.setText(tblLichSu.getValueAt(rowSellected, 1).toString());
        lblTenHang.setText(tenHang);
        txtSoLuongNhap.setText(tblLichSu.getValueAt(rowSellected, 4).toString());
        if(tblLichSu.getValueAt(rowSellected, 5)==null){
            txtGhiChu.setText("");
        } else {
            txtGhiChu.setText(tblLichSu.getValueAt(rowSellected, 5).toString());
        }
        lblImage.setText("");
        int newHeight = (lblImage.getWidth() * new ImageIcon(urlProduct).getImage().getHeight(null)) / new ImageIcon(urlProduct).getImage().getWidth(null);
        Image img = new ImageIcon(urlProduct).getImage().getScaledInstance(lblImage.getWidth(), newHeight, Image.SCALE_SMOOTH);
        lblImage.setIcon(new ImageIcon(img));
        lblIdDangThaoTac.setText(tblLichSu.getValueAt(rowSellected, 0).toString());
        try {
            ngayNhapDangThaoTac = sdf.parse(tblLichSu.getValueAt(rowSellected, 2).toString());
        } catch (ParseException ex) {
            Logger.getLogger(NhapHang.class.getName()).log(Level.SEVERE, null, ex);
        }
        nguoiNhapDangThaoTac = tblLichSu.getValueAt(rowSellected, 3).toString();
    }//GEN-LAST:event_tblLichSuMouseClicked

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
            java.util.logging.Logger.getLogger(NhapHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NhapHang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Jlabel;
    private javax.swing.JLabel Jlabel1;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnNhapHangSubmit;
    private javax.swing.JButton btnSuaThongTin;
    private javax.swing.JButton btnTrangChu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblIdDangThaoTac;
    private javax.swing.JLabel lblImage;
    private javax.swing.JLabel lblMaHang;
    private javax.swing.JLabel lblSoLuongBanDau;
    private javax.swing.JLabel lblSoLuongBanDau1;
    private javax.swing.JLabel lblTenHang;
    private javax.swing.JTable tblHangHoa;
    private javax.swing.JTable tblLichSu;
    private javax.swing.JTextArea txtGhiChu;
    private javax.swing.JTextField txtSoLuongNhap;
    // End of variables declaration//GEN-END:variables

    private void fillTableImportHistory() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

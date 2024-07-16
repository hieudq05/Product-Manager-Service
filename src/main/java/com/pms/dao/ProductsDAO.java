package com.pms.dao;

import com.pms.entity.Products;
import com.pms.utils.Connector;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductsDAO extends pmsDAO<Products, String>{
    final String insert = "INSERT INTO Products(mahang, tenhang, soluong, dongia, hinhanh, ghichu) VALUES (?, ?, ?, ?, ?, ?)";
    final String update = """
                          UPDATE Products 
                          SET tenhang = ?, soluong = ?, dongia = ?, hinhanh = ?, ghichu = ?
                          WHERE mahang = ?""";
    final String delete = "DELETE Products WHERE mahang = ?";
    final String selectAll = "SELECT * FROM Products";
    final String selectByID = "SELECT * FROM Products WHERE mahang = ?";

    @Override
    public void insert(Products entity) {
        Connector.update(insert, entity.getMaHang(), entity.getTenHang(), entity.getSoLuong(), entity.getDonGia(), entity.getHinhAnh(), entity.getGhiChu());
    }

    @Override
    public void update(Products entity) {
        Connector.update(update, entity.getTenHang(), entity.getSoLuong(), entity.getDonGia(), entity.getHinhAnh(), entity.getGhiChu(), entity.getMaHang());
    }

    @Override
    public void delete(String id) {
        Connector.update(delete, id);
    }

    @Override
    public List<Products> selectAll() {
        List<Products> list = new ArrayList<>();
        ResultSet rs = Connector.query(selectAll);
        try {
            while (rs.next()) {
                Products pd = new Products();
                pd.setMaHang(rs.getString(1));
                pd.setTenHang(rs.getNString(2));
                pd.setSoLuong(rs.getInt(4));
                pd.setDonGia(rs.getInt(3));
                pd.setHinhAnh(rs.getString(5));
                pd.setGhiChu(rs.getNString(6));
                list.add(pd);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public Products selectByID(String id) {
        Products pd = new Products();
        ResultSet rs = Connector.query(selectByID, id);
        try {
            rs.next();
            pd.setMaHang(rs.getString(1));
            pd.setTenHang(rs.getNString(2));
            pd.setDonGia(rs.getInt(3));
            pd.setSoLuong(rs.getInt(4));
            pd.setHinhAnh(rs.getNString(5));
            pd.setGhiChu(rs.getNString(6));
        } catch (SQLException ex) {
            Logger.getLogger(ProductsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pd;
    }
    
}

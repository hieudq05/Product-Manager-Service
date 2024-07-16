package com.pms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.pms.entity.Products_Import;
import com.pms.utils.Connector;

public class Products_Import_DAO extends pmsDAO<Products_Import, Integer>{
	
	String insert = "INSERT INTO Products_import(mahang, ngaynhap, nguoinhap, soluong, ghichu) VALUES (?, ?, ?, ?, ?)";
	
	String update = "UPDATE Products_import SET mahang = ?, ngaynhap = ?, nguoinhap = ?, soluong = ?, ghichu = ? WHERE ID = ?";
	
	String delete = "DELETE Products_import WHERE ID = ?";
	
	String selectAll = "SELECT * FROM Products_import WHERE nguoinhap = ?";
	
	String selectByID = "SELECT * FROM Products_import WHERE ID = ?";
	
	String selectByNgayNhap = "SELECT * FROM Products_import WHERE ngaynhap = ?";
	
	String selectByNguoiNhap = "SELECT * FROM Products_import WHERE nguoinhap = ?";

	@Override
	public void insert(Products_Import entity) {
		Connector.update(insert, entity.getMaHang(), entity.getNgayNhap(), entity.getNguoiNhap(), entity.getSoLuong(), entity.getGhiChu());
	}

	@Override
	public void update(Products_Import entity) {
		Connector.update(update, entity.getMaHang(), entity.getNgayNhap(), entity.getNguoiNhap(), entity.getSoLuong(), entity.getGhiChu(), entity.getID());
		
	}

	@Override
	public void delete(Integer id) {
		Connector.update(delete, id);
		
	}

	public List<Products_Import> selectAll(String nguoinhap) {
		List<Products_Import> list = new ArrayList<>();
		ResultSet rs = Connector.query(selectAll, nguoinhap);
		try {
			while (rs.next()) {
				Products_Import pImport = new Products_Import();
				pImport.setID(rs.getInt(1));
				pImport.setMaHang(rs.getString(2));
				pImport.setNgayNhap(rs.getDate(3));
				pImport.setNguoiNhap(rs.getString(4));
				pImport.setSoLuong(rs.getInt(5));
                                pImport.setGhiChu(rs.getNString(6));
				list.add(pImport);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Products_Import selectByID(Integer id) {
		Products_Import pImport = new Products_Import();
		ResultSet rs = Connector.query(selectByID, id);
		try {
			rs.next();
			pImport.setID(rs.getInt(1));
			pImport.setMaHang(rs.getNString(2));
			pImport.setNgayNhap(rs.getDate(3));
			pImport.setNguoiNhap(rs.getNString(4));
			pImport.setSoLuong(rs.getInt(5));
                        pImport.setGhiChu(rs.getNString(6));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pImport;
	}

    @Override
    public List<Products_Import> selectAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}

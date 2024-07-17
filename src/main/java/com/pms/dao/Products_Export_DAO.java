package com.pms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.pms.utils.Connector;
import com.pms.entity.Products_Export;

public class Products_Export_DAO extends pmsDAO<Products_Export, Integer>{
	
	String insert = "INSERT INTO Products_export(mahang, ngayxuat, nguoixuat, soluong, ghichu) VALUES (?, ?, ?, ?, ?)";
	
	String update = "UPDATE Products_export SET mahang = ?, ngayxuat = ?, nguoixuat = ?, soluong = ?, ghichu = ? WHERE ID = ?";
	
	String delete = "DELETE Products_export WHERE ID = ?";
	
	String selectAll = "SELECT * FROM Products_export WHERE nguoixuat = ?";
	
	String selectByID = "SELECT * FROM Products_export WHERE ID = ?";
	
	String selectByNgayXuat = "SELECT * FROM Products_export WHERE ngayxuat = ?";
	
	String selectByNguoiXuat = "SELECT * FROM Products_export WHERE nguoixuat = ?";

	@Override
	public void insert(Products_Export entity) {
		Connector.update(insert, entity.getMaHang(), entity.getNgayXuat(), entity.getNguoiXuat(), entity.getSoLuong(), entity.getGhiChu());
	}

	@Override
	public void update(Products_Export entity) {
		Connector.update(update, entity.getMaHang(), entity.getNgayXuat(), entity.getNguoiXuat(), entity.getSoLuong(), entity.getGhiChu(), entity.getID());
		
	}

	@Override
	public void delete(Integer id) {
		Connector.update(delete, id);
		
	}

	@Override
	public List<Products_Export> selectAll() {
		List<Products_Export> list = new ArrayList<>();
		ResultSet rs = Connector.query(selectAll);
		try {
			while (rs.next()) {
				Products_Export pExport = new Products_Export();
				pExport.setID(rs.getInt(1));
				pExport.setMaHang(rs.getString(2));
				pExport.setNgayXuat(rs.getDate(3));
				pExport.setNguoiXuat(rs.getString(4));
				pExport.setSoLuong(rs.getInt(5));
                                pExport.setGhiChu(rs.getNString(6));
				list.add(pExport);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Products_Export selectByID(Integer id) {
		Products_Export pExport = new Products_Export();
		ResultSet rs = Connector.query(selectByID, id);
		try {
			rs.next();
			pExport.setID(rs.getInt(1));
			pExport.setMaHang(rs.getNString(2));
			pExport.setNgayXuat(rs.getDate(3));
			pExport.setNguoiXuat(rs.getNString(4));
			pExport.setSoLuong(rs.getInt(5));
                        pExport.setGhiChu(rs.getNString(6));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pExport;
	}
        
        public List<Products_Export> selectAll(String nguoinhap) {
		List<Products_Export> list = new ArrayList<>();
		ResultSet rs = Connector.query(selectAll, nguoinhap);
		try {
			while (rs.next()) {
				Products_Export pImport = new Products_Export();
				pImport.setID(rs.getInt(1));
				pImport.setMaHang(rs.getString(2));
				pImport.setNgayXuat(rs.getDate(3));
				pImport.setNguoiXuat(rs.getString(4));
				pImport.setSoLuong(rs.getInt(5));
                                pImport.setGhiChu(rs.getNString(6));
				list.add(pImport);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}

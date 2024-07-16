package com.pms.dao;

import com.pms.entity.History_IP_EP;
import com.pms.utils.Connector;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class History_Import_Export_DAO extends pmsDAO<History_IP_EP, Integer> {

    String selectAll = "SELECT * FROM History_import_export";
    String selectHistory = """
                           SELECT * 
                           FROM History_import_export
                           WHERE (mahang = ? OR ? IS NULL)
                           AND (ngaythaotac = ? OR ? IS NULL)
                           AND (nguoithaotac = ? OR ? IS NULL)""";

    @Override
    public List<History_IP_EP> selectAll() {
        List<History_IP_EP> list = new ArrayList<>();
        ResultSet rs = Connector.query(selectAll);
        try {
            while (rs.next()) {
                History_IP_EP history_IP_EP = new History_IP_EP();
                history_IP_EP.setID(rs.getInt(1));
                history_IP_EP.setNgayThaoTac(rs.getDate(2));
                history_IP_EP.setThaoTac(rs.getString(3));
                history_IP_EP.setMaHang(rs.getString(4));
                history_IP_EP.setTenHang(rs.getString(5));
                history_IP_EP.setDonGia(rs.getInt(6));
                history_IP_EP.setSoLuong(rs.getInt(7));
                history_IP_EP.setNguoiThaoTac(rs.getString(8));
                history_IP_EP.setSoLuongBanDau(rs.getInt(9));
                history_IP_EP.setSoLuongConLai(rs.getInt(10));
                list.add(history_IP_EP);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public List<String> select(String object){
        List<String> list = new ArrayList<>();
        ResultSet rs = Connector.query("SELECT DISTINCT "+ object +" FROM History_import_export");
        try {
            while (rs.next()) {
                list.add(rs.getObject(1).toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    } 
    
    public List<History_IP_EP> selectHistory(String mahang, String ngaythaotac, String nguoithaotac){
        List<History_IP_EP> list = new ArrayList<>();
        ResultSet rs = Connector.query(selectHistory, mahang, mahang, ngaythaotac, ngaythaotac, nguoithaotac, nguoithaotac);
        try {
            while (rs.next()) {
                History_IP_EP history_IP_EP = new History_IP_EP();
                history_IP_EP.setID(rs.getInt(1));
                history_IP_EP.setNgayThaoTac(rs.getDate(2));
                history_IP_EP.setThaoTac(rs.getString(3));
                history_IP_EP.setMaHang(rs.getString(4));
                history_IP_EP.setTenHang(rs.getString(5));
                history_IP_EP.setDonGia(rs.getInt(6));
                history_IP_EP.setSoLuong(rs.getInt(7));
                history_IP_EP.setNguoiThaoTac(rs.getString(8));
                history_IP_EP.setSoLuongBanDau(rs.getInt(9));
                history_IP_EP.setSoLuongConLai(rs.getInt(10));
                list.add(history_IP_EP);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public History_IP_EP selectByID(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(History_IP_EP entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(History_IP_EP entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pms.dao;
import com.pms.entity.History_import_export;
import com.pms.utils.Connector;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author GIGABYTE
 */
public class History_import_exportDAO extends pmsDAO<History_import_export, String>{
    final String INSERT_SQL ="INSERT INTO History_import_export(maHang,tenhang,soLuong,soluongbandau,soluongtonkho) values (?,?,?,?,?)";
    final String UPDATE_SQL ="UPDATE History_import_export set tenhang=?,soLuong=?,soluongbandau=?,soluongtonkho=? WHERE maHang =?";
    final String DELETE_SQL ="DELETE FROM History_import_export WHERE maHang =?";
    final String SELECT_ALL_SQL ="SELECT ID,maHang,tenhang,soluongbandau,soluongtonkho FROM History_import_export";
    final String SELECT_BY_ID_SQL ="SELECT * FROM History_import_export WHERE tenhang = ?";
    final String SELECT_BY_TEN_HANG_SQL = "SELECT * FROM History_import_export WHERE tenhang = ?"; 
    final String SELECT_BY_SAN_PHAM_KHO = "SELECT COUNT(tenhang) from History_import_export "; 
    final String SELECT_BY_SLBD = "SELECT sum(soluongbandau) from History_import_export "; 
    final String SELECT_BY_SLTK = "SELECT sum(soluongtonkho) from History_import_export "; 

    @Override
    public void insert(History_import_export entity) {
     Connector.update(INSERT_SQL, entity.getID(),entity.getMaHang(),entity.getTenhang(),entity.getSoluongbandau(),entity.getSoluongtonkho());

    }

    @Override
    public void update(History_import_export entity) {
     Connector.update(UPDATE_SQL, entity.getID(),entity.getMaHang(),entity.getTenhang(),entity.getSoluongbandau(),entity.getSoluongtonkho());
    }

    @Override
    public void delete(String id) {
        Connector.update(DELETE_SQL, id);
    }

    @Override
    public List<History_import_export> selectAll() {
         return  selectBySql(SELECT_ALL_SQL);   
    }

    public List<History_import_export> selectByTenHang(String tenHang) {
         return  selectBySql(SELECT_BY_TEN_HANG_SQL,tenHang);   
    }
    public Integer selectSPKho() {
         try {
             ResultSet rs = Connector.query(SELECT_BY_SAN_PHAM_KHO);  
             rs.next();
         return rs.getInt(1);
         } catch (Exception e) {
             e.printStackTrace();
         }
         return -1;
     }
    public Integer selectSLBD() {
         try {
             ResultSet rs = Connector.query(SELECT_BY_SLBD);  
             rs.next();
         return rs.getInt(1);
         } catch (Exception e) {
             e.printStackTrace();
         }
         return -1;
     }
    public Integer selectSLTK() {
         try {
             ResultSet rs = Connector.query(SELECT_BY_SLTK);  
             rs.next();
         return rs.getInt(1);
         } catch (Exception e) {
             e.printStackTrace();
         }
         return -1;
     }
    @Override
    public History_import_export selectByID(String id) {
        List<History_import_export> list= selectBySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

     public List<History_import_export> selectBySql(String sql, Object... args) {
            List<History_import_export> list = new ArrayList<>();
                try {
                    ResultSet rs = Connector.query(sql, args);
                    while (rs.next()) {
                        History_import_export thongke = new History_import_export();
			thongke.setID(rs.getInt("ID"));
			thongke.setMaHang(rs.getString("maHang"));
                        thongke.setTenhang(rs.getString("tenhang"));
			thongke.setSoluongbandau(rs.getInt("soluongbandau"));
			thongke.setSoluongtonkho(rs.getInt("soluongtonkho"));
			list.add(thongke);  
             }
        } catch (Exception e) {
            e.printStackTrace();
        } 
         return list;
    }
     
}

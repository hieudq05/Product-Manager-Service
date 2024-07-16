package com.pms.dao;

import com.pms.entity.Users;
import com.pms.utils.Connector;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsersDAO extends pmsDAO<Users, String>{
    final String insert = "INSERT INTO Users(manv, matkhau, tennv, nguoithem, vaitro, duongdananh) VALUES(?, ?, ?, ?, ?, ?)";
    final String delete = "DELETE FROM Users WHERE manv = ?";
    final String update = """
                          UPDATE Users
                          SET matkhau = ?, tennv = ?, ngaythem = ?, nguoithem = ?, vaitro = ?, duongdananh = ?
                          WHERE manv = ?""";
    final String selectAll = "SELECT * FROM Users";
    final String selectByID = "SELECT * FROM Users WHERE manv = ?";

    @Override
    public void insert(Users entity) {
        Connector.update(insert, entity.getMaNV(), entity.getMatkhau(), entity.getTenNV(), entity.getNguoiThem(), entity.getVaiTro(), entity.getDuongDanAnh());
    }

    @Override
    public void update(Users entity) {
        Connector.update(update, entity.getMatkhau(), entity.getTenNV(), entity.getNgayThem(), entity.getNguoiThem(), entity.getVaiTro(), entity.getDuongDanAnh(), entity.getMaNV());
    }
    @Override
    public void delete(String id) {
        Connector.update(delete, id);
    }

    @Override
    public List<Users> selectAll() {
        List<Users> list = new ArrayList<>();
        ResultSet rs = Connector.query(selectAll);
        try {
            while (rs.next()) {
                Users us = new Users();
                us.setMaNV(rs.getString(1));
                us.setTenNV(rs.getNString(2));
                us.setMatkhau(rs.getNString(3));
                us.setNgayThem(rs.getDate(4));
                us.setNguoiThem(rs.getString(5));
                us.setVaiTro(rs.getBoolean(6));
                us.setDuongDanAnh(rs.getNString(7));
                list.add(us);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public Users selectByID(String id) {
        Users us = new Users();
        ResultSet rs = Connector.query(selectByID, id);
        try {
            while(rs.next()){
                us.setMaNV(rs.getString(1));
                us.setTenNV(rs.getNString(2));
                us.setMatkhau(rs.getNString(3));
                us.setNgayThem(rs.getDate(4));
                us.setNguoiThem(rs.getString(5));
                us.setVaiTro(rs.getBoolean(6));
                us.setDuongDanAnh(rs.getNString(7));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return us;
    }
}

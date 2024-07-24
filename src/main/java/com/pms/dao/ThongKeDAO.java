package com.pms.dao;

import com.pms.utils.Connector;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ThongKeDAO {
    String sellectInto_DoanhThu_ThongKe = """
                                          SELECT nhap.thang, nhap.ngaybatdau, nhap.ngayketthuc, tongtienxuat, tongtiennhap, (tongtienxuat-tongtiennhap) AS 'tongcuoi'
                                          FROM (
                                          SELECT DATEPART(MONTH, ngaythaotac) AS 'thang',
                                          DATEFROMPARTS(DATEPART(YEAR, ngaythaotac),DATEPART(MONTH, ngaythaotac), 1) AS 'ngaybatdau',
                                          EOMONTH(ngaythaotac) AS 'ngayketthuc',
                                          SUM(dongia*soluong) AS 'tongtiennhap'
                                          FROM History_import_export
                                          WHERE thaotac=1
                                          GROUP BY DATEPART(MONTH, ngaythaotac), thaotac, DATEFROMPARTS(DATEPART(YEAR, ngaythaotac), DATEPART(MONTH, ngaythaotac), 1),
                                          EOMONTH(ngaythaotac)
                                          ) AS xuat
                                          JOIN (
                                          SELECT DATEPART(MONTH, ngaythaotac) AS 'thang',
                                          DATEFROMPARTS(DATEPART(YEAR, ngaythaotac),DATEPART(MONTH, ngaythaotac), 1) AS 'ngaybatdau',
                                          EOMONTH(ngaythaotac) AS 'ngayketthuc',
                                          SUM(dongia*soluong) AS 'tongtienxuat'
                                          FROM History_import_export
                                          WHERE thaotac=0
                                          GROUP BY DATEPART(MONTH, ngaythaotac), thaotac, DATEFROMPARTS(DATEPART(YEAR, ngaythaotac), DATEPART(MONTH, ngaythaotac), 1),
                                          EOMONTH(ngaythaotac)
                                          ) as nhap ON nhap.thang = xuat.thang""";
    String sellectBy_Thang_ThongKe = """
                                          SELECT nhap.thang, nhap.ngaybatdau, nhap.ngayketthuc, tongtienxuat, tongtiennhap, (tongtienxuat-tongtiennhap) AS 'tongcuoi'
                                          FROM (
                                          SELECT DATEPART(MONTH, ngaythaotac) AS 'thang',
                                          DATEFROMPARTS(DATEPART(YEAR, ngaythaotac),DATEPART(MONTH, ngaythaotac), 1) AS 'ngaybatdau',
                                          EOMONTH(ngaythaotac) AS 'ngayketthuc',
                                          SUM(dongia*soluong) AS 'tongtiennhap'
                                          FROM History_import_export
                                          WHERE thaotac=1
                                          GROUP BY DATEPART(MONTH, ngaythaotac), thaotac, DATEFROMPARTS(DATEPART(YEAR, ngaythaotac), DATEPART(MONTH, ngaythaotac), 1),
                                          EOMONTH(ngaythaotac)
                                          ) AS xuat
                                          JOIN (
                                          SELECT DATEPART(MONTH, ngaythaotac) AS 'thang',
                                          DATEFROMPARTS(DATEPART(YEAR, ngaythaotac),DATEPART(MONTH, ngaythaotac), 1) AS 'ngaybatdau',
                                          EOMONTH(ngaythaotac) AS 'ngayketthuc',
                                          SUM(dongia*soluong) AS 'tongtienxuat'
                                          FROM History_import_export
                                          WHERE thaotac=0
                                          GROUP BY DATEPART(MONTH, ngaythaotac), thaotac, DATEFROMPARTS(DATEPART(YEAR, ngaythaotac), DATEPART(MONTH, ngaythaotac), 1),
                                          EOMONTH(ngaythaotac)
                                          ) as nhap ON nhap.thang = xuat.thang
                                          WHERE nhap.thang = ? AND """;
    
    public List<Object[]> selectAllDoanhThu_ThongKe(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        List<Object[]> list = new ArrayList<>();
        ResultSet rs = Connector.query(sellectInto_DoanhThu_ThongKe);
        try {
            while(rs.next()){
                Object[] doanhThu = {
                    sdf.format(rs.getDate(2)),
                    sdf.format(rs.getDate(3)),
                    rs.getInt(4),
                    rs.getInt(5),
                    rs.getInt(6)
                };
                list.add(doanhThu);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ThongKeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public List<Object[]> selectBy_Thang_ThongKe(Integer month){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        List<Object[]> list = new ArrayList<>();
        ResultSet rs = Connector.query(sellectBy_Thang_ThongKe, month);
        try {
            while(rs.next()){
                Object[] doanhThu = {
                    sdf.format(rs.getDate(2)),
                    sdf.format(rs.getDate(3)),
                    rs.getInt(4),
                    rs.getInt(5),
                    rs.getInt(6)
                };
                list.add(doanhThu);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ThongKeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}

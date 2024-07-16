package com.pms.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Products_Import {
    private Integer ID;
    private String maHang;
    private Date ngayNhap;
    private String nguoiNhap;
    private Integer soLuong;
    private String ghiChu;
}

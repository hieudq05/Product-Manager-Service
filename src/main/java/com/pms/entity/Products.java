package com.pms.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Products {
    private String maHang;
    private String tenHang;
    private Integer soLuong;
    private String hinhAnh;   
    private String ghiChu;
    private Integer donGia;
}

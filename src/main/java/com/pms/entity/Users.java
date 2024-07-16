package com.pms.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Users {
    private String maNV;
    private String matkhau;
    private String tenNV;
    private Date ngayThem;
    private String nguoiThem;
    private Boolean vaiTro;
    private String duongDanAnh;
}

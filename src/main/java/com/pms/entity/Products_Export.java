package com.pms.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Products_Export {
    private Integer ID;
    private String maHang;
    private Date ngayXuat;
    private String nguoiXuat;
    private Integer soLuong;    
    private String ghiChu;
}

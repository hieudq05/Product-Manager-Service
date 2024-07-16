
package com.pms.entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class History_IP_EP {
    private Integer ID;
    private Date ngayThaoTac;
    private String thaoTac;
    private String maHang;
    private String tenHang;
    private Integer soLuong;
    private String nguoiThaoTac;
    private Integer soLuongBanDau;
    private Integer soLuongConLai;
    private Integer donGia;
}

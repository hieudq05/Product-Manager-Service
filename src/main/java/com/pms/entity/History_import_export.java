/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pms.entity;

/**
 *
 * @author GIGABYTE
 */
public class History_import_export {
    private int ID;
    private String maHang;
    private String tenhang;
    private int soLuong;
    private int soluongbandau;
    private int soluongtonkho;

    public History_import_export() {
    }

    public History_import_export(int ID, String maHang, String tenhang, int soLuong, int soluongbandau, int soluongtonkho) {
        this.ID = ID;
        this.maHang = maHang;
        this.tenhang = tenhang;
        this.soLuong = soLuong;
        this.soluongbandau = soluongbandau;
        this.soluongtonkho = soluongtonkho;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getMaHang() {
        return maHang;
    }

    public void setMaHang(String maHang) {
        this.maHang = maHang;
    }

    public String getTenhang() {
        return tenhang;
    }

    public void setTenhang(String tenhang) {
        this.tenhang = tenhang;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getSoluongbandau() {
        return soluongbandau;
    }

    public void setSoluongbandau(int soluongbandau) {
        this.soluongbandau = soluongbandau;
    }

    public int getSoluongtonkho() {
        return soluongtonkho;
    }

    public void setSoluongtonkho(int soluongtonkho) {
        this.soluongtonkho = soluongtonkho;
    }

    @Override
    public String toString() {
        return  tenhang ;
    }

   
}

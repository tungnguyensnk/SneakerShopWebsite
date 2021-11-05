package com.tung.projectdb.model;

import lombok.Data;

import java.util.Map;

@Data
public class DonHang {
    Map<Item, Integer> donHang;
    String trangThaiDon;
    String ngayDat;
    String ngayHenGiao;
    int tongTien;

    public DonHang(Map<Item, Integer> donHang, String trangThaiDon, String ngayDat, String ngayHenGiao) {
        this.donHang = donHang;
        this.trangThaiDon = trangThaiDon;
        this.ngayDat = ngayDat;
        this.ngayHenGiao = ngayHenGiao;
        tongTien = 0;
        donHang.forEach((item, soLuong) -> tongTien+=(item.getSoLuong()>soLuong) ? item.getGia()*soLuong : item.getGia()*item.getSoLuong());
    }
}

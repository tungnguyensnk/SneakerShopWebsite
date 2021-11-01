package com.tung.projectdb.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class KhachHang {
    String ten;
    String sdt;
    String diaChi;
    Map<Integer, Integer> donHang;
    String trangThaiDon;
    String ngayDat;
    String ngayHenGiao;
    
}

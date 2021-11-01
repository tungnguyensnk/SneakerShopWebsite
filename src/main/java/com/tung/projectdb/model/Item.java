package com.tung.projectdb.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Item {
    String linkAnh;
    String ten;
    String moTa;
    int maSanPham;
    int gia;
    int soLuong;

}

package com.tung.projectdb.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "sanpham")
@Entity
@Getter
@Setter
public class Item {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "gia")
    private Integer gia;

    @Column(name = "soluong")
    private Integer soLuong;

    @Column(name = "ten", length = 100)
    private String ten;

    @Column(name = "mota", length = 1000)
    private String moTa;

    @Column(name = "linkanh", length = 100)
    private String linkAnh;

}
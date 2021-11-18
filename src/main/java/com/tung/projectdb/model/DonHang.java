package com.tung.projectdb.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Table(name = "donhang")
@Entity
@Getter
@Setter
public class DonHang {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "trangthaidon", length = 50)
    private String trangthaidon;

    @Column(name = "ngaydat")
    private Date ngaydat;

    @Column(name = "ngayhengiao")
    private Date ngayhengiao;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private TaiKhoan user;

    @Transient
    int total;

    @Transient
    LinkedList<Chitietdon> list;
}
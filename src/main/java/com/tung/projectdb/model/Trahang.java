package com.tung.projectdb.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Table(name = "trahang")
@Entity
@Getter
@Setter
public class Trahang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private DonHang order;

    @Column(name = "trangthai", length = 50)
    private String trangthai;

    @Column(name = "ngaytao")
    private Date ngaytao;
}
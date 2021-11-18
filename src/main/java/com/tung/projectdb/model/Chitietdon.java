package com.tung.projectdb.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "chitietdon")
@Entity
@Getter
@Setter
public class Chitietdon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private DonHang order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Item product;

    @Column(name = "soluong")
    private Integer soluong;
}
package com.tung.projectdb.model;

import javax.persistence.*;

@Table(name = "nhanxet")
@Entity
public class Nhanxet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Item product;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private TaiKhoan user;

    @Column(name = "noidung", length = 400)
    private String noidung;

    @Column(name = "sao")
    private Integer sao;

    @ManyToOne
    @JoinColumn(name = "chitietdon_id")
    private Chitietdon chitietdon;

    public Chitietdon getChitietdon() {
        return chitietdon;
    }

    public void setChitietdon(Chitietdon chitietdon) {
        this.chitietdon = chitietdon;
    }

    public Integer getSao() {
        return sao;
    }

    public void setSao(Integer sao) {
        this.sao = sao;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public TaiKhoan getUser() {
        return user;
    }

    public void setUser(TaiKhoan user) {
        this.user = user;
    }

    public Item getProduct() {
        return product;
    }

    public void setProduct(Item product) {
        this.product = product;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
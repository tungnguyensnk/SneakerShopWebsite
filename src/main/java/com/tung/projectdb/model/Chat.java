package com.tung.projectdb.model;

import javax.persistence.*;

@Table(name = "chat")
@Entity
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "noidung", length = 500)
    private String noidung;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private TaiKhoan user;

    public TaiKhoan getUser() {
        return user;
    }

    public void setUser(TaiKhoan user) {
        this.user = user;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
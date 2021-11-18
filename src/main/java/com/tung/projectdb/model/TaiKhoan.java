package com.tung.projectdb.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.codec.digest.DigestUtils;

import javax.persistence.*;

@Table(name = "taikhoan")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class TaiKhoan {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user", nullable = false, length = 50)
    private String user;

    @Column(name = "pass", nullable = false, length = 50)
    private String pass;

    @Column(name = "secretkey", nullable = false, length = 100)
    private String secretkey;

    @Column(name = "ten", length = 100)
    private String ten;

    @Column(name = "sdt")
    private Integer sdt;

    @Column(name = "diachi", length = 200)
    private String diaChi;

    public TaiKhoan(String user, String pass, String ten, Integer sdt, String diaChi) {
        this.user = user;
        this.pass = pass;
        this.ten = ten;
        this.sdt = sdt;
        this.diaChi = diaChi;
        this.secretkey = DigestUtils.sha256Hex(user + pass);
    }
}
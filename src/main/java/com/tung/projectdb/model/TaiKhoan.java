package com.tung.projectdb.model;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.LinkedList;
import java.util.List;

public class TaiKhoan {
    @Getter
    String user;
    @Setter
    @Getter
    String pass;

    @Setter
    @Getter
    String key;

    @Setter
    @Getter
    String ten;

    @Setter
    @Getter
    String sdt;

    @Setter
    @Getter
    String diaChi;

    @Getter
    @Setter
    List<DonHang> donHangList = new LinkedList<>();
    public TaiKhoan(String user, String pass) {
        this.user = user;
        this.pass = pass;
        this.key = DigestUtils.sha256Hex(user + pass);
    }

    public TaiKhoan(String user, String pass, String ten, String sdt, String diaChi) {
        this.user = user;
        this.pass = pass;
        this.ten = ten;
        this.sdt = sdt;
        this.diaChi = diaChi;
        this.key = DigestUtils.sha256Hex(user + pass);
    }
}

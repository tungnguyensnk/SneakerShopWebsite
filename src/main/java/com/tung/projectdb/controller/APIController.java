package com.tung.projectdb.controller;

import com.tung.projectdb.model.Data;
import com.tung.projectdb.model.DonHang;
import com.tung.projectdb.model.Item;
import com.tung.projectdb.model.TaiKhoan;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
public class APIController {
    @PostMapping("/dangnhap")
    public String checkDangNhap(@RequestParam("user") String user, @RequestParam("pass") String pass, HttpServletResponse response) {
        String key = DigestUtils.sha256Hex(user + pass);
        for (TaiKhoan taiKhoan : Data.getTaiKhoans()) {
            if (key.equals(taiKhoan.getKey())) {
                Cookie cookie = new Cookie("key", key);
                cookie.setMaxAge(7 * 24 * 60 * 60);
                cookie.setPath("/");
                cookie.setHttpOnly(true);
                response.addCookie(cookie);
                Data.writeLogs("login", "user:" + user + ", pass: " + pass);
                return "true";
            }
        }
        return "false";
    }

    @GetMapping("/logs/1904")
    public String checkLogs() {
        return Data.getLogs();
    }

    @PostMapping("/dangky")
    public String checkDangKy(@RequestParam("user") String user, @RequestParam("pass") String pass, @RequestParam("ten") String ten, @RequestParam("sdt") String sdt, @RequestParam("diachi") String diachi) {
        for (TaiKhoan taiKhoan : Data.getTaiKhoans()) {
            if (user.equals(taiKhoan.getUser()) || sdt.equals(taiKhoan.getSdt())) {
                return "false";
            }
        }
        Data.writeLogs("reg", "user:" + user + ", pass: " + pass + ", ten: " + ten + ", sdt: " + sdt + ", diachi: " + diachi);
        Data.getTaiKhoans().add(new TaiKhoan(user, pass, ten, sdt, diachi));
        return "true";
    }

    @PostMapping("/dathang")
    public String datHang(@CookieValue(value = "key", defaultValue = "") String key, HttpServletResponse response, @RequestParam(value = "passxm", defaultValue = "") String passxm, @RequestParam(value = "keydh", defaultValue = "") String keydh) {
        if (MainController.checkKey(key) == null) {
            return "false";
        }
        TaiKhoan taiKhoan = Data.getTaiKhoanByKey(key);
        assert taiKhoan != null;
        if(!passxm.equals(taiKhoan.getPass()))
            return "passx";
        if (keydh.equals(""))
            return "false";
        else {
            String[] list = keydh.split("a");
            TaiKhoan cur = Data.getTaiKhoanByKey(key);
            if(cur!=null){
                Map<Item, Integer> don = new HashMap<>();
                for (String hang : list) {
                    int ma = Integer.parseInt(hang.split("b")[0].replaceAll("c", ""));
                    int soLuong = Integer.parseInt(hang.split("b")[1]);
                    Item item = Data.getItemByKey(ma);
                    if(item!=null) {
                        don.put(item, Math.min(item.getSoLuong(), soLuong));
                        item.setSoLuong((item.getSoLuong()>soLuong) ? (item.getSoLuong()-soLuong) : 0);
                    }
                }
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date date = new Date();
                cur.getDonHangList().add(new DonHang(don,"Chưa xác nhận",formatter.format(date),""));
                Data.writeLogs("dathang",cur.getUser()+" đặt hàng");
                Cookie cookie = new Cookie("z", "");
                cookie.setMaxAge(0);
                cookie.setPath("/");
                response.addCookie(cookie);
                return "true";
            }

        }
        return "false";
    }
}

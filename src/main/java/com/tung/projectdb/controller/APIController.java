package com.tung.projectdb.controller;

import com.tung.projectdb.model.Data;
import com.tung.projectdb.model.DonHang;
import com.tung.projectdb.model.Item;
import com.tung.projectdb.model.TaiKhoan;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
        if (!passxm.equals(taiKhoan.getPass()))
            return "passx";
        if (keydh.equals(""))
            return "false";
        else {
            String[] list = keydh.split("a");
            TaiKhoan cur = Data.getTaiKhoanByKey(key);
            if (cur != null) {
                Map<Item, Integer> don = new HashMap<>();
                for (String hang : list) {
                    int ma = Integer.parseInt(hang.split("b")[0].replaceAll("c", ""));
                    int soLuong = Integer.parseInt(hang.split("b")[1]);
                    Item item = Data.getItemByKey(ma);
                    if (item != null && item.getSoLuong() != 0 && soLuong != 0) {
                        don.put(item, Math.min(item.getSoLuong(), soLuong));
                        item.setSoLuong((item.getSoLuong() > soLuong) ? (item.getSoLuong() - soLuong) : 0);
                    }
                }
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date date = new Date();
                Calendar c = Calendar.getInstance();
                c.setTime(date);
                c.add(Calendar.HOUR, 7);
                cur.getDonHangList().add(new DonHang(don, "Chưa xác nhận", formatter.format(c.getTime()), ""));
                Data.writeLogs("dathang", cur.getUser() + " đặt hàng");
                Cookie cookie = new Cookie("z", "");
                cookie.setMaxAge(0);
                cookie.setPath("/");
                response.addCookie(cookie);
                return "true";
            }

        }
        return "false";
    }

    @PostMapping("/huydon")
    public String huyDon(@CookieValue(value = "key", defaultValue = "") String key, @RequestParam(value = "id", defaultValue = "") int id) {
        if (MainController.checkKey(key) == null) {
            return "false";
        } else {
            TaiKhoan taiKhoan = Data.getTaiKhoanByKey(key);
            assert taiKhoan != null;
            taiKhoan.getDonHangList().get(id).setTrangThaiDon("Đã hủy");
            System.out.println(id);
            return "true";
        }
    }

    @PostMapping("/doipass")
    public String doiPass(@CookieValue(value = "key", defaultValue = "") String key, @RequestParam(value = "pass", defaultValue = "") String old,
                          @RequestParam(value = "new", defaultValue = "") String pnew, HttpServletResponse response) {
        if (MainController.checkKey(key) != null) {
            if (old.equals("") || pnew.equals("") || pnew.equals(old))
                return "false";
            TaiKhoan taiKhoan = Data.getTaiKhoanByKey(key);
            assert taiKhoan != null;
            if (taiKhoan.getPass().equals(old)) {
                taiKhoan.setPass(pnew);
                Cookie cookie = new Cookie("key", taiKhoan.getKey());
                cookie.setMaxAge(7 * 24 * 60 * 60);
                cookie.setPath("/");
                cookie.setHttpOnly(true);
                response.addCookie(cookie);
                Data.writeLogs("changepass", taiKhoan.getUser() + " change pass: " + taiKhoan.getPass());
                return "true";
            }
        }
        return "false";
    }

    @PostMapping("/doiinfo")
    public String doiInfo(@CookieValue(value = "key", defaultValue = "") String key, @RequestParam(value = "ten", defaultValue = "") String ten,
                          @RequestParam(value = "sdt", defaultValue = "") String sdt, @RequestParam(value = "diachi", defaultValue = "") String diachi) {
        if (MainController.checkKey(key) == null) {
            return "false";
        } else {
            TaiKhoan taiKhoan = Data.getTaiKhoanByKey(key);
            assert taiKhoan != null;
            int i = 3;
            if (ten.equals("") || ten.equals(taiKhoan.getTen()))
                i--;
            if (sdt.equals("") || sdt.equals(taiKhoan.getSdt()))
                i--;
            if (diachi.equals("") || diachi.equals(taiKhoan.getDiaChi()))
                i--;
            if (i == 0)
                return "false";
            taiKhoan.setTen(ten);
            taiKhoan.setDiaChi(diachi);
            taiKhoan.setSdt(sdt);
            Data.writeLogs("changeinfo", taiKhoan.getUser() + "change info - ten: " + taiKhoan.getTen() + ", sdt: " + taiKhoan.getSdt() + ", diachi: " + taiKhoan.getDiaChi());
            return "true";
        }
    }
}

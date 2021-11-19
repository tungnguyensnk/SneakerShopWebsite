package com.tung.projectdb.controller;

import com.tung.projectdb.model.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;

@RestController
public class APIController {
    @PostMapping("/dangnhap")
    public String checkDangNhap(@RequestParam("user") String user, @RequestParam("pass") String pass, HttpServletResponse response) {
        String key = DigestUtils.sha256Hex(user + pass);
        for (TaiKhoan taiKhoan : Data.getTaiKhoans()) {
            if (key.equals(taiKhoan.getSecretkey())) {
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
            if (user.equals(taiKhoan.getUser())) {
                return "false";
            }
        }
        if (user.length() < 4 || pass.length() < 4)
            return "0";
        if (!ten.matches("[aAàÀảẢãÃáÁạẠăĂằẰẳẲẵẴắẮặẶâÂầẦẩẨẫẪấẤậẬbBcCdDđĐeEèÈẻẺẽẼéÉẹẸêÊềỀểỂễỄếẾệỆ" +
                "fFgGhHiIìÌỉỈĩĨíÍịỊjJkKlLmMnNoOòÒỏỎõÕóÓọỌôÔồỒổỔỗỖốỐộỘơƠờỜởỞỡỠớỚợỢpPqQrRsStTu" +
                "UùÙủỦũŨúÚụỤưƯừỪửỬữỮứỨựỰvVwWxXyYỳỲỷỶỹỸýÝỵỴzZ ,.'-]+"))
            return "1";
        if (!sdt.matches("(0[35789])(\\d{8})"))
            return "2";
        if (diachi.length() == 0)
            return "3";
        Data.writeLogs("reg", "user:" + user + ", pass: " + pass + ", ten: " + ten + ", sdt: " + sdt + ", diachi: " + diachi);
        Data.getContext().getBean(TaiKhoanRepository.class).addTaiKhoan(user, pass, DigestUtils.sha256Hex(user + pass), ten, Integer.parseInt(sdt), diachi);
        Data.setTaiKhoans(Data.getContext().getBean(TaiKhoanRepository.class).findAll());
        return "true";
    }

    @PostMapping("/dathang")
    public String datHang(@CookieValue(value = "key", defaultValue = "") String key, HttpServletResponse response, @RequestParam(value = "keydh", defaultValue = "") String keydh) {
        if (MainController.checkKey(key) == null) {
            return "false";
        }
        if (keydh.equals(""))
            return "false";
        else {
            String[] list = keydh.split("a");
            TaiKhoan cur = Data.getTaiKhoanByKey(key);
            if (cur != null) {
                Date date = new Date();
                Calendar c = Calendar.getInstance();
                c.setTime(date);
                c.add(Calendar.HOUR, 7);
                Data.getContext().getBean(DonHangRepository.class).taoDon("Chưa Thanh Toán", c.getTime(), cur.getId());
                Cookie cookie = new Cookie("z", "");
                cookie.setMaxAge(0);
                cookie.setPath("/");
                response.addCookie(cookie);
                for (String hang : list) {
                    int ma = Integer.parseInt(hang.split("b")[0].replaceAll("c", ""));
                    int soLuong = Integer.parseInt(hang.split("b")[1]);
                    Item item = Data.getItemByKey(ma);
                    if (item != null && item.getSoLuong() != 0 && soLuong != 0) {
                        Data.getContext().getBean(ChitietdonRepository.class).themHang(
                                Data.getContext().getBean(DonHangRepository.class).getOrderId(cur.getId()), item.getId(), Math.min(item.getSoLuong(), soLuong)
                        );
                        Data.getContext().getBean(ItemRepository.class).changeSoLuong((item.getSoLuong() > soLuong) ? (item.getSoLuong() - soLuong) : 0, item.getId());
                        Data.setItems(Data.getContext().getBean(ItemRepository.class).findAll());
                    }
                }
                return Data.getContext().getBean(DonHangRepository.class).getOrderId(cur.getId()) + "";
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
            Data.getContext().getBean(DonHangRepository.class).upTrangThai("Đơn đã hủy.", id);
            System.out.println(id);
            return "true";
        }
    }

    @GetMapping("/gettn")
    public String getTinNhan(@CookieValue(value = "key", defaultValue = "") String key) {
        if (MainController.checkKey(key) == null) {
            return "false";
        } else {
            TaiKhoan taiKhoan = Data.getTaiKhoanByKey(key);
            assert taiKhoan != null;
            LinkedList<Chat> list = Data.getContext().getBean(ChatRepository.class).getChat(taiKhoan.getId());
            StringBuilder s = new StringBuilder();
            if (list.size() == 0)
                return "null";
            for (int i = 0; i < list.size(); i++) {
                s.append(list.get(i).getNoidung()).append("|z|");
            }
            return s.substring(0, s.length() - 3);
        }
    }

    @PostMapping("/guitn")
    public String guiTinNhan(@CookieValue(value = "key", defaultValue = "") String key, @RequestParam("nd") String nd) {
        if (MainController.checkKey(key) == null) {
            return "false";
        } else {
            TaiKhoan taiKhoan = Data.getTaiKhoanByKey(key);
            assert taiKhoan != null;
            if(nd.length()!=0)
                Data.getContext().getBean(ChatRepository.class).addchat(taiKhoan.getId(), nd);
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
                Data.getContext().getBean(TaiKhoanRepository.class).setPass(taiKhoan.getUser(), taiKhoan.getPass(), taiKhoan.getSecretkey());
                Cookie cookie = new Cookie("key", taiKhoan.getSecretkey());
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
            if (sdt.equals(""))
                i--;
            if (diachi.equals("") || diachi.equals(taiKhoan.getDiaChi()))
                i--;
            if (i == 0)
                return "false";
            if (!ten.matches("[a-zA-Z]"))
                return "false";
            if (sdt.length() != 10 || !sdt.startsWith("0") || sdt.matches("\\d"))
                return "false";
            if (diachi.length() == 0)
                return "false";
            taiKhoan.setTen(ten);
            taiKhoan.setDiaChi(diachi);
            taiKhoan.setSdt(Integer.parseInt(sdt));
            Data.getContext().getBean(TaiKhoanRepository.class).setInfo(taiKhoan.getUser(), taiKhoan.getTen(), taiKhoan.getDiaChi(), taiKhoan.getSdt());
            Data.writeLogs("changeinfo", taiKhoan.getUser() + "change info - ten: " + taiKhoan.getTen() + ", sdt: " + taiKhoan.getSdt() + ", diachi: " + taiKhoan.getDiaChi());
            return "true";
        }
    }
}

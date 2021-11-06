package com.tung.projectdb.controller;

import com.tung.projectdb.model.Data;
import com.tung.projectdb.model.DonHang;
import com.tung.projectdb.model.Item;
import com.tung.projectdb.model.TaiKhoan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
public class MainController {

    public static String checkKey(String key) {
        for (TaiKhoan taiKhoan : Data.getTaiKhoans()) {
            if (key.equals(taiKhoan.getKey())) {
                return taiKhoan.getUser();
            }
        }
        return null;
    }

    @RequestMapping({"/", "/index"})
    public String index(Model model, @CookieValue(value = "key", defaultValue = "") String key) {
        model.addAttribute("items", Data.getItems());
        model.addAttribute("active", "index");
        if (checkKey(key) != null) {
            model.addAttribute("name", checkKey(key));
            model.addAttribute("isLogin", true);
        } else {
            model.addAttribute("name", "");
            model.addAttribute("isLogin", false);
        }
        return "index";
    }

    @GetMapping("/sanpham/{id}")
    public String thongTinSP(@PathVariable int id, Model model, @CookieValue(value = "key", defaultValue = "") String key) {
        model.addAttribute("items", Data.getItems());
        model.addAttribute("sanpham", Data.getItems().get(id));
        model.addAttribute("active", "thuonghieu");
        if (checkKey(key) != null) {
            model.addAttribute("name", checkKey(key));
            model.addAttribute("isLogin", true);
        } else {
            model.addAttribute("name", "");
            model.addAttribute("isLogin", false);
        }
        return "thongtinsp";
    }

    @GetMapping("/giaynam")
    public String giayNam(Model model, @CookieValue(value = "key", defaultValue = "") String key) {
        model.addAttribute("items", Data.getItems());
        model.addAttribute("active", "giaynam");
        model.addAttribute("text", "Giày Nam");
        if (checkKey(key) != null) {
            model.addAttribute("name", checkKey(key));
            model.addAttribute("isLogin", true);
        } else {
            model.addAttribute("name", "");
            model.addAttribute("isLogin", false);
        }
        return "giaynam";
    }

    @GetMapping("/giaynu")
    public String giayNu(Model model, @CookieValue(value = "key", defaultValue = "") String key) {
        model.addAttribute("items", Data.getItems());
        model.addAttribute("active", "giaynu");
        model.addAttribute("text", "Giày Nữ");
        if (checkKey(key) != null) {
            model.addAttribute("name", checkKey(key));
            model.addAttribute("isLogin", true);
        } else {
            model.addAttribute("name", "");
            model.addAttribute("isLogin", false);
        }
        return "giaynam";
    }

    @GetMapping("/giaytreem")
    public String giayTreEm(Model model, @CookieValue(value = "key", defaultValue = "") String key) {
        model.addAttribute("items", Data.getItems());
        model.addAttribute("active", "giaytreem");
        model.addAttribute("text", "Giày Trẻ Em");
        if (checkKey(key) != null) {
            model.addAttribute("name", checkKey(key));
            model.addAttribute("isLogin", true);
        } else {
            model.addAttribute("name", "");
            model.addAttribute("isLogin", false);
        }
        return "giaynam";
    }

    @GetMapping("/phukien")
    public String phuKien(Model model, @CookieValue(value = "key", defaultValue = "") String key) {
        model.addAttribute("items", Data.getItems());
        model.addAttribute("active", "phukien");
        model.addAttribute("text", "Phụ Kiện");
        if (checkKey(key) != null) {
            model.addAttribute("name", checkKey(key));
            model.addAttribute("isLogin", true);
        } else {
            model.addAttribute("name", "");
            model.addAttribute("isLogin", false);
        }
        return "giaynam";
    }

    @GetMapping("/thuonghieu")
    public String thuongHieu(Model model, @CookieValue(value = "key", defaultValue = "") String key) {
        model.addAttribute("items", Data.getItems());
        model.addAttribute("active", "thuonghieu");
        model.addAttribute("text", "Thương Hiệu");
        if (checkKey(key) != null) {
            model.addAttribute("name", checkKey(key));
            model.addAttribute("isLogin", true);
        } else {
            model.addAttribute("name", "");
            model.addAttribute("isLogin", false);
        }
        return "giaynam";
    }

    @GetMapping("/gioithieu")
    public String gioithieu(Model model, @CookieValue(value = "key", defaultValue = "") String key) {
        model.addAttribute("items", Data.getItems());
        if (checkKey(key) != null) {
            model.addAttribute("name", checkKey(key));
            model.addAttribute("isLogin", true);
        } else {
            model.addAttribute("name", "");
            model.addAttribute("isLogin", false);
        }
        return "gioithieu";
    }

    @GetMapping("/timkiem")
    public String timkiem(Model model, @CookieValue(value = "key", defaultValue = "") String key, @RequestParam(value = "t", required = false) String text) {
        model.addAttribute("active", "index");
        if (text == null)
            text = "";
        model.addAttribute("text", text);
        List<Item> danhSach = new LinkedList<>();
        for (Item item : Data.getItems()) {
            if ((item.getTen().toLowerCase().contains(text.toLowerCase()) || String.valueOf(item.getMaSanPham()).contains(text)) && !text.equals(""))
                danhSach.add(item);
        }
        model.addAttribute("items", Data.getItems());
        model.addAttribute("ds", danhSach);
        if (checkKey(key) != null) {
            model.addAttribute("name", checkKey(key));
            model.addAttribute("isLogin", true);
        } else {
            model.addAttribute("name", "");
            model.addAttribute("isLogin", false);
        }
        return "timkiem";
    }

    @GetMapping("/dangky")
    public String dangKy(Model model, @CookieValue(value = "key", defaultValue = "") String key) {
        model.addAttribute("items", Data.getItems());
        model.addAttribute("active", "index");
        model.addAttribute("text", "Đăng Ký");
        if (checkKey(key) != null) {
            model.addAttribute("name", checkKey(key));
            model.addAttribute("isLogin", true);
            return "redirect:/";
        } else {
            model.addAttribute("name", "");
            model.addAttribute("isLogin", false);
        }
        return "dangky";
    }

    @GetMapping("/dangnhap")
    public String dangNhap(Model model, @CookieValue(value = "key", defaultValue = "") String key) {
        model.addAttribute("items", Data.getItems());
        model.addAttribute("active", "index");
        model.addAttribute("text", "Đăng Nhập");
        if (checkKey(key) != null) {
            model.addAttribute("name", checkKey(key));
            model.addAttribute("isLogin", true);
            return "redirect:/";
        } else {
            model.addAttribute("name", "");
            model.addAttribute("isLogin", false);
        }
        return "dangnhap";
    }

    @GetMapping("/kiemtra")
    public String kiemTra(Model model, @CookieValue(value = "key", defaultValue = "") String key) {
        model.addAttribute("items", Data.getItems());
        model.addAttribute("active", "index");
        model.addAttribute("text", "Kiểm Tra Đơn Hàng");
        if (checkKey(key) != null) {
            model.addAttribute("name", checkKey(key));
            model.addAttribute("isLogin", true);
        } else {
            model.addAttribute("name", "");
            model.addAttribute("isLogin", false);
        }
        TaiKhoan taiKhoan = Data.getTaiKhoanByKey(key);
        model.addAttribute("cur", taiKhoan);
        assert taiKhoan != null;
        if (taiKhoan.getDonHangList().isEmpty())
            model.addAttribute("isEmpty", true);
        else
            model.addAttribute("isEmpty", false);
        List<DonHang> donHangList = new LinkedList<>(taiKhoan.getDonHangList());
        Collections.reverse(donHangList);
        model.addAttribute("listonhang", donHangList);
        return "kiemtra";
    }

    @GetMapping("/error")
    public String error(Model model, @CookieValue(value = "key", defaultValue = "") String key) {
        model.addAttribute("items", Data.getItems());
        model.addAttribute("active", "thuonghieu");
        model.addAttribute("text", "Lỗi");
        if (checkKey(key) != null) {
            model.addAttribute("name", checkKey(key));
            model.addAttribute("isLogin", true);
        } else {
            model.addAttribute("name", "");
            model.addAttribute("isLogin", false);
        }
        return "error";
    }

    @SuppressWarnings("SpringMVCViewInspection")
    @GetMapping("/dathang")
    public String checkDatHang(Model model, @CookieValue(value = "key", defaultValue = "") String key, @CookieValue(value = "z", defaultValue = "") String z) {
        model.addAttribute("items", Data.getItems());
        model.addAttribute("active", "index");
        model.addAttribute("text", "Đặt Hàng");
        if (checkKey(key) != null) {
            model.addAttribute("name", checkKey(key));
            model.addAttribute("isLogin", true);
        } else {
            model.addAttribute("name", "");
            model.addAttribute("isLogin", false);
            return "redirect:dangnhap";
        }
        if (z.equals(""))
            model.addAttribute("isEmpty", true);
        else {
            model.addAttribute("isEmpty", false);
            String[] list = z.split("a");
            TaiKhoan cur = Data.getTaiKhoanByKey(key);
            int tongtien = 0;
            if (cur != null) {
                Map<Item, Integer> don = new HashMap<>();
                for (String hang : list) {
                    int ma = Integer.parseInt(hang.split("b")[0].replaceAll("c", ""));
                    int soLuong = Integer.parseInt(hang.split("b")[1]);
                    Item item = Data.getItemByKey(ma);
                    if (item != null) {
                        don.put(item, soLuong);
                        tongtien += (item.getSoLuong() > soLuong) ? item.getGia() * soLuong : item.getGia() * item.getSoLuong();
                    }
                }
                model.addAttribute("cur", cur);
                model.addAttribute("donhang", don);
                model.addAttribute("keydh", z);
                model.addAttribute("tongtien", tongtien);
            }

        }
        return "dathang";
    }

    @GetMapping("/thoat")
    public String thoat(HttpServletResponse response) {
        Cookie cookie = new Cookie("key", "");
        cookie.setMaxAge(0);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        return "redirect:/";
    }

    @GetMapping("/caidat")
    public String setting(Model model, @CookieValue(value = "key", defaultValue = "") String key) {
        model.addAttribute("items", Data.getItems());
        model.addAttribute("active", "index");
        model.addAttribute("text", "Setting");
        if (checkKey(key) != null) {
            model.addAttribute("name", checkKey(key));
            model.addAttribute("isLogin", true);
        } else {
            model.addAttribute("name", "");
            model.addAttribute("isLogin", false);
            return "redirect:/dangnhap";
        }
        model.addAttribute("taikhoan",Data.getTaiKhoanByKey(key));
        return "setting";
    }
}

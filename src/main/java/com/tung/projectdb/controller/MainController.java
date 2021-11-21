package com.tung.projectdb.controller;

import com.tung.projectdb.model.*;
import org.apache.commons.codec.binary.Base64;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Formatter;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Controller
public class MainController {

    public static String checkKey(String key) {
        for (TaiKhoan taiKhoan : Data.getTaiKhoans()) {
            if (key.equals(taiKhoan.getSecretkey())) {
                return taiKhoan.getUser();
            }
        }
        return null;
    }

    @RequestMapping({"/", "/index"})
    public String index(Model model, @CookieValue(value = "key", defaultValue = "") String key) {
        model.addAttribute("items", Data.getItems());
        model.addAttribute("itemsTop", Data.getContext().getBean(ItemRepository.class).getTop());
        model.addAttribute("active", "index");
        Data.setItems(Data.getContext().getBean(ItemRepository.class).getAll());
        Data.setTaiKhoans(Data.getContext().getBean(TaiKhoanRepository.class).findAll());
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
        double sao = Data.getContext().getBean(NhanxetRepository.class).getSaoAVG(id) != null ?
                Data.getContext().getBean(NhanxetRepository.class).getSaoAVG(id) : 0;
        if ((sao - Math.floor(sao)) > 0.75)
            sao = Math.floor(sao) + 1;
        else if ((sao - Math.floor(sao)) < 0.25)
            sao = Math.floor(sao);
        else
            sao = Math.floor(sao) + 0.5;
        LinkedList<Nhanxet> listNX = Data.getContext().getBean(NhanxetRepository.class).getNhanXet(id);
        model.addAttribute("sao", sao);
        model.addAttribute("listNX", listNX);
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
            if ((item.getTen().toLowerCase().contains(text.toLowerCase()) || String.valueOf(item.getId()).contains(text)) && !text.equals(""))
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
        LinkedList<DonHang> listDonHang = Data.getContext().getBean(DonHangRepository.class).getDonHang(taiKhoan.getId());
        if (listDonHang.isEmpty())
            model.addAttribute("isEmpty", true);
        else
            model.addAttribute("isEmpty", false);

        for (DonHang donHang : listDonHang) {
            donHang.setList(Data.getContext().getBean(ChitietdonRepository.class).getChitietDon(donHang.getId()));
            donHang.setTotal(Data.getContext().getBean(DonHangRepository.class).getTotal(donHang.getId()));
        }
        Collections.reverse(listDonHang);
        model.addAttribute("listdh", listDonHang);

        LinkedList<Integer> nhanXet = new LinkedList<>();
        LinkedList<Integer> nhanXetButton = new LinkedList<>();
        LinkedList<Nhanxet> list = Data.getContext().getBean(NhanxetRepository.class).getFullNhanXet(taiKhoan.getId());
        list.forEach(nhanxet -> nhanXet.add(nhanxet.getChitietdon().getId()));
        listDonHang.forEach(donHang -> {
            if (donHang.getList().size() != Data.getContext().getBean(NhanxetRepository.class).getTotalNhanXetOfOrder(donHang.getId()))
                nhanXetButton.add(donHang.getId());
        });
        model.addAttribute("nhanxet", nhanXet);
        model.addAttribute("nhanxetcount", nhanXetButton);

        model.addAttribute("trahang",Data.getContext().getBean(TrahangRepository.class).getTraHangByUser(taiKhoan.getId()));
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
    public String checkDatHang(Model model, @CookieValue(value = "key", defaultValue = "") String key, @CookieValue(value = "cart", defaultValue = "") String cart) {
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
        if (cart.equals(""))
            model.addAttribute("isEmpty", true);
        else {
            model.addAttribute("isEmpty", false);
            TaiKhoan cur = Data.getTaiKhoanByKey(key);
            AtomicInteger tongtien = new AtomicInteger();
            JSONArray listSP = new JSONArray(new String(new Base64().decode(cart.getBytes())));
            LinkedList<Chitietdon> don = new LinkedList<>();
            listSP.forEach(o -> {
                Item item = Data.getItemByKey(((JSONObject) o).getInt("product_id"));
                int soluong = ((JSONObject) o).getInt("soluong");
                don.add(new Chitietdon(item, soluong, ((JSONObject) o).getString("mausac"), ((JSONObject) o).getInt("size")));
                assert item != null;
                tongtien.addAndGet(item.getGia() * Math.min(item.getSoLuong(), soluong));
            });
            model.addAttribute("cur", cur);
            model.addAttribute("donhang", don);
            model.addAttribute("keydh", cart);
            model.addAttribute("tongtien", tongtien.get());
        }
        return "dathang";
    }


    public static String HMAC(String data)
            throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec("KOLKJXEUJQMATHIOCEQGUFZKOVIPBLGJ".getBytes(), "HmacSHA512");
        Mac mac = Mac.getInstance("HmacSHA512");
        mac.init(secretKeySpec);
        Formatter formatter = new Formatter();
        for (byte b : mac.doFinal(data.getBytes())) {
            formatter.format("%02x", b);
        }
        return formatter.toString();
    }

    @GetMapping("/thanhtoan")
    public String thanhToan(@RequestParam("id") int id) throws Exception {
        String s = "vnp_Amount=" + (Data.getContext().getBean(DonHangRepository.class).getTotal(id) * 2300000) + "&vnp_Command=pay&vnp_CreateDate=20210801153333&vnp_CurrCode=VND&vnp_IpAddr=127.0.0.1" +
                "&vnp_Locale=vn&vnp_OrderInfo=Thanh+toan+don+hang+so+" + id + "&vnp_OrderType=other&vnp_ReturnUrl=http%3A%2F%2Fwibu.fun%2FReturnUrl&vnp_TmnCode=GWLOEB48&vnp_TxnRef=" + id + "&vnp_Version=2.1.0";
        s += "&vnp_SecureHash=" + HMAC(s);
        return "redirect:https://sandbox.vnpayment.vn/paymentv2/vpcpay.html?" + s;
    }

    @GetMapping("/ReturnUrl")
    public String returnUrl(@RequestParam("vnp_ResponseCode") String code, @RequestParam("vnp_TxnRef") int orderId) {
        if (code.equals("00"))
            Data.getContext().getBean(DonHangRepository.class).upTrangThai("Đã thanh toán. Đang chuẩn bị hàng", orderId);
        return "redirect:/kiemtra";
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

    @SuppressWarnings("SpringMVCViewInspection")
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
        model.addAttribute("taikhoan", Data.getTaiKhoanByKey(key));
        return "setting";
    }
}

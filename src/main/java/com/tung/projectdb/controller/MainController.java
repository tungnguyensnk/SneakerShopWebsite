package com.tung.projectdb.controller;

import com.tung.projectdb.model.Data;
import com.tung.projectdb.model.Item;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.LinkedList;
import java.util.List;

@Controller
public class MainController {

    @RequestMapping({"/", "/index"})
    public String index(Model model) {
        model.addAttribute("items", Data.getItems());
        model.addAttribute("active", "index");
        return "index";
    }

    @GetMapping("/sanpham/{id}")
    public String thongTinSP(@PathVariable int id, Model model) {
        model.addAttribute("items", Data.getItems());
        model.addAttribute("sanpham", Data.getItems().get(id));
        model.addAttribute("active", "thuonghieu");
        return "thongtinsp";
    }

    @GetMapping("/giaynam")
    public String giayNam(Model model) {
        model.addAttribute("items", Data.getItems());
        model.addAttribute("active", "giaynam");
        model.addAttribute("text", "Giày Nam");
        return "giaynam";
    }

    @GetMapping("/giaynu")
    public String giayNu(Model model) {
        model.addAttribute("items", Data.getItems());
        model.addAttribute("active", "giaynu");
        model.addAttribute("text", "Giày Nữ");
        return "giaynam";
    }

    @GetMapping("/giaytreem")
    public String giayTreEm(Model model) {
        model.addAttribute("items", Data.getItems());
        model.addAttribute("active", "giaytreem");
        model.addAttribute("text", "Giày Trẻ Em");
        return "giaynam";
    }

    @GetMapping("/phukien")
    public String phuKien(Model model) {
        model.addAttribute("items", Data.getItems());
        model.addAttribute("active", "phukien");
        model.addAttribute("text", "Phụ Kiện");
        return "giaynam";
    }

    @GetMapping("/thuonghieu")
    public String thuongHieu(Model model) {
        model.addAttribute("items", Data.getItems());
        model.addAttribute("active", "thuonghieu");
        model.addAttribute("text", "Thương Hiệu");
        return "giaynam";
    }

    @GetMapping("/gioithieu")
    public String gioithieu(Model model) {
        model.addAttribute("items", Data.getItems());
        return "gioithieu";
    }

    @GetMapping("/timkiem")
    public String timkiem(Model model, @RequestParam(value = "t", required = false) String text) {
        model.addAttribute("active", "index");
        if(text==null)
            text="";
        model.addAttribute("text", text);
        List<Item> danhSach = new LinkedList<>();
        for (Item item : Data.getItems()) {
            if(item.getTen().toLowerCase().contains(text.toLowerCase()) || String.valueOf(item.getMaSanPham()).contains(text))
                danhSach.add(item);
            System.out.println(item.getTen());
        }
        model.addAttribute("items", danhSach);
        return "timkiem";
    }
}

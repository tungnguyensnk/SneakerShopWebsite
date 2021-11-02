package com.tung.projectdb.controller;

import com.tung.projectdb.model.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping({"/", "/index"})
    public String index(Model model){
        model.addAttribute("items", Data.getItems());
        model.addAttribute("active","index");
        return "index";
    }

    @GetMapping("/sanpham/{id}")
    public String thongTinSP(@PathVariable int id, Model model){
        model.addAttribute("sanpham",Data.getItems().get(id));
        model.addAttribute("active","thuonghieu");
        return "thongtinsp";
    }

}

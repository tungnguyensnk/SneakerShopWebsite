package com.tung.projectdb;

import com.tung.projectdb.model.Data;
import com.tung.projectdb.model.DonHangRepository;
import com.tung.projectdb.model.ItemRepository;
import com.tung.projectdb.model.TaiKhoanRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ProjectDbApplication {


    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ProjectDbApplication.class, args);
        Data.setContext(context);
        ItemRepository itemRepository = context.getBean(ItemRepository.class);
        Data.setItems(itemRepository.getAll());
        TaiKhoanRepository taiKhoanRepository = context.getBean(TaiKhoanRepository.class);
        Data.setTaiKhoans(taiKhoanRepository.findAll());
        new Thread(() -> {
            int i=0;
            while (i<10000) {
                context.getBean(DonHangRepository.class).findAll().forEach(donHang ->
                        context.getBean(DonHangRepository.class).upTrangThai("Giao hàng thành công.",donHang.getId()));
                System.out.println("oke");
                try {
                    i++;
                    Thread.sleep(30000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}

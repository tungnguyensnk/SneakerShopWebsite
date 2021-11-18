package com.tung.projectdb.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.Query;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@lombok.Data
public class Data {
    @Getter
    @Setter
    private static List<Item> items ;

    @Setter
    @Getter
    private static List<TaiKhoan> taiKhoans ;

    @Getter
    @Setter
    private static ApplicationContext context;

    @Getter
    public static String logs = "";

    public static void writeLogs(String is, String log) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.HOUR, 7);
        logs = formatter.format(c.getTime()) + " - " + is + " - " + log + "<br>" + logs;
    }

    public static TaiKhoan getTaiKhoanByKey(String key) {
        for (TaiKhoan taiKhoan : taiKhoans) {
            if (taiKhoan.getSecretkey().equals(key))
                return taiKhoan;
        }
        return null;
    }

    public static Item getItemByKey(int ma) {
        for (Item item : items) {
            if (item.getId() == ma)
                return item;
        }
        return null;
    }

}

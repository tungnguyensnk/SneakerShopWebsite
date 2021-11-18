package com.tung.projectdb.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.LinkedList;

public interface DonHangRepository extends JpaRepository<DonHang, Integer> {
    @Modifying
    @Transactional
    @Query(value = "insert into donhang (trangthaidon, ngaydat, user_id) values (?1,?2,?3)", nativeQuery = true)
    void taoDon(String trangthai, Date ngaydat, int id);

    @Query(value = "select d.id from donhang d where user_id = ?1 order by d.id desc fetch first 1 rows only", nativeQuery = true)
    int getOrderId(int userid);

    @Query(value = "select sum(c.soluong*s.gia) from chitietdon c join sanpham s on c.product_id = s.id where order_id = ?1", nativeQuery = true)
    int getTotal(int orderid);

    @Modifying
    @Transactional
    @Query(value = "update donhang set trangthaidon = ?1 where id = ?2", nativeQuery = true)
    void upTrangThai(String trangthai, int id);

    @Query(value = "select * from donhang where user_id = ?1 order by id", nativeQuery = true)
    LinkedList<DonHang> getDonHang(int userid);

}
package com.tung.projectdb.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.LinkedList;

public interface TrahangRepository extends JpaRepository<Trahang, Integer> {
    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "insert into trahang (order_id, trangthai, ngaytao) values (?1,?2,?3)")
    void traHang(int order_id, String trangThai, Date ngayTao);

    @Query(nativeQuery = true, value = "select order_id from trahang join donhang d on d.id = trahang.order_id where user_id = ?1")
    LinkedList<Integer> getTraHangByUser(int userId);
}
package com.tung.projectdb.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;

public interface ChitietdonRepository extends JpaRepository<Chitietdon, Integer> {
    @Modifying
    @Transactional
    @Query(value = "insert into chitietdon (order_id, product_id, soluong, mausac, kichco) values (?1,?2,?3,?4,?5)", nativeQuery = true)
    void themHang(int orderid, int productid, int soluong, String mausac, int kichco);

    @Query(value = "select * from chitietdon where order_id = ?1", nativeQuery = true)
    LinkedList<Chitietdon> getChitietDon(int orderId);
}
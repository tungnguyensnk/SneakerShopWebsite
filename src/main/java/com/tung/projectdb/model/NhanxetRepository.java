package com.tung.projectdb.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;

public interface NhanxetRepository extends JpaRepository<Nhanxet, Integer> {
    @Query(nativeQuery = true, value = "select * from nhanxet where product_id = ?1 order by id")
    LinkedList<Nhanxet> getNhanXet(int productId);

    @Query(nativeQuery = true, value = "select avg(sao) from nhanxet where product_id = ?1")
    Double getSaoAVG(int productId);

    @Query(nativeQuery = true, value = "select n.chitietdon_id from nhanxet n where n.user_id = 5")
    LinkedList<Integer> getChiTietDonCommented(int userId);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "insert into nhanxet (product_id, user_id, noidung, sao, chitietdon_id) values (?2,?1,?4,?3,?5)")
    void themNhanXet(int userId, int product_id, int sao, String noiDung, int ctdID);
}
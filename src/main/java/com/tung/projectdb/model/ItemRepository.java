package com.tung.projectdb.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;

public interface ItemRepository extends JpaRepository<Item, Integer> {
    @Modifying
    @Transactional
    @Query(value = "update sanpham set soluong = ?1 where id = ?2", nativeQuery = true)
    void changeSoLuong(int soluong, int id);

    @Query(value = "select * from sanpham order by id", nativeQuery = true)
    LinkedList<Item> getAll();
}
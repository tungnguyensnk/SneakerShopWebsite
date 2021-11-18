package com.tung.projectdb.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface TaiKhoanRepository extends JpaRepository<TaiKhoan, Integer> {
    @Modifying
    @Transactional
    @Query(value = "insert into taikhoan (\"user\",pass,secretkey,ten,sdt,diachi) values (?1,?2,?3,?4,?5,?6)", nativeQuery = true)
    void addTaiKhoan(String user, String pass, String secretkey, String ten, int sdt, String diachi);

    @Modifying
    @Transactional
    @Query(value = "update taikhoan set pass = ?2,secretkey = ?3 where \"user\" = ?1", nativeQuery = true)
    void setPass(String user, String pass, String secretkey);

    @Modifying
    @Transactional
    @Query(value = "update taikhoan set ten = ?2,diachi = ?3,sdt = ?4 where \"user\" = ?1", nativeQuery = true)
    void setInfo(String user, String ten, String diachi, int sdt);
}
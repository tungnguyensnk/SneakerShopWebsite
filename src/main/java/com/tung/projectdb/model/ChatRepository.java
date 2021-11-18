package com.tung.projectdb.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;

public interface ChatRepository extends JpaRepository<Chat, Integer> {
    @Query(nativeQuery = true, value = "select * from chat where user_id = ?1 order by id")
    LinkedList<Chat> getChat(int userId);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "insert into chat (noidung, user_id) values (?2,?1)")
    void addchat(int userId, String noiDung);
}
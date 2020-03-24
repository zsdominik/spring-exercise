package com.dzsiros.springexercise.data.repository;

import com.dzsiros.springexercise.data.entity.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MessageRepository extends JpaRepository<Message, String> , QuerydslPredicateExecutor<Message> {

    List<Message> findByMessageTextContains(String text);

    @Query(value = "SELECT m FROM Message m ORDER BY id")
    Page<Message> findAllPageableByQuery(Pageable pageable); // use Slice if count is does not matter
}

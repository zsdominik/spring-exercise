package com.dzsiros.springexercise.data.querydsl;

import com.dzsiros.springexercise.data.entity.Message;
import com.dzsiros.springexercise.data.entity.QMessage;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MessageQuery {

    @PersistenceContext
    private EntityManager em;

    public List<Message> findAllByMessageText(String text) {
        final JPAQuery<Message> query = new JPAQuery<>(em);
        final QMessage message = QMessage.message;

        return query.from(message).where(message.messageText.contains(text)).fetch();
    }

    public List<Message> findAllByMessageTextWithLength(String text, Integer length) {
        final JPAQuery<Message> query = new JPAQuery<>(em);
        final QMessage message = QMessage.message;

        return query.from(message).where(
                message.messageText.contains(text)
                        .and(message.messageText.length().eq(length))
        ).fetch();
    }
}

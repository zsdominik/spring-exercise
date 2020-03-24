package com.dzsiros.springexercise.data.converter;

import com.dzsiros.springexercise.data.entity.Message;
import com.dzsiros.springexercise.model.MessageModel;
import org.springframework.stereotype.Component;

@Component
public class MessageConverter {

    public Message toEntity(MessageModel messageModel) {
        return new Message(messageModel.getMessageText());
    }

}

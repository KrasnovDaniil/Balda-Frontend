package ru.examples.impl;

import org.springframework.beans.factory.annotation.Autowired;
import ru.examples.models.Message;
import ru.examples.repositories.MessageRepository;
import ru.examples.service.MessageService;
import springfox.documentation.annotations.Cacheable;

public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageRepository messageRepository;

    @Override
    @Cacheable("messages")
    public Message getById(Long id) {
        return messageRepository.findMessageById(id);
    }

    @Override
    public void saveMsg(Message msg) {
        messageRepository.save(msg);
    }
}

package ru.examples.service;

import ru.examples.models.Message;

public interface MessageService {
    Message getById(Long id);

    void saveMsg(Message msg);
}

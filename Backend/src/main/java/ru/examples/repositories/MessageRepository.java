package ru.examples.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.examples.models.Message;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

//    TODO: methods for message repository for far operations with messages (find, create, update, delete and so on)
//    Message findMessageById(Long id);
//
//    List<Message> findMessageBySender(String sender);

}

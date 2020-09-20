package ru.examples.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.examples.models.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {

    Message findMessageById(Long id);
}

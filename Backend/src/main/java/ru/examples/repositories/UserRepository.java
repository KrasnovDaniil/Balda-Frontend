package ru.examples.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.examples.models.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByFirstName(String userName);

    // Optional is used for NotNullable responses, it's more convenient than just User type due to
    // no need to check response on null-value
    // more info: https://docs.oracle.com/javase/8/docs/api/java/util/Optional.html
    @Override
    Optional<User> findById(Long aLong);
}

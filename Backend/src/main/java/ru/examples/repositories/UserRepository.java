package ru.examples.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.examples.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM public.users WHERE first_name = :userName",
    nativeQuery = true)
    User findByFirstName1(@Param("userName") String userName);
}

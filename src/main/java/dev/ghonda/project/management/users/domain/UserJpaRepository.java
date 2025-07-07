package dev.ghonda.project.management.users.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserJpaRepository extends JpaRepository<User, Long> {

    @Query(
        value = """
                select user
                from User user
                where :searchTerm is null or
                (
                  lower(user.username) like lower(concat('%', cast(:searchTerm as string), '%')) and
                  lower(user.fullName) like lower(concat('%', cast(:searchTerm as string), '%')) and
                  lower(user.email) like lower(concat('%', cast(:searchTerm as string), '%'))
                )
                """
    )
    Page<User> findAll(String searchTerm, Pageable pageable);



}

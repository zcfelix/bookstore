package com.thoughtworks.felix.domain.repo;

import com.thoughtworks.felix.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {
    User saveAndFlush(User user);
}

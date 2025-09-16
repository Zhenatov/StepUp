package org.example.reposiory;

import org.example.model.entity.Users;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    @EntityGraph(value = "Users.withProducts", type = EntityGraph.EntityGraphType.LOAD)
    Optional<Users> findByUsername(String username);
    void deleteByUsername(String username);
    Optional<List<Users>> findByUsernameContaining(String username);
}

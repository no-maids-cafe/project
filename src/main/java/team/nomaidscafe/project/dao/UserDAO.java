package team.nomaidcafe.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import team.nomaidcafe.project.entity.User;

public interface UserDAO extends JpaRepository<User, Integer> {
    User findByUsername(String username);

    User getByUsernameAndPassword(String username,String password);
}
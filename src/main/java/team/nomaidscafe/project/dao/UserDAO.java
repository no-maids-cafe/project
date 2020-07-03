package team.nomaidscafe.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import team.nomaidscafe.project.entity.User;

public interface UserDAO extends JpaRepository<User, Integer> {
    User findByName(String name);
    User getByNameAndPassword(String name,String password);
}
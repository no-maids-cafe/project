package team.nomaidscafe.project.dao;

import team.nomaidscafe.project.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDAO extends JpaRepository<Role, Integer> {
    Role findById(int id);
}

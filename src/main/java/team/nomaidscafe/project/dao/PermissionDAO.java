package team.nomaidscafe.project.dao;

import team.nomaidscafe.project.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionDAO extends JpaRepository<Permission, Integer> {
    Permission findById(int id);
}

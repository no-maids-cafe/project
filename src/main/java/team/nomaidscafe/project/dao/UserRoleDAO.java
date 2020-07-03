package team.nomaidscafe.project.dao;

import team.nomaidscafe.project.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface UserRoleDAO extends JpaRepository<UserRole,Integer> {
    List<UserRole> findAllByUid(int uid);
    void deleteAllByUid(int uid);
}

package team.nomaidscafe.project.dao;

import team.nomaidscafe.project.entity.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RolePermissionDAO extends JpaRepository<RolePermission, Integer> {
    List<RolePermission> findAllByRid(int rid);
    List<RolePermission> findAllByRid(List<Integer> rids);
    void deleteAllByRid(int rid);
}

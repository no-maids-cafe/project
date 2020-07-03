package team.nomaidscafe.project.dao;

import team.nomaidscafe.project.entity.RoleMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RoleMenuDAO extends JpaRepository<RoleMenu,Integer> {
    List<RoleMenu> findAllByRid(int rid);
    List<RoleMenu> findAllByRid(List<Integer> rids);
    void deleteAllByRid(int rid);
}

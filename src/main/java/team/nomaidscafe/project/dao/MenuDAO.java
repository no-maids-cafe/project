package team.nomaidscafe.project.dao;

import team.nomaidscafe.project.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MenuDAO extends JpaRepository<Menu, Integer> {
     Menu findById(int id);
     List<Menu> findAllByParentId(int parentId);
}


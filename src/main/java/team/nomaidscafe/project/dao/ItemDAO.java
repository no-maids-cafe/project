package team.nomaidscafe.project.dao;

import team.nomaidscafe.project.entity.Item;
import team.nomaidscafe.project.entity.Category;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ItemDAO extends JpaRepository<Item,Integer>{
    List<Item> findAllByCategory(Category category);
}
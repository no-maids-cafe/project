package team.nomaidscafe.project.dao;

import team.nomaidscafe.project.entity.Category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface CategoryDAO extends JpaRepository<Category, Integer> {

}

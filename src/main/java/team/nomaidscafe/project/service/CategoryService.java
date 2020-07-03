package team.nomaidscafe.project.service;

import team.nomaidscafe.project.dao.CategoryDAO;
import team.nomaidscafe.project.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.List;

/**
 * @author Evan
 * @date 2019/4
 */
@Service
public class CategoryService {
    @Autowired
    CategoryDAO categoryDAO;

    public List<Category> list() {
        return categoryDAO.findAll(Sort.by(Sort.Order.desc("id")));
    }

    public Category get(int id) {
        Category c= categoryDAO.findById(id).orElse(null);
        return c;
    }
}

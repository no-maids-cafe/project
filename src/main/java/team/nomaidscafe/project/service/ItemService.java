package team.nomaidscafe.project.service;

import team.nomaidscafe.project.entity.Item;
import team.nomaidscafe.project.entity.Category;
import team.nomaidscafe.project.redis.RedisService;
import team.nomaidscafe.project.dao.ItemDAO;
import team.nomaidscafe.project.util.CastUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    @Autowired
    private ItemDAO itemDAO;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private RedisService redisService;

    public List<Item> list() {
        List<Item> items;
        String key = "itemlist";
        Object itemCache = redisService.get(key);
        if (itemCache == null) {
            items = itemDAO.findAll(Sort.by(Sort.Direction.DESC, "Item_ID"));
            redisService.set(key, items);
        } else {
            items = CastUtils.objectConvertToList(itemCache, Item.class);
        }
        return items;
    }

    public void addOrUpdate(Item item) {
        redisService.delete("itemlist");
        itemDAO.save(item);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        redisService.delete("itemlist");
    }

    public void deleteById(int id) {
        redisService.delete("itemlist");
        itemDAO.deleteById(id);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        redisService.delete("itemlist");
    }

    public List<Item> listByCategory(int cid) {
        Category category = categoryService.get(cid);
        return itemDAO.findAllByCategory(category);
    }

}
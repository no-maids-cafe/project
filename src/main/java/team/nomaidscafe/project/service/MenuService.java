package team.nomaidscafe.project.service;

import team.nomaidscafe.project.dao.MenuDAO;
import team.nomaidscafe.project.entity.Menu;
import team.nomaidscafe.project.entity.RoleMenu;
import team.nomaidscafe.project.entity.UserRole;
import team.nomaidscafe.project.entity.User;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class MenuService {
    @Autowired
    MenuDAO MenuDAO;
    @Autowired
    UserService userService;
    @Autowired
    UserRoleService UserRoleService;
    @Autowired
    RoleMenuService RoleMenuService;

    public List<Menu> getAllByParentId(int parentId) {
        return MenuDAO.findAllByParentId(parentId);
    }

    public List<Menu> getMenusByCurrentUser() {
        // Get current user in DB.
        String name = SecurityUtils.getSubject().getPrincipal().toString();
        User user = userService.findByName(name);

        // Get roles' ids of current user.
        List<Integer> rids = UserRoleService.listAllByUid(user.getId())
                .stream().map(UserRole::getRid).collect(Collectors.toList());

        // Get menu items of these roles.
        List<Integer> menuIds = RoleMenuService.findAllByRid(rids)
                .stream().map(RoleMenu::getMid).collect(Collectors.toList());
        List<Menu> menus = MenuDAO.findAllById(menuIds).stream().distinct().collect(Collectors.toList());

        // Adjust the structure of the menu.
        handleMenus(menus);
        return menus;
    }

    public List<Menu> getMenusByRoleId(int rid) {
        List<Integer> menuIds = RoleMenuService.findAllByRid(rid)
                .stream().map(RoleMenu::getMid).collect(Collectors.toList());
        List<Menu> menus = MenuDAO.findAllById(menuIds);

        handleMenus(menus);
        return menus;
    }

    /**
     * Adjust the Structure of the menu.
     *
     * @param menus Menu items list without structure
     */
    public void handleMenus(List<Menu> menus) {
        menus.forEach(m -> {
            List<Menu> children = getAllByParentId(m.getId());
            m.setChildren(children);
        });

        menus.removeIf(m -> m.getParentId() != 0);
    }
}

package team.nomaidscafe.project.service;

import team.nomaidscafe.project.dao.RoleDAO;
import team.nomaidscafe.project.entity.Menu;
import team.nomaidscafe.project.entity.Permission;
import team.nomaidscafe.project.entity.Role;
import team.nomaidscafe.project.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleService {
    @Autowired
    RoleDAO RoleDAO;
    @Autowired
    UserService userService;
    @Autowired
    UserRoleService UserRoleService;
    @Autowired
    PermissionService PermissionService;
    @Autowired
    RolePermissionService RolePermissionService;
    @Autowired
    MenuService MenuService;

    public List<Role> listWithPermsAndMenus() {
        List<Role> roles = RoleDAO.findAll();
        List<Permission> perms;
        List<Menu> menus;
        for (Role role : roles) {
            perms = PermissionService.listPermsByRoleId(role.getId());
            menus = MenuService.getMenusByRoleId(role.getId());
            role.setPerms(perms);
            role.setMenus(menus);
        }
        return roles;
    }

    public List<Role> findAll() {
        return RoleDAO.findAll();
    }


    public void addOrUpdate(Role Role) {
        RoleDAO.save(Role);
    }

    public List<Role> listRolesByUser(String name) {
        int uid = userService.findByName(name).getId();
        List<Integer> rids = UserRoleService.listAllByUid(uid)
                .stream().map(UserRole::getRid).collect(Collectors.toList());
        return RoleDAO.findAllById(rids);
    }

    public Role updateRoleStatus(Role role) {
        Role roleInDB = RoleDAO.findById(role.getId());
        roleInDB.setEnabled(role.isEnabled());
        return RoleDAO.save(roleInDB);
    }

    public void editRole(@RequestBody Role role) {
        RoleDAO.save(role);
        RolePermissionService.savePermChanges(role.getId(), role.getPerms());
    }
}

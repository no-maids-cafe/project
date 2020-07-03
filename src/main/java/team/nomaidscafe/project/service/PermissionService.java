package team.nomaidscafe.project.service;

import team.nomaidscafe.project.dao.PermissionDAO;
import team.nomaidscafe.project.dao.RolePermissionDAO;
import team.nomaidscafe.project.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class PermissionService {
    @Autowired
    PermissionDAO PermissionDAO;
    @Autowired
    UserRoleService UserRoleService;
    @Autowired
    RoleService RoleService;
    @Autowired
    RolePermissionService RolePermissionService;
    @Autowired
    RolePermissionDAO RolePermissionDAO;
    @Autowired
    UserService userService;

    public List<Permission> list() {return PermissionDAO.findAll();}

    /**
     * Determine whether client requires permission when requests
     * a certain API.
     * @param requestAPI API requested by client
     * @return true when requestAPI is found in the DB
     */
    public boolean needFilter(String requestAPI) {
        List<Permission> ps = PermissionDAO.findAll();
        for (Permission p: ps) {
            // match prefix
            if (requestAPI.startsWith(p.getUrl())) {
                return true;
            }
        }
        return false;
    }

    public List<Permission> listPermsByRoleId(int rid) {
        List<Integer> pids = RolePermissionService.findAllByRid(rid)
                .stream().map(RolePermission::getPid).collect(Collectors.toList());
        return PermissionDAO.findAllById(pids);
    }

    public Set<String> listPermissionURLsByUser(String username) {
        List<Integer> rids = RoleService.listRolesByUser(username)
                .stream().map(Role::getId).collect(Collectors.toList());

        List<Integer> pids = RolePermissionDAO.findAllByRid(rids)
                .stream().map(RolePermission::getPid).collect(Collectors.toList());

        List<Permission> perms = PermissionDAO.findAllById(pids);

        Set<String> URLs = perms.stream().map(Permission::getUrl).collect(Collectors.toSet());

        return URLs;
    }
}

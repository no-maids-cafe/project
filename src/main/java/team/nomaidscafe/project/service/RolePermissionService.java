package team.nomaidscafe.project.service;

import team.nomaidscafe.project.dao.RolePermissionDAO;
import team.nomaidscafe.project.entity.Permission;
import team.nomaidscafe.project.entity.RolePermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class RolePermissionService {
    @Autowired
    RolePermissionDAO RolePermissionDAO;

    List<RolePermission> findAllByRid(int rid) {
        return RolePermissionDAO.findAllByRid(rid);
    }

//    @Modifying
    @Transactional
    public void savePermChanges(int rid, List<Permission> perms) {
        RolePermissionDAO.deleteAllByRid(rid);
        List<RolePermission> rps = new ArrayList<>();
        perms.forEach(p -> {
            RolePermission rp = new RolePermission();
            rp.setRid(rid);
            rp.setPid(p.getId());
            rps.add(rp);
        });
        RolePermissionDAO.saveAll(rps);
    }
}

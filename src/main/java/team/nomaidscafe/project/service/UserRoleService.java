package team.nomaidscafe.project.service;

import team.nomaidscafe.project.dao.UserRoleDAO;
import team.nomaidscafe.project.entity.Role;
import team.nomaidscafe.project.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserRoleService {
    @Autowired
    UserRoleDAO UserRoleDAO;

    public List<UserRole> listAllByUid(int uid) {
        return UserRoleDAO.findAllByUid(uid);
    }

//    @Modifying
    @Transactional
    public void saveRoleChanges(int uid, List<Role> roles) {
        UserRoleDAO.deleteAllByUid(uid);
        List<UserRole> urs = new ArrayList<>();
        roles.forEach(r -> {
            UserRole ur = new UserRole();
            ur.setUid(uid);
            ur.setRid(r.getId());
            urs.add(ur);
        });
        UserRoleDAO.saveAll(urs);
    }
}

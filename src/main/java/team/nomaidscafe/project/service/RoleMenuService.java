package team.nomaidscafe.project.service;

import team.nomaidscafe.project.dao.RoleMenuDAO;
import team.nomaidscafe.project.entity.Role;
import team.nomaidscafe.project.entity.RoleMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Evan
 * @date 2019/11
 */
@Service
public class RoleMenuService {
    @Autowired
    RoleMenuDAO RoleMenuDAO;

    public List<RoleMenu> findAllByRid(int rid) {
        return RoleMenuDAO.findAllByRid(rid);
    }

    public List<RoleMenu> findAllByRid(List<Integer> rids) {
        return RoleMenuDAO.findAllByRid(rids);
    }

    public void save(RoleMenu rm) {
        RoleMenuDAO.save(rm);
    }

    @Modifying
    @Transactional
    public void updateRoleMenu(int rid, Map<String, List<Integer>> menusIds) {
        RoleMenuDAO.deleteAllByRid(rid);
        List<RoleMenu> rms = new ArrayList<>();
        for (Integer mid : menusIds.get("menusIds")) {
            RoleMenu rm = new RoleMenu();
            rm.setMid(mid);
            rm.setRid(rid);
            rms.add(rm);
        }

        RoleMenuDAO.saveAll(rms);
    }
}

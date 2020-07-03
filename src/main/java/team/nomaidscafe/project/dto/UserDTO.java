package team.nomaidscafe.project.dto;

import team.nomaidscafe.project.dto.base.OutputConverter;
import team.nomaidscafe.project.entity.Role;
import team.nomaidscafe.project.entity.User;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author Evan
 * @date 2020/4/1 19:57
 */
@Data
@ToString
public class UserDTO implements OutputConverter<UserDTO, User> {

    private int id;

    private String name;

    private String phone;

    private String email;

    private boolean enabled;

    private List<Role> roles;

}

package team.nomaidscafe.project.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * Role entity.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "role")
@ToString
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    /**
     * Role name.
     */
    private String name;

    /**
     * Role name in Chinese.
     */
    @Column(name = "name_zh")
    private String nameZh;

    /**
     * Role status.
     */
    private boolean enabled;


    /**
     * Transient property for storing permissions owned by current role.
     */
    @Transient
    private List<Permission> perms;

    /**
     * Transient property for storing menus owned by current role.
     */
    @Transient
    private List<Menu> menus;
}

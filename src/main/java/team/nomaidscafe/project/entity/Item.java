package team.nomaidscafe.project.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
// import javax.validation.constraints.NotEmpty;
// import javax.validation.constraints.NotNull;

/**
 * Item entity.
 */
@Data
@Entity
@Table(name = "item")
@ToString
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    /**
     * Nmae of the item.
     */
    private String name;

    /**
     * 
     */
    private int remain;

    /**
     * 
     */
    private float price;

    /**
     * The url of the item's cover.
     */
    private String desc;

    /**
     * 
     */
    private int sold;

}
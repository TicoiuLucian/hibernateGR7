package ro.itschool.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Fish extends Pet {

    private Integer neededSpace;

    private boolean freshWater;

    @ManyToOne
    @JoinTable(
            name = "fish_owner",
            joinColumns = {@JoinColumn(name = "fish_id")},
            inverseJoinColumns = {@JoinColumn(name = "owner_id")}
    )
    private Owner owner;


}

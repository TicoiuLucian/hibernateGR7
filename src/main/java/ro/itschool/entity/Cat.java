package ro.itschool.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Cat extends Pet {

    private Integer napHours;

    @ManyToMany(mappedBy = "cats")
    private List<Owner> owners = new ArrayList<>();

    public void addOwnerToOwners(Owner owner){
        this.owners.add(owner);
    }

}

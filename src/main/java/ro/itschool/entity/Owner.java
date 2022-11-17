package ro.itschool.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String phoneNumber;

    private String name;

    //FetchType.EAGER = aducem tot, inclusiv nested objects
    //FetchType.LAZY = aducem doar obiectul principal, fara nested objects
    //nested objects aducem doar daca sunt chemate
    @OneToOne
    private Dog dog;

    @OneToMany(mappedBy = "owner")
    private List<Fish> fish = new ArrayList<>();


    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Cat> cats = new ArrayList<>();

    public void addFishToList(Fish f) {
        this.fish.add(f);
        f.setOwner(this);
    }

    public void addCatsToList(Cat cat) {
        this.cats.add(cat);
        cat.addOwnerToOwners(this);
    }

    @Override
    public String toString() {
        return "Owner{" +
                "id=" + id +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", name='" + name + '\'' +
                ", dog=" + dog.name +
                '}';
    }
}

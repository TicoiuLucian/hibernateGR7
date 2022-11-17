package ro.itschool.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Dog extends Pet {

    private String hairColour;

    @OneToOne(mappedBy = "dog")
    private Owner owner;

    @Override
    public String toString() {
        return "Dog{" +
                "hairColour='" + hairColour + '\'' +
                ", owner=" + owner.getName() +
                ", id=" + id +
                ", price=" + price +
                ", name='" + name + '\'' +
                ", food=" + food +
                '}';
    }
}

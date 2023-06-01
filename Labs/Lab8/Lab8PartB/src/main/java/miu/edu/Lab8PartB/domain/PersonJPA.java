package miu.edu.Lab8PartB.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class PersonJPA {

    @Id
    @GeneratedValue
    private int id;




    private String firstName;
    private String lastName;

    @OneToMany(cascade = {CascadeType.PERSIST})
    private List<PetJPA> pets = new ArrayList<>();

    public PersonJPA() {
    }

    public PersonJPA(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<PetJPA> getPets() {
        return pets;
    }

    public void setPets(List<PetJPA> pets) {
        this.pets = pets;
    }

    public void addPet(PetJPA pet) {
        pets.add(pet);
    }
}

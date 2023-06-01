package miu.edu.Lab8PartB.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
public class PersonMongo {

    @Id
    private int id;

    private String firstName;
    private String lastName;

    private List<PetMongo> pets = new ArrayList<>();


    
    public PersonMongo(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void addPet(PetMongo pet) {
        pets.add(pet);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<PetMongo> getPets() {
        return pets;
    }

    public void setPets(List<PetMongo> pets) {
        this.pets = pets;
    }
}

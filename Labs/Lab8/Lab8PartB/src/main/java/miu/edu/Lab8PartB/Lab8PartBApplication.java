package miu.edu.Lab8PartB;

import miu.edu.Lab8PartB.Repository.PersonJpaRepository;
import miu.edu.Lab8PartB.Repository.PersonMongoRepository;
import miu.edu.Lab8PartB.Repository.PetJpaRepository;
import miu.edu.Lab8PartB.domain.PersonJPA;
import miu.edu.Lab8PartB.domain.PersonMongo;
import miu.edu.Lab8PartB.domain.PetJPA;
import miu.edu.Lab8PartB.domain.PetMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Lab8PartBApplication implements CommandLineRunner {

	@Autowired
	private PersonJpaRepository personJpaRepository;

	@Autowired
	private PersonMongoRepository personMongoRepository;

	public static void main(String[] args) {
		SpringApplication.run(Lab8PartBApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		StopWatch sw = new StopWatch();
		System.out.println("----------Watch save 10.000 person with JPA to HSQL");
		sw.start();
		savePersonUsingJpa();
		sw.stop();
		System.out.println(sw.getTotalTimeSeconds());

		System.out.println("----------Watch save 10.000 person with MongoDB");
		sw.start();
		savePersonUsingMongo();
		sw.stop();
		System.out.println(sw.getTotalTimeSeconds());

		System.out.println("----------Watch get all 10.000 person with JPA");
		sw.start();
		getAllPersonUsingJPA();
		sw.stop();
		System.out.println(sw.getTotalTimeSeconds());

		System.out.println("----------Watch get all 10.000 person with MongoDB");
		sw.start();
		getAllPersonUsingMongo();
		sw.stop();
		System.out.println(sw.getTotalTimeSeconds());

	}

	public void savePersonUsingJpa() {
		for (int i  = 0; i < 10000; i++) {
			PersonJPA person = new PersonJPA("First "+i,"Last "+i);

			for (int j = 0; j < 10; j++) {
				PetJPA pet = new PetJPA("Name"+j, j+1);
				person.addPet(pet);
			}
			personJpaRepository.save(person);
		}
	}

	public void getAllPersonUsingJPA() {
		personJpaRepository.findAll();
	}

	public void savePersonUsingMongo() {
		for (int i  = 0; i < 10000; i++) {
			PersonMongo person = new PersonMongo(i, "First "+i,"Last "+i);

			for (int j = 0; j < 10; j++) {
				PetMongo pet = new PetMongo("Name"+j, j+1);
				person.addPet(pet);
			}
			personMongoRepository.save(person);
		}
	}

	public void getAllPersonUsingMongo() {
		personMongoRepository.findAll();
	}
}

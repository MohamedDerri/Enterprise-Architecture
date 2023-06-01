package app;

import java.util.List;
import java.util.Optional;

import domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import repositories.BookRepository;
import repositories.CustomerRepository;
import domain.Customer;

@SpringBootApplication
@EnableJpaRepositories("repositories")
@EntityScan("domain") 
public class CustomerApplication implements CommandLineRunner{
	
	@Autowired
	CustomerRepository customerrepository;

	@Autowired
	BookRepository bookRepository;

	public static void main(String[] args) {
		SpringApplication.run(CustomerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		customerrepository.save(new Customer("said", "derri", "sderri@acme.com"));
		customerrepository.save(new Customer("med", "derri", "mderri@acme.com"));
		customerrepository.save(new Customer("aicha", "derri", "aderri@acme.com"));


		
		System.out.println("findAll(): ======>");
		for (Customer customer : customerrepository.findAll()) {
			System.out.println(customer);
		}
		System.out.println();

		// search customer by ID
		Optional<Customer> custOpt = customerrepository.findById(1L);
		Customer customer = custOpt.get();
		System.out.println("findOne(1L):=======W");
		System.out.println(customer);
		System.out.println();

		System.out.println("saving a book=======>");
		bookRepository.save(new Book("Hands on machine learning with scickit learn keras and tensorflow", "K165723", "unknown", 198));


		System.out.println("Printing all book========>");
		printAllBook();

		System.out.println("Update a book=========>");
		Optional<Book> bookOptional = bookRepository.findById(6);
		Book book = bookOptional.get();
		book.setPrice(256.99);
		bookRepository.save(book);

		System.out.println("Delete a book==========>");
		bookRepository.deleteById(8);//doesn't exist

		System.out.println("Print all book after delete======>");
		printAllBook();
	}

	private void printAllBook() {
		List<Book> books = bookRepository.findAll();
		for (Book book: books) {
			System.out.println(book);
		}
	}
}

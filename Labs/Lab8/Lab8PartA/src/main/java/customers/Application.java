package customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.Query;

import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private StudentRepository studentRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
        // create customer
		Customer customer = new Customer(101,"John doe", "johnd@acme.com", "0622341678");
		CreditCard creditCard = new CreditCard("12324564321", "Visa", "11/23");
		customer.setCreditCard(creditCard);
		customerRepository.save(customer);
		customer = new Customer(109,"John Jones", "jones@acme.com", "0624321234");
		creditCard = new CreditCard("657483342", "Visa", "09/23");
		customer.setCreditCard(creditCard);
		customerRepository.save(customer);
		customer = new Customer(66,"James Johnson", "jj123@acme.com", "068633452");
		creditCard = new CreditCard("99876549876", "MasterCard", "01/24");
		customer.setCreditCard(creditCard);
		customerRepository.save(customer);
		//get customers
		System.out.println(customerRepository.findById(66).get());
		System.out.println(customerRepository.findById(101).get());
		System.out.println("-----------All customers ----------------");
		System.out.println(customerRepository.findAll());
		//update customer
		customer = customerRepository.findById(101).get();
		customer.setEmail("jd@gmail.com");
		customerRepository.save(customer);
		System.out.println("-----------find by phone ----------------");
		System.out.println(customerRepository.findByPhone("0622341678"));
		System.out.println("-----------find by email ----------------");
		System.out.println(customerRepository.findCustomerWithEmail("jj123@acme.com"));
		System.out.println("-----------find customers with a certain type of creditcard ----------------");
		List<Customer> customers = customerRepository.findByCreditCardType("Visa");
		for (Customer cust : customers){
			System.out.println(cust);
		}

//		student
		Student s1 = new Student(1001, "Jack", "123456789");
		Address add1 = new Address("Berlington", "Fairfield", "52556");
		s1.setAddress(add1);
		Grade g1 = new Grade("Java", "A");
		Grade g2 = new Grade("Python", "B");
		Grade g3 = new Grade("EA", "A");
		List<Grade> grades1 = new ArrayList<>();
		grades1.add(g1);grades1.add(g2);grades1.add(g3);
		s1.setGrades(grades1);
		studentRepository.save(s1);

		Student s2 = new Student(1002, "Mary", "864456789");
		Address add2 = new Address("Avine", "Ottomwa", "52556");
		s2.setAddress(add2);
		Grade g4 = new Grade("Python", "A");
		Grade g5 = new Grade("EA", "B");
		List<Grade> grades2 = new ArrayList<>();
		grades2.add(g4);grades2.add(g5);
		s2.setGrades(grades2);
		studentRepository.save(s2);

//		Find Student By Name
		System.out.println(studentRepository.findByName("Jack"));
//		Find Student By Phone
		System.out.println(studentRepository.findByPhone("864456789"));
//		Find Student By City
		System.out.println(studentRepository.findByAddressCity("Fairfield"));
//		Find Students learn Java
		System.out.println(studentRepository.findByGradesCourseName("Java"));
//		Find Students get A of Python
		System.out.println("Find Students get A of Python");
		//System.out.println(studentRepository.findByGradesCourseNameAndGradesGrade("Python", "A"));
		List<Student> students = studentRepository.findStudentsByCourseName("Python");
		//System.out.println(studentRepository.findStudentsByCourseName("Python"));
		System.out.println(students);
	}

}

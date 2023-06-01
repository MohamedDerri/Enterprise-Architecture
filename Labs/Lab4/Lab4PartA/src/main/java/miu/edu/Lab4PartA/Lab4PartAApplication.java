package miu.edu.Lab4PartA;

import miu.edu.Lab4PartA.a.Department;
import miu.edu.Lab4PartA.a.DepartmentDAO;
import miu.edu.Lab4PartA.a.Employee;
import miu.edu.Lab4PartA.b.Book;
import miu.edu.Lab4PartA.b.BookDAO;
import miu.edu.Lab4PartA.b.Publisher;
import miu.edu.Lab4PartA.c.Flight;
import miu.edu.Lab4PartA.c.Passenger;
import miu.edu.Lab4PartA.c.PassengerDAO;
import miu.edu.Lab4PartA.d.School;
import miu.edu.Lab4PartA.d.SchoolDAO;
import miu.edu.Lab4PartA.d.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.*;

@SpringBootApplication
public class Lab4PartAApplication implements CommandLineRunner {

	@Autowired
	private DepartmentDAO departmentDAO;

	@Autowired
	private BookDAO bookDAO;

	@Autowired
	private PassengerDAO passengerDAO;

	@Autowired
	private SchoolDAO schoolDAO;


	public static void main(String[] args) {
		SpringApplication.run(Lab4PartAApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("part A ==================>");
		Department department = new Department("computer scince");
		Employee e1 = new Employee("mohamed");
		e1.setDepartment(department);
		Employee e2 = new Employee("derri");
		e2.setDepartment(department);
		List<Employee> employees = new ArrayList<>();
		employees.add(e1);
		employees.add(e2);
		department.setEmployees(employees);

		System.out.println("save ==================>");
		departmentDAO.save(department);
		System.out.println("retrieve ==================>");
		Optional<Department> optionalDepartment = departmentDAO.findById(1);
		Department dept = optionalDepartment.get();
		System.out.println(dept);

		System.out.println("part B ==================>");
		Book book1 = new Book(123567, "Hands on machine learning with scickit learn, keras and tensoflow", "aurélien géron");
		book1.setPublisher(new Publisher("o'reilly"));
		bookDAO.save(book1);
		Book book2 = new Book(123568, "Hands on machine learning with scickit learn, keras and tensoflow second edition", "aurélien géron");
		bookDAO.save(book2);
		System.out.println(bookDAO.findAll());

		System.out.println("part C ==================>");
		Passenger passenger = new Passenger("med");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Flight flight1 = new Flight("flight1", "new york", "Des Moines", dateFormat.parse("2023-04-06"));
		List<Flight> flights = new ArrayList<>();
		flights.add(flight1);
		passenger.setFlights(flights);
		passengerDAO.save(passenger);

		System.out.println("part D ==================>");
		School school = new School("MIU");
		Student student1 = new Student("med", "derri");
		Student student2 = new Student("john", "mount");
		Map<Integer, Student> students = new HashMap<>();
		students.put(student1.getStudentid(), student1);
		students.put(student2.getStudentid(), student2);
		school.setStudents(students);
		schoolDAO.save(school);
	}
}

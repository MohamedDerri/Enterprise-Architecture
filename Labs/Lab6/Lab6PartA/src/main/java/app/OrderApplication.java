package app;

import domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import repositories.AddressRepository;
import repositories.CDRepository;
import repositories.CustomerRepository;
import repositories.OrderRepository;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
@EnableJpaRepositories("repositories")
@EntityScan("domain") 
public class OrderApplication implements CommandLineRunner{
	@Autowired
	OrderRepository orderRepository;
	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	CDRepository cdRepository;
	@Autowired
	AddressRepository addressRepository;

	public static void main(String[] args) {
		SpringApplication.run(OrderApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Order o1 = createOrder1();


		orderRepository.save(o1);

        getAllCustomers();
		getAllCDFromU2WithPriceSmallerThanTen();
		
		
		
		getAllCustomersFromUSA();
		getAllCDsFromCertainArtist();


		getOrderNumbersFromOrdersWithStatusClosed();
		getAllCustomersFromAmsterdam();
		getOrderNumbersFromCustomersInCity();
		getAllCDsFromArtistAndPriceBiggerThan();



		getAllAddressesfromAmsterdam();
		getAllCDsFromU2();
		
	}
	
	private void getAllCustomers() {
		List<Customer> customers= customerRepository.findAll();
		System.out.println("=======> all cust:");
		customers.stream().forEach(c->System.out.println(c));
	}

	// ------------------------------------------------------------

	private void getAllCDFromU2WithPriceSmallerThan10() {
		List<CD> cds=cdRepository.findByArtistAndPriceGreaterThan("pnl", 10.0);
		System.out.println("===========> cds with price > 10");
		cds.stream().forEach(c->System.out.println(c));
	}

	// ------------------------------------------------------------

	private void getAllCDsFromArtist() {
		List<CD> cds=cdRepository.findByArtist("pnl");
		System.out.println("all cds");
		cds.stream().forEach(c->System.out.println(c));
	}

	// ------------------------------------------------------------


	private void getOrderNumbersFromCustomersInCity() {
		List<String> orders = orderRepository.getOrderNumbersFromOrdersFromCity("Amsterdam");
		System.out.println("===========> ");
		orders.stream().forEach(c->System.out.println(c));
	}


	// ------------------------------------------------------------

	private void getOrderNumbersFromOrdersWithStatusClosed() {
		List<String> orders = orderRepository.getOrderNumbersFromOrdersWithStatusClosed();
		System.out.println("==============>");
		orders.stream().forEach(c->System.out.println(c));
	}


	// ------------------------------------------------------------


	private void getAllCustomersFromAmsterdam() {
		List<Customer> customers = customerRepository.getAllCustomersFromCity("Amsterdam");
		System.out.println("==============>");
		customers.stream().forEach(c->System.out.println(c));
	}

	// ------------------------------------------------------------


	private void getAllAddressesfromAmsterdam() {
		List<Address> addresses = addressRepository.getAllAddressesFromCity("Amsterdam");
		System.out.println("==============>");
		addresses.stream().forEach(c->System.out.println(c));
	}


	// ------------------------------------------------------------


	private void getAllCustomersFromUSA() {
		List<Customer> customers = customerRepository.getAllCustomersFrom("USA");
		System.out.println("==============>");
		customers.stream().forEach(c->System.out.println(c));
	}


	// ------------------------------------------------------------


	private void getAllCDsFromU2() {
		List<CD> cds=cdRepository.findByAnArtist("pnl");
		System.out.println("==============>");
		cds.stream().forEach(c->System.out.println(c));
	}



	// ------------------------------------------------------------

	private void getAllCDsFromArtistAndPriceBiggerThan() {
		List<CD> cds=cdRepository.getAllCDsFromArtistAndPriceBiggerThan("pnl",9.0);
		System.out.println("==============>");
		cds.stream().forEach(c->System.out.println(c));
	}


	// ------------------------------------------------------------








	public static void printOrder(Order order){
		System.out.println("Order with orderNumber: "+order.getOrdernr());
		System.out.println("Order date: "+order.getDate());
		System.out.println("Order status: "+order.getStatus());
		Customer cust=order.getCustomer();
		System.out.println("Customer: "+cust.getFirstname()+" "+cust.getLastname());
		System.out.println("Address: "+cust.getAddress().getStreet()+" "+cust.getAddress().getCity()+cust.getAddress().getZip()+" "+cust.getAddress().getCountry());

		Iterator iter = order.getOrderlines().iterator();
		while (iter.hasNext()) {
			OrderLine orderline = (OrderLine) iter.next();
			System.out.println("Order line: quantity= " + orderline.getQuantity());
			System.out.println(orderline.getProduct());
		}
	}


	private static Order createOrder() {
		Customer cus = new Customer("Med", "derri");
		Address add= new Address("1000 nth 4th street", "fairfield", "52557", "USA");
		cus.setAddress(add);
		Book book = new Book();
		book.setName("competitive programming");
		book.setDescription("learn the best practices on how to be good at problem solving");
		book.setPrice(67.00);
		book.setTitle("competitive programming");
		OrderLine ol1= new OrderLine(200);
		ol1.setProduct(book);

		CD cd = new CD();
		cd.setName("name");
		cd.setDescription("desc");
		cd.setPrice(122);
		cd.setArtist("pnl");
		OrderLine ol2= new OrderLine(100);
		ol2.setProduct(cd);

		DVD dvd = new DVD();
		dvd.setName("dvd1");
		dvd.setDescription("desc for dvd1");
		dvd.setPrice(156.0);
		dvd.setGenre("unknown");
		OrderLine ol3= new OrderLine(126);
		ol3.setProduct(dvd);

		Order o1 = new Order("0001","04/09/2023","available");
		o1.addOrderLine(ol1);
		o1.addOrderLine(ol3);
		o1.setCustomer(c1);
		return o1;
	}


}


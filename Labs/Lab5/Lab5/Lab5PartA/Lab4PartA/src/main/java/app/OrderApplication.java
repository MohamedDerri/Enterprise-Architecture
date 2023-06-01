package app;

import domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import repositories.OrderRepository;

import java.util.Iterator;
import java.util.Optional;

@SpringBootApplication
@EnableJpaRepositories("repositories")
@EntityScan("domain") 
public class OrderApplication implements CommandLineRunner{
	@Autowired
	OrderRepository orderRepository;

	public static void main(String[] args) {
		SpringApplication.run(OrderApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Order o1 = addNewOrder();
		Order o2 = addNewOrder();
		orderRepository.save(o1);
		orderRepository.save(o2);
		Optional<Order> orderOpt = orderRepository.findById(1L);
		Order order = orderOpt.get();
		printOrder(order);
	}


	public static void printOrder(Order order){
		System.out.println("Order with orderNumber: "+order.getOrdernr());
		System.out.println("Order date: "+order.getDate());
		System.out.println("Order status: "+order.getStatus());
		Customer cust=order.getCustomer();
		System.out.println("Customer: "+cust.getFirstname()+" "+cust.getLastname());
		System.out.println("Address: "+cust.getAddress().getStreet()+" "+cust.getAddress().getCity()+cust.getAddress().getZip()+" "+cust.getAddress().getCountry());

		for (OrderLine orderline : order.getOrderlines()) {
			System.out.println("Order line: quantity= "
					+ orderline.getQuantity());
			Product product = orderline.getProduct();
			System.out.println("Product: " + product.getName() + " "
					+ product.getDescription() + " " + product.getPrice());
		}
	}




	private static Order addNewOrder() {
		Customer c1 = new Customer("med", "derri");
		Address a1= new Address("1000 Nth 4 street", "fairfield", "52557", "USA");
		c1.setAddress(a1);
		Book book = new Book();
		book.setName("Hibernate 3");
		book.setDescription("Good book on Hibernate");
		book.setPrice(35.50);
		book.setTitle("Hibernate in action");
		OrderLine ol1= new OrderLine(100);
		ol1.setProduct(book);

		CD cd = new CD();
		cd.setName("cd 1");
		cd.setDescription("album 1");
		cd.setPrice(12222);
		cd.setArtist("artist 1");
		OrderLine ol2= new OrderLine(4);
		ol2.setProduct(cd);

		Order o1 = new Order("1","04/08/2023","en cours");
		o1.addOrderLine(ol1);
		o1.addOrderLine(ol2);
		o1.setCustomer(c1);
		return o1;
	}


}
package restClient.BookRestClient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
public class BookRestClientApplication implements CommandLineRunner {

	RestTemplate restTemplate = new RestTemplate();
	private String baseUrl = "http://localhost:8080/books";

	public static void main(String[] args) {
		SpringApplication.run(BookRestClientApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//--------------------- adding books -------------------------
		restTemplate.postForLocation(baseUrl, new Book("pq400", "advanced probability", 399.99, "mohamed"));

		Book book = restTemplate.getForObject(baseUrl+"/pq200", Book.class);
		System.out.println("book with isbn pq200 = " + book.getTitle());


		//--------------------------- GetAll ------------------------
		Books books = restTemplate.getForObject(baseUrl, Books.class);
		System.out.println("all book --> : ");
		for (Book bookk : books.getBooks()) {
			System.out.println("Book's title ="+bookk.getTitle());
		}


		//--------------------------- books belong to author mohamed ------------------------
		Books authorbooks = restTemplate.getForObject("http://localhost:8080/searchbooks/mohamed", Books.class);
		for (Book bookkk : authorbooks.getBooks()) {
			System.out.println("Book's title ="+bookkk.getTitle());
		}
	}
}

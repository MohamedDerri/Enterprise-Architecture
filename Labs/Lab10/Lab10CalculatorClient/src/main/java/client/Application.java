package client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	CalculatorClient calculatorClient;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Inputs num = new Inputs(998,67);
        System.out.println("test");

		System.out.println( num.getX()+" + "+num.getY()+ " = "+calculatorClient.getResultOfAdd(num));
		System.out.println(num.getX()+" - "+num.getY()+ " = "+ calculatorClient.getResultOfSubtraction(num));
	}

}



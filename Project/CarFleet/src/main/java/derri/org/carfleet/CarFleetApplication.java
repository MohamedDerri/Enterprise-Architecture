package derri.org.carfleet;

import derri.org.carfleet.service.ICarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CarFleetApplication implements CommandLineRunner {

    @Autowired
    private ICarService carService;

    public static void main(String[] args) {
        SpringApplication.run(CarFleetApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}

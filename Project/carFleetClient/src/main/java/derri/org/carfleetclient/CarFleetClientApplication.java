package derri.org.carfleetclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CarFleetClientApplication implements CommandLineRunner {

    @Autowired
    CarFleetSupplier carFleetSupplier;

    public static void main(String[] args) {
        SpringApplication.run(CarFleetClientApplication.class, args);
        System.out.println("starting the client ....");
    }

    @Override
    public void run(String... args) throws Exception {

        carFleetSupplier.addCar("x12", "Model x", "Tesla", 35.00);
        carFleetSupplier.addCar("x22", "Model x", "Tesla", 35.00);
        carFleetSupplier.addCar("x33", "Model x", "Tesla", 35.00);
        carFleetSupplier.addCar("x44", "Model x", "Tesla", 35.00);
        carFleetSupplier.addCar("g12", "class G", "mercedes", 50.00);
        carFleetSupplier.addCar("G22", "class G", "mercedes", 50.00);
        carFleetSupplier.addCar("g23", "class G", "mercedes", 50.00);
        carFleetSupplier.addCar("g24", "class G", "mercedes", 50.00);
        carFleetSupplier.addCar("a12", "A3", "Audi", 44.00);
        carFleetSupplier.addCar("a22", "A3", "Audi", 44.00);
        carFleetSupplier.addCar("a32", "A3", "Audi", 44.00);
        carFleetSupplier.addCar("a42", "A3", "Audi", 44.00);
        carFleetSupplier.addCar("m12", "m3", "bmw", 23.91);
        carFleetSupplier.addCar("m22", "m3", "bmw", 23.91);
        carFleetSupplier.addCar("m23", "m3", "bmw", 23.91);
        carFleetSupplier.addCar("m24", "m3", "bmw", 23.91);
        carFleetSupplier.addCar("g56", "golf 7.5", "volkswagen", 20.15);
        carFleetSupplier.addCar("g78", "golf 7.5", "volkswagen", 20.15);

        System.out.println(carFleetSupplier.getAllCar());

        System.out.println(carFleetSupplier.searchCar("A3", "Audi", 44.00));



    }
}

package derri.org.carfleetclient;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CarFleetSupplier {

    RestTemplate restTemplate = new RestTemplate();
    private String serverUrl = "http://localhost:8080/cars";

    public CarDTO addCar (String licensePlate, String type, String brand, double price) {
        CarDTO newCar = new CarDTO(licensePlate, type, brand, price, false);
        restTemplate.postForLocation(serverUrl, newCar);
        return newCar;
    }

    public CarDTO getCar (String licensePlate) {
        CarDTO car = restTemplate.getForObject(serverUrl+"/{licensePlate}", CarDTO.class, licensePlate);
        return car;
    }

    public CarsDTO getAllCar () {
        CarsDTO cars = restTemplate.getForObject(serverUrl, CarsDTO.class);
        return cars;
    }

    public void removeCar (String licensePlate) {
        restTemplate.delete(serverUrl+"/{licensePlate}", licensePlate);
    }

    public CarDTO updateCar (CarDTO updatedCar) {
        CarDTO currentCar = getCar(updatedCar.getLicensePlate());
        if (currentCar == null) {
            return null;
        } else {
            restTemplate.put(serverUrl+"/{licensePlate}", updatedCar, updatedCar.getLicensePlate());
        }
        return updatedCar;
    }

    public CarsDTO searchCar (String type, String brand, Double price) {
        CarsDTO cars =  restTemplate.getForObject(serverUrl+"/search?type={type}&brand={brand}&price={price}",
                CarsDTO.class,
                type, brand, price);
        if (cars == null) {
            System.out.println("the cars you are searching is not available, try another time");
        }
        return cars;
    }
}

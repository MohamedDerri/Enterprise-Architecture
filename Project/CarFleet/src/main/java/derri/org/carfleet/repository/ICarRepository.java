package derri.org.carfleet.repository;

import derri.org.carfleet.model.Car;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICarRepository extends MongoRepository<Car, String> {
    List<Car> findAll();
    List<Car> findByBrand(String brand);
    List<Car> findCarByType(String type);
    List<Car> findByPriceLessThan(double price);
    List<Car> findCarByPrice(double price);
    List<Car> findCarByReserved(boolean reserved);
    Car findCarByLicensePlate(String licencePlate);

    void deleteCarByLicensePlate(String licencePlate);

    List<Car> findByTypeAndBrandAndPrice(String type, String brand, Double price);

    List<Car> findByTypeAndBrand(String type, String brand);

    List<Car> findByTypeAndPrice(String type, Double price);

    List<Car> findByBrandAndPrice(String brand, Double price);

    List<Car> findByType(String type);


    //List<Car> searchCars(String type, String brand, Double price, String licensePlate);
}

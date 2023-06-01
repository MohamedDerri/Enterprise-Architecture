package derri.org.carfleet.service;

import derri.org.carfleet.dto.CarDTO;
import derri.org.carfleet.model.Car;

import java.util.List;

public interface ICarService {

    CarDTO saveCar(CarDTO carDTO);
    boolean removeCar(String licensePlate);
    CarDTO findCarByLicensePlate(String licensePlate);
    List<CarDTO> findCarsByBrand(String brand);
    List<CarDTO> findCarsByType(String type);
    public List<CarDTO> searchCar (String type, String brand, Double price);
    List<CarDTO> findAllCars();

    //List<Car> searchCars(String type, String brand, Double price, String licensePlate);
}

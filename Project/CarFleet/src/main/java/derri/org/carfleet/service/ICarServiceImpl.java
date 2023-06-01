package derri.org.carfleet.service;

import derri.org.carfleet.dto.CarAdapter;
import derri.org.carfleet.dto.CarDTO;
import derri.org.carfleet.model.Car;
import derri.org.carfleet.repository.ICarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ICarServiceImpl implements ICarService {
    @Autowired
    private ICarRepository ICarRepository;
    Logger logger = LoggerFactory.getLogger(ICarServiceImpl.class);

    @Override
    public CarDTO saveCar(CarDTO carDTO) {
        Car car = CarAdapter.fromDTO(carDTO);
        if (car == null) {
            logger.error("Error saving this car");
            return null;
        }
        else {
            ICarRepository.save(car);
            logger.info("Car with licencePlate " + car.getLicensePlate() + " has been successfully saved");
        }
        return carDTO;
    }

    @Override
    public boolean removeCar(String licensePlate) {
        Optional<Car> carOptional = Optional.ofNullable(ICarRepository.findCarByLicensePlate(licensePlate));
        if (carOptional.isPresent()) {
            ICarRepository.deleteCarByLicensePlate(licensePlate);
            logger.info("car with licenPlate " + licensePlate + "has been deleted");
            return true;
        } else {
            logger.error("Car with license plate " + licensePlate + " cannot be deleted");
        }
        return false;
    }


    @Override
    public CarDTO findCarByLicensePlate(String licensePlate) {
        Optional<Car> carOptional = Optional.ofNullable(ICarRepository.findCarByLicensePlate(licensePlate));
        Car car = new Car();
        if (carOptional.isPresent()) {
            car = carOptional.get();
        } else {
            logger.error("Car with license plate " + licensePlate + " cannot be found");
            return null;
        }
        return CarAdapter.toDTO(car);
    }

    @Override
    public List<CarDTO> findCarsByBrand(String brand) {
        Optional<List<Car>> carOptional = Optional.ofNullable(ICarRepository.findByBrand(brand));
        List<Car> cars = new ArrayList<>();
        if (carOptional.isPresent()) {
            cars = carOptional.get();
        } else {
            logger.error("List of Cars with brand " + brand + " cannot be found");
            return null;
        }
        return CarAdapter.carsDtoFromcars(cars);
    }

    @Override
    public List<CarDTO> findCarsByType(String type) {
        Optional<List<Car>> carOptional = Optional.ofNullable(ICarRepository.findCarByType(type));
        List<Car> cars = new ArrayList<>();
        if (carOptional.isPresent()) {
            cars = carOptional.get();
        } else {
            logger.error("List of Cars with brand " + type + " cannot be found");
            return null;
        }
        return CarAdapter.carsDtoFromcars(cars);
    }

    @Override
    public List<CarDTO> searchCar(String type, String brand, Double price) {
        if (type == null && brand == null && price == null) {
            logger.error("Can't search car");
            return null;
        }

        List<Car> res;
        if (type != null && brand != null && price != null) {
            res = ICarRepository.findByTypeAndBrandAndPrice(type, brand, price);
        } else if (type != null && brand != null) {
            res = ICarRepository.findByTypeAndBrand(type, brand);
        } else if (type != null && price != null) {
            res = ICarRepository.findByTypeAndPrice(type, price);
        } else if (brand != null && price != null) {
            res = ICarRepository.findByBrandAndPrice(brand, price);
        } else if (type != null) {
            res = ICarRepository.findCarByType(type);
        } else if (brand != null) {
            res = ICarRepository.findByBrand(brand);
        } else {
            res = ICarRepository.findCarByPrice(price);
        }

        if (res.isEmpty()) {
            logger.error("search performed, empty list of as search result");
            return Collections.emptyList();
        }

        return CarAdapter.carsDtoFromcars(res);
    }

    @Override
    public List<CarDTO> findAllCars() {
        List<Car> cars = ICarRepository.findAll();
        return CarAdapter.carsDtoFromcars(cars);
    }



    /*@Override
    public List<Car> searchCars(String type, String brand, Double price, String licensePlate) {
        return carRepository.searchCars(type, brand, price, licensePlate);
    }*/
}


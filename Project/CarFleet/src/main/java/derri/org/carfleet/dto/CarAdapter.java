package derri.org.carfleet.dto;

import derri.org.carfleet.model.Car;
import derri.org.carfleet.model.Cars;

import java.util.ArrayList;
import java.util.List;

public class CarAdapter {

    public static CarDTO toDTO(Car car) {
        CarDTO carDTO = new CarDTO();
        carDTO.setType(car.getType());
        carDTO.setBrand(car.getBrand());
        carDTO.setLicensePlate(car.getLicensePlate());
        carDTO.setPrice(car.getPrice());
        carDTO.setReserved(car.isReserved());
        return carDTO;
    }

    public static Car fromDTO(CarDTO carDTO) {
        Car car = new Car();
        car.setType(carDTO.getType());
        car.setBrand(carDTO.getBrand());
        car.setLicensePlate(carDTO.getLicensePlate());
        car.setPrice(carDTO.getPrice());
        car.setReserved(carDTO.isReserved());
        return car;
    }

    public static List<CarDTO> carsDtoFromcars(List<Car> cars) {
        List<CarDTO> carDTOList = new ArrayList<>();
        for (Car car : cars ) {
            carDTOList.add(toDTO(car));
        }
        return carDTOList;
    }
}

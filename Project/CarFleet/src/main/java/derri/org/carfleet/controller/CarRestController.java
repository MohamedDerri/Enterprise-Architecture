package derri.org.carfleet.controller;

import derri.org.carfleet.dto.CarDTO;
import derri.org.carfleet.dto.CarsDTO;
import derri.org.carfleet.model.Car;
import derri.org.carfleet.model.Cars;
import derri.org.carfleet.service.ICarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarRestController {
    @Autowired
    private ICarService ICarService;

    @GetMapping("/{licensePlate}")
    public ResponseEntity<?> getCar(@PathVariable String licensePlate) {
        CarDTO carDTO = ICarService.findCarByLicensePlate(licensePlate);
        if (carDTO != null) {
            return ResponseEntity.ok(carDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllCars() {
        CarsDTO allcars = new CarsDTO(ICarService.findAllCars());
        return ResponseEntity.ok(allcars);
    }

    @PostMapping
    public ResponseEntity<?> createCar(@RequestBody CarDTO carDTO) {
        CarDTO newCar = ICarService.saveCar(carDTO);
        return ResponseEntity.created(URI.create("/cars/" + newCar.getLicensePlate())).body(newCar);
    }

    @PutMapping("/{licensePlate}")
    public ResponseEntity<?> updateCar(@PathVariable String licensePlate, @RequestBody CarDTO carDTO) {
        CarDTO savedCar = ICarService.findCarByLicensePlate(licensePlate);
        if (savedCar != null) {
            savedCar.setBrand(carDTO.getBrand());
            savedCar.setType(carDTO.getType());
            savedCar.setPrice(carDTO.getPrice());
            savedCar.setReserved(carDTO.isReserved());
            ICarService.saveCar(savedCar);
            return ResponseEntity.ok(savedCar);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{licensePlate}")
    public ResponseEntity<Void> deleteCar(@PathVariable String licensePlate) {
        boolean deleted = ICarService.removeCar(licensePlate);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /*@GetMapping
    public List<Car> searchCars(@RequestParam(required = false) String type,
                                @RequestParam(required = false) String brand,
                                @RequestParam(required = false) Double price,
                                @RequestParam(required = false) String licensePlate) {
        return carService.searchCars(type, brand, price, licensePlate);
    }*/

    @GetMapping("/cars/search")
    public ResponseEntity<?> searchCar (
            @RequestParam(value = "type", required = false, defaultValue = "") String type,
            @RequestParam(value = "brand", required = false, defaultValue = "") String brand,
            @RequestParam(value = "price", required = false, defaultValue = "0") Double price) {

        CarsDTO cars = new CarsDTO();
        cars.setCars(ICarService.searchCar(type.equals("") ? null : type, brand.equals("") ? null : brand, price == 0 ? null : price));
        if (cars == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cars);
    }
}

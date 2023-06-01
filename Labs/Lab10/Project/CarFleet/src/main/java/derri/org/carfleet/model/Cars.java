package derri.org.carfleet.model;

import derri.org.carfleet.model.Car;

import java.util.Collection;

public class Cars {
    private Collection<Car> cars;

    public Cars(Collection<Car> cars) {
        this.cars = cars;
    }

    public Collection<Car> getCars() {
        return cars;
    }

    public void setCars(Collection<Car> cars) {
        this.cars = cars;
    }
}

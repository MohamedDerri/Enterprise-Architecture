package derri.org.carfleet.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(collection = "cars")
public class Car {
    @Id
    private String licensePlate;

    private String type;
    private String brand;
    private double price;
    private boolean reserved;

    public Car() {
    }

    public Car(String type, String brand, String licensePlate, double price, boolean reserved) {
        this.type = type;
        this.brand = brand;
        this.licensePlate = licensePlate;
        this.price = price;
        this.reserved = reserved;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    @Override
    public String toString() {
        return "Car{" +
                ", type='" + type + '\'' +
                ", brand='" + brand + '\'' +
                ", licensePlate='" + licensePlate + '\'' +
                ", price=" + price +
                ", reserved=" + reserved +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Double.compare(car.price, price) == 0 && reserved == car.reserved  && Objects.equals(type, car.type) && Objects.equals(brand, car.brand) && Objects.equals(licensePlate, car.licensePlate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, brand, licensePlate, price, reserved);
    }
}
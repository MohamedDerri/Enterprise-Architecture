package derri.org.carfleet.dto;


import java.util.Objects;

public class CarDTO {
    private String licensePlate;
    private String type;
    private String brand;
    private double price;
    private boolean reserved;

    public CarDTO() {
    }

    public CarDTO(String type, String brand, String licensePlate, double price, boolean reserved) {
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
}

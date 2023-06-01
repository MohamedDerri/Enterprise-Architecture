package derri.org.carfleetclient;


import java.util.Collection;

public class CarsDTO {
    private Collection<CarDTO> carsDTO;

    public CarsDTO(Collection<CarDTO> carsDTO) {
        this.carsDTO = carsDTO;
    }

    public CarsDTO() {

    }


    public Collection<CarDTO> getCars() {
        return carsDTO;
    }

    public void setCars(Collection<CarDTO> carsDTO) {
        this.carsDTO = carsDTO;
    }

    @Override
    public String toString() {
        return "CarsDTO{" +
                "cars=" + carsDTO +
                '}';
    }
}
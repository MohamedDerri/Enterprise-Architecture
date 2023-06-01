package derri.org.carfleet.integration;

import derri.org.carfleet.dto.CarDTO;
import derri.org.carfleet.service.ICarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EventListener {

    @Autowired
    private ICarService carService;

    //@JmsListener(destination = "carfleet")
    public void receiveMessage (String message) {
        System.out.println("------------testing --------------");
        System.out.println("message = " + message);

        String[] messageArray = message.split(" ");
        String licensePlate = messageArray[messageArray.length-1];
        CarDTO carDTO = carService.findCarByLicensePlate(licensePlate);
        List<CarDTO> allCars = carService.findAllCars();
        if(allCars.size() < 3)
            System.out.println("sending email ///");
    }
}
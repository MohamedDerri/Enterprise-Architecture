package derri.org.carfleet.integration;

import derri.org.carfleet.dto.CarDTO;
import derri.org.carfleet.dto.CarsDTO;
import derri.org.carfleet.service.ICarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class OverviewTask {

    Logger logger = LoggerFactory.getLogger(OverviewTask.class);

    @Autowired
    private ICarService carService;

    @Scheduled(fixedRate = 20000)
    public void overviewCar() {
        Date date = Calendar.getInstance().getTime();
        DateFormat timeFormatter = DateFormat.getTimeInstance(DateFormat.DEFAULT);
        String currentTime = timeFormatter.format(date);
        System.out.println("baginning --- " + currentTime);
        List<CarDTO> cars = carService.findAllCars();
        CarsDTO carsDTO = new CarsDTO(cars);
        System.out.println(carsDTO);
    }
}
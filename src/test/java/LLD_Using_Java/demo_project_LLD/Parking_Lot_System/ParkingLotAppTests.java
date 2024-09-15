package LLD_Using_Java.demo_project_LLD.Parking_Lot_System;

import LLD_Using_Java.demo_project_LLD.Parking_Lot_System.Controller.ParkingLotController;
import LLD_Using_Java.demo_project_LLD.Parking_Lot_System.Service.ParkingLotService;
import LLD_Using_Java.demo_project_LLD.Parking_Lot_System.Service.TicketService;
import LLD_Using_Java.demo_project_LLD.Parking_Lot_System.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class ParkingLotAppTests {

    @Autowired
    private ParkingLotController parkingLotController;

    @Test
    public void test() {
        ParkingLotDTO parkingLotDTO = new ParkingLotDTO();
        parkingLotDTO.setName("ABC");
        parkingLotDTO.setNumberOfLevels(4);
        List<ParkingLevelDTO> list = new ArrayList<>();
        for (int i = 0; i < parkingLotDTO.getNumberOfLevels(); i++) {
            list.add(new ParkingLevelDTO(10, 15, i % 2 == 0 ? Type.CAR : Type.BIKE));
        }
        parkingLotDTO.setParkingLevelDTOS(list);
        ParkingLot parkingLot = parkingLotController.createParkingLot(parkingLotDTO);

        System.out.println(parkingLot);

        Ticket ticket = parkingLotController.findParkingSpot(parkingLot.getId(), Type.CAR);
        System.out.println(ticket);

        ticket = parkingLotController.findParkingSpot(parkingLot.getId(), Type.CAR);
        System.out.println(ticket);
        ticket = parkingLotController.findParkingSpot(parkingLot.getId(), Type.CAR);
        System.out.println(ticket);
        ticket = parkingLotController.findParkingSpot(parkingLot.getId(), Type.CAR);
        System.out.println(ticket);
        ticket = parkingLotController.findParkingSpot(parkingLot.getId(), Type.CAR);
        System.out.println(ticket);
    }
}

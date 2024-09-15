package LLD_Using_Java.demo_project_LLD.Parking_Lot_System.Controller;

import LLD_Using_Java.demo_project_LLD.Parking_Lot_System.Service.ParkingLotService;
import LLD_Using_Java.demo_project_LLD.Parking_Lot_System.Service.TicketService;
import LLD_Using_Java.demo_project_LLD.Parking_Lot_System.model.ParkingLot;
import LLD_Using_Java.demo_project_LLD.Parking_Lot_System.model.ParkingLotDTO;
import LLD_Using_Java.demo_project_LLD.Parking_Lot_System.model.Type;
import LLD_Using_Java.demo_project_LLD.Parking_Lot_System.model.Ticket;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("parkinglot")
@Component
@Data
public class ParkingLotController {

    @Autowired
    private ParkingLotService parkingLotService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    public ParkingLotController(ParkingLotService parkingLotService, TicketService ticketService) {
        this.parkingLotService = parkingLotService;
        this.ticketService = ticketService;
    }

    @PostMapping
    public ParkingLot createParkingLot(ParkingLotDTO parkingLotDTO){
        return parkingLotService.createParkingLot(parkingLotDTO);
    }

    @PutMapping
    public ParkingLot updateParkingLot(ParkingLotDTO parkingLotDTO){
        return parkingLotService.createParkingLot(parkingLotDTO);
    }

    @GetMapping("/{parkingLotId}/parkingspot")
    public Ticket findParkingSpot(long parkingLotId, Type vehicalType){
        return ticketService.findParkingSpot(parkingLotId,vehicalType);
    }

}

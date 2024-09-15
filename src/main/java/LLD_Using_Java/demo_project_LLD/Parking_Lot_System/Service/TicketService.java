package LLD_Using_Java.demo_project_LLD.Parking_Lot_System.Service;

import LLD_Using_Java.demo_project_LLD.Parking_Lot_System.Dao.ParkingLevelDao;
import LLD_Using_Java.demo_project_LLD.Parking_Lot_System.Dao.ParkingLotDao;
import LLD_Using_Java.demo_project_LLD.Parking_Lot_System.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TicketService {

    @Autowired
    private ParkingLotDao parkingLotDao;

    @Autowired
    private ParkingLevelDao parkingLevelDao;


    public Ticket findParkingSpot(long parkingLotId, Type vehicleType) {
        ParkingLot parkingLot = parkingLotDao.getParkingLotById(parkingLotId);
        List<ParkingLevel> parkingLevels = parkingLot.getParkingLevels()
                .stream()
                .map(parkingLevelDao::getParkingLevelById)
                .collect(Collectors.toList());

        for (ParkingLevel parkingLevel : parkingLevels) {
            List<List<ParkingSpot>> seats = parkingLevel.getSeats();
            if (!seats.isEmpty()) {
                Optional<ParkingSpot> spot = seats.stream()
                        .flatMap(List::stream)
                        .filter(parkingSpot -> parkingSpot.getType().equals(vehicleType)
                                && parkingSpot.getStatus().equals(Status.AVAILABLE))
                        .findFirst();

                if (spot.isPresent()) {
                    return Ticket.getTicketBuilder()
                            .entryTime(LocalDateTime.now())
                            .parkingSpot(spot.get())
                            .parkingLevelName(parkingLevel.getName())
                            .build();
                }
            }
        }

        return null;
    }
}

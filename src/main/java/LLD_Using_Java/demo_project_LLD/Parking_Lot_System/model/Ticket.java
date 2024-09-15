package LLD_Using_Java.demo_project_LLD.Parking_Lot_System.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Builder
public class Ticket {

    private long id;
    private ParkingSpot parkingSpot;
    private LocalDateTime entryTime;
    private String parkingLevelName;

    public static TicketBuilder getTicketBuilder(){
        return new TicketBuilder();
    }

}

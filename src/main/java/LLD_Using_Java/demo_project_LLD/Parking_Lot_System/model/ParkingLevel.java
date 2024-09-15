package LLD_Using_Java.demo_project_LLD.Parking_Lot_System.model;

import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParkingLevel {

    private long id;
    private String name;
    private List<List<ParkingSpot>> seats = new ArrayList<>();
    private int totalSeats;
    private int totalSeatsOccupied;

    public static ParkingLevelBuilder getParkingLevelBuilder(){
        return new ParkingLevelBuilder();
    }

}

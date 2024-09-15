package LLD_Using_Java.demo_project_LLD.Parking_Lot_System.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ParkingSpot {

    private Type type;
    private Status status;
    private long parkingLevelId;
    private int row;
    private int col;

}

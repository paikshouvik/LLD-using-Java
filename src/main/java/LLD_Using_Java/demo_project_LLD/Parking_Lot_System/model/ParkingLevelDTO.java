package LLD_Using_Java.demo_project_LLD.Parking_Lot_System.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ParkingLevelDTO {

    private int row;
    private int col;
    private Type type;

}

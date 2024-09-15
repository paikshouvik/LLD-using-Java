package LLD_Using_Java.demo_project_LLD.Parking_Lot_System.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParkingLotDTO {

    private int  numberOfLevels;
    private List<ParkingLevelDTO> parkingLevelDTOS = new ArrayList<>();
    private String name;

}

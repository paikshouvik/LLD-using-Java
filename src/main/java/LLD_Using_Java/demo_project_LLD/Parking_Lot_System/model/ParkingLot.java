package LLD_Using_Java.demo_project_LLD.Parking_Lot_System.model;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class ParkingLot {

    private long id;
    private Address location;
    private List<Long> parkingLevels = new ArrayList<Long>();
    private String description;
    private String name;

    public static ParkingLotBuilder getParkingLotBuilder(){
        return new ParkingLotBuilder();
    }

    @Override
    public String toString() {
        return "ParkingLot{" +
                "id=" + id +
                ", location=" + location +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

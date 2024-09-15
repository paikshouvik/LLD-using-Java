package LLD_Using_Java.demo_project_LLD.Parking_Lot_System.Dao;

import LLD_Using_Java.demo_project_LLD.Parking_Lot_System.model.ParkingLot;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Getter
@Component
public class ParkingLotDao {

    private HashMap<Long, ParkingLot> idToParkingLot = new HashMap<>();

    public ParkingLot getParkingLotById(long id){
        return idToParkingLot.get(id);
    }

    public ParkingLot save(ParkingLot parkingLot){
        if(parkingLot.getId()==0){
            parkingLot.setId(System.currentTimeMillis());
        }
        idToParkingLot.put(parkingLot.getId(),parkingLot);
        return parkingLot;
    }
}

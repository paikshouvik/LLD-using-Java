package LLD_Using_Java.demo_project_LLD.Parking_Lot_System.Dao;

import LLD_Using_Java.demo_project_LLD.Parking_Lot_System.model.ParkingLevel;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class ParkingLevelDao {

    private HashMap<Long, ParkingLevel> idToParkingLevelMap = new HashMap<>();


    public long generateParkingLevelId(){
        return System.currentTimeMillis();
    }

    public ParkingLevel getParkingLevelById(long id){
        return idToParkingLevelMap.get(id);
    }

    public ParkingLevel save(ParkingLevel parkingLevel){
        if(parkingLevel.getId()==0){
            parkingLevel.setId(System.currentTimeMillis());
        }
        idToParkingLevelMap.put(parkingLevel.getId(),parkingLevel);
        return parkingLevel;
    }


}

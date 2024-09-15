package LLD_Using_Java.demo_project_LLD.Parking_Lot_System.Service;


import LLD_Using_Java.demo_project_LLD.Parking_Lot_System.Dao.ParkingLevelDao;
import LLD_Using_Java.demo_project_LLD.Parking_Lot_System.Dao.ParkingLotDao;
import LLD_Using_Java.demo_project_LLD.Parking_Lot_System.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParkingLotService {

    @Autowired
    private ParkingLotDao parkingLotDao;

    @Autowired
    private ParkingLevelDao parkingLevelDao;


    public void addParkingLevel(ParkingLot parkingLot, ParkingLevel level) {
        List<ParkingLevel> parkingLevels = parkingLot.getParkingLevels().stream().map(id->parkingLevelDao.getParkingLevelById(id)).collect(Collectors.toList());
        if (level != null) {
            parkingLevels.add(level);
        }
    }

    public boolean removeParkingLevel(ParkingLot parkingLot, ParkingLevel level) {
        List<ParkingLevel> parkingLevels = parkingLot.getParkingLevels().stream().map(id->parkingLevelDao.getParkingLevelById(id)).collect(Collectors.toList());
        return parkingLevels.remove(level);
    }

    public ParkingLevel findParkingLevelById(ParkingLot parkingLot, int parkingLevelId) {
        List<ParkingLevel> parkingLevels = parkingLot.getParkingLevels().stream().map(id->parkingLevelDao.getParkingLevelById(id)).collect(Collectors.toList());
        for (ParkingLevel level : parkingLevels) {
            if (level.getId() == parkingLevelId) {
                return level;
            }
        }
        return null;
    }

    public ParkingLot createParkingLot(ParkingLotDTO parkingLotDTO) {
        if (parkingLotDTO == null) {
            throw new IllegalArgumentException("ParkingLotDTO cannot be null");
        }

        List<Long> parkingLevels = new ArrayList<Long>();


        // Iterate through each parking level from the DTO
        for (int i = 0; i < parkingLotDTO.getNumberOfLevels(); i++) {
            ParkingLevelDTO levelDTO = parkingLotDTO.getParkingLevelDTOS().get(i);
            int rows = levelDTO.getRow();
            int cols = levelDTO.getCol();


            List<List<ParkingSpot>> seats = new ArrayList<>();
            long parkingLevelId = parkingLevelDao.generateParkingLevelId();

            // Create parking spots for each row
            for (int row = 0; row < rows; row++) {
                List<ParkingSpot> seatRow = new ArrayList<>();
                for (int col = 0; col < cols; col++) {
                    seatRow.add(new ParkingSpot(levelDTO.getType(), Status.AVAILABLE,parkingLevelId,row,col));
                }
                seats.add(seatRow);
            }

            // Build ParkingLevel and add to the list
            ParkingLevel parkingLevel = ParkingLevel.getParkingLevelBuilder()
                    .seats(seats)
                    .name(getFloorName(i))
                    .id(parkingLevelId)
                    .build();

            ParkingLevel savedparkingLevel = parkingLevelDao.save(parkingLevel);
            parkingLevels.add(savedparkingLevel.getId());
        }

        // Build and return ParkingLot
        ParkingLot parkingLot =  ParkingLot.getParkingLotBuilder()
                .parkingLevels(parkingLevels)
                .name(parkingLotDTO.getName())
                .build();
        return parkingLotDao.save(parkingLot);
    }

    private String getFloorName(int i) {
        if(i==0){
            return "Ground Floor";
        }
        return String.format("%sth Floor",i);
    }


}

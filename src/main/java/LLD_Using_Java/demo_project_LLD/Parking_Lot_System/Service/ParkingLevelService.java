package LLD_Using_Java.demo_project_LLD.Parking_Lot_System.Service;

import LLD_Using_Java.demo_project_LLD.Parking_Lot_System.model.ParkingLevel;
import LLD_Using_Java.demo_project_LLD.Parking_Lot_System.model.ParkingSpot;
import LLD_Using_Java.demo_project_LLD.Parking_Lot_System.model.Type;
import LLD_Using_Java.demo_project_LLD.Parking_Lot_System.model.Status;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ParkingLevelService {

    public boolean addParkingSpot(ParkingLevel parkingLevel, int row, int column, ParkingSpot parkingSpot) {
        if (parkingSpot == null) {
            return false;
        }
        List<List<ParkingSpot>> seats = parkingLevel.getSeats();
        ensureRowCapacity(parkingLevel,row);
        List<ParkingSpot> rowSpots = seats.get(row);
        ensureColumnCapacity(rowSpots,row, column, parkingLevel.getId());

        if (column >= 0 && column < rowSpots.size()) {
            rowSpots.set(column, parkingSpot);
            updateSeatCount(parkingLevel,parkingSpot);
            return true;
        }
        return false;
    }

    private void ensureRowCapacity(ParkingLevel parkingLevel,int row) {
        List<List<ParkingSpot>> seats = parkingLevel.getSeats();
        while (row >= seats.size()) {
            seats.add(new ArrayList<>());
        }
    }

    private void ensureColumnCapacity(List<ParkingSpot> rowSpots, int row, int column, long id) {
        while (column >= rowSpots.size()) {
            rowSpots.add(new ParkingSpot(Type.UNKNOWN, Status.UNAVAILABLE,id,row,column));
        }
    }

    private void updateSeatCount(ParkingLevel parkingLevel,ParkingSpot parkingSpot) {
        if (parkingSpot == null) {
            return;
        }
        if (parkingSpot.getStatus() == Status.AVAILABLE) {
            parkingLevel.setTotalSeats(parkingLevel.getTotalSeats()+1);
            parkingLevel.setTotalSeatsOccupied(parkingLevel.getTotalSeatsOccupied()+1);
        } else if (parkingSpot.getStatus() == Status.OCCUPIED) {
            parkingLevel.setTotalSeats(parkingLevel.getTotalSeats()+1);
        }
    }

    private void reduceSeatCount(ParkingLevel parkingLevel,ParkingSpot parkingSpot) {
        if (parkingSpot == null) {
            return;
        }

        if (parkingSpot.getStatus() == Status.AVAILABLE) {
            parkingLevel.setTotalSeats(parkingLevel.getTotalSeats()-1);
            parkingLevel.setTotalSeatsOccupied(parkingLevel.getTotalSeatsOccupied()-1);
        } else if (parkingSpot.getStatus() == Status.OCCUPIED) {
            parkingLevel.setTotalSeats(parkingLevel.getTotalSeats()-1);
        }
    }

    public boolean removeParkingSpot(ParkingLevel parkingLevel,int row, int column) {
        List<List<ParkingSpot>> seats = parkingLevel.getSeats();
        if (row >= 0 && row < seats.size()) {
            List<ParkingSpot> rowSpots = seats.get(row);
            if (column >= 0 && column < rowSpots.size()) {
                ParkingSpot parkingSpot = rowSpots.get(column);
                reduceSeatCount(parkingLevel,parkingSpot);
                parkingSpot.setType(Type.UNKNOWN);
                parkingSpot.setStatus(Status.UNAVAILABLE);
                return true;
            }
        }
        return false;
    }

    public Optional<ParkingSpot> getParkingSpot(ParkingLevel parkingLevel,int row, int column) {

        List<List<ParkingSpot>> seats = parkingLevel.getSeats();
        if (row >= 0 && row < seats.size()) {
            List<ParkingSpot> rowSpots = seats.get(row);
            if (column >= 0 && column < rowSpots.size()) {
                return Optional.ofNullable(rowSpots.get(column));
            }
        }
        return Optional.empty();
    }

    public boolean isSpotAvailable(ParkingLevel parkingLevel,int row, int column) {
        return !getParkingSpot(parkingLevel,row, column).isPresent();
    }

    public boolean isFull(ParkingLevel parkingLevel) {
        List<List<ParkingSpot>> seats = parkingLevel.getSeats();
        return seats.stream()
                .flatMap(List::stream)
                .allMatch(spot -> spot != null && spot.getStatus() == Status.OCCUPIED);
    }

    public long countAvailableSpots(ParkingLevel parkingLevel) {
        List<List<ParkingSpot>> seats = parkingLevel.getSeats();
        return seats.stream()
                .flatMap(List::stream)
                .filter(spot -> spot != null && spot.getStatus() == Status.AVAILABLE)
                .count();
    }
}

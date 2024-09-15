package LLD_Using_Java.demo_project_LLD.Parking_Lot_System.model;

import lombok.Data;

@Data
public class Address {

    private String houseNo;
    private String streetNo;
    private String location;
    private String city;
    private String state;
    private String country;
    private long[] geolocation;

}

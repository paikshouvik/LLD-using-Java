package LLD_Using_Java.demo_project_LLD.Parking_Lot_System.Dao;

import LLD_Using_Java.demo_project_LLD.Parking_Lot_System.model.Ticket;
import lombok.Data;

import java.util.HashMap;

@Data
public class TicketDao {
    private HashMap<Long, Ticket> idToTicketMap = new HashMap<>();

    public Ticket save(Ticket ticket){
        if(ticket.getId()==0){
            ticket.setId(System.currentTimeMillis());
        }
        idToTicketMap.put(ticket.getId(),ticket);
        return ticket;
    }

    public Ticket getTicketById(long id){
        return idToTicketMap.get(id);
    }
}

package project.Service;

import project.Entity.Room;
import project.Entity.RoomOrder;
import project.Entity.Speech;
import project.Util.CrudImplementation;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.sql.Date;
import java.util.List;


@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class RoomOrderService extends CrudImplementation {

    public RoomOrder update(Integer id, RoomOrder roomOrder){
        try {
            RoomOrder oldRoomOrder = findOne(RoomOrder.class, id);
            oldRoomOrder.setRoom(roomOrder.getRoom());
            oldRoomOrder.setSpeech(roomOrder.getSpeech());
            oldRoomOrder.setDateFrom(roomOrder.getDateFrom());
            oldRoomOrder.setDateTo(roomOrder.getDateTo());
            return em.merge(oldRoomOrder);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<RoomOrder> findAll(){
        return super.findAll(RoomOrder.class);
    }

    public RoomOrder findOne(Integer id){
        return super.findOne(RoomOrder.class, id);
    }

    public void remove(Integer id){
        super.remove(RoomOrder.class, id);
    }

    public boolean check(Integer speechId, Integer roomId, Date dateFrom, Date dateTo){
        List<RoomOrder> roomOrders = em.createQuery("select e from RoomOrder e where e.speech.id = :speechId and e.room.number = :roomId")
                .setParameter("speechId", speechId)
                .setParameter("roomId", roomId)
                .getResultList();
        System.err.println("71");
        for (RoomOrder elem : roomOrders) {
            if (dateTo.compareTo(elem.getDateFrom()) >= 0 && dateFrom.compareTo(elem.getDateTo()) <= 0)
                return false;
        }
        System.err.println("76");
        return true;
    }

    public boolean add(Integer speechId, Integer roomId, Date dateFrom, Date dateTo){
        try {
            List<RoomOrder> roomOrders = em.createQuery("select e from RoomOrder e where e.speech.id = :speechId and e.room.number = :roomId")
                    .setParameter("speechId",speechId)
                    .setParameter("roomId",roomId)
                    .getResultList();
            for (RoomOrder elem: roomOrders){
                if (dateTo.compareTo(elem.getDateFrom())>=0 && dateFrom.compareTo(elem.getDateTo())<=0 )
                    return false;
            }
            Speech speech = findOne(Speech.class, speechId);
            Room room = findOne(Room.class, roomId);
            RoomOrder roomOrder = new RoomOrder(room,speech,dateFrom,dateTo);
            em.persist(roomOrder);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}

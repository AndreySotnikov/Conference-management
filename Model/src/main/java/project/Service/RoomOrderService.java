package project.Service;

import project.Entity.Room;
import project.Entity.RoomOrder;
import project.Entity.Speech;
import project.Util.CrudImplementation;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.Date;

/**
 * Created by nikita on 19.07.15.
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class RoomOrderService extends CrudImplementation {
    public RoomOrder update(String id, Date dateTo){
        try {
            RoomOrder oldRoomOrder = findOne(RoomOrder.class,id);
            oldRoomOrder.setDateTo(dateTo);
            return em.merge(oldRoomOrder);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public RoomOrder createRoomOrder(String speechId, String roomId, Date dateFrom, Date dateTo){
        try {
            Integer i = em.createQuery("select count(e) from RoomOrder e where e.speech.id = :idSpeech and e.room.number = :idRoom and e.dateFrom = :dateFrom")
                    .setParameter("idSpeech",speechId)
                    .setParameter("idRoom",roomId)
                    .setParameter("dateFrom",dateFrom)
                    .getFirstResult();
            if (i != 0)
                return null;
            Speech speech = findOne(Speech.class, speechId);
            Room room = findOne(Room.class,roomId);
            RoomOrder roomOrder = new RoomOrder(room,speech,dateFrom,dateTo);
            return roomOrder;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}

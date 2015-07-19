package project.Service;

import project.Entity.Room;
import project.Util.CrudImplementation;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.List;

/**
 * Created by nikitayakuntsev on 17.07.15.
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class RoomService extends CrudImplementation {

    public Room update(Integer id, Room room) {
        try {
            Room oldRoom = em.find(Room.class, id);
            oldRoom.setCapacity(room.getCapacity());
            oldRoom.setIsBusy(room.getIsBusy());
            oldRoom.setRoomOwner(room.getRoomOwner());
            oldRoom.setOrders(room.getOrders());
            return em.merge(oldRoom);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public List<Room> findAll() {
        return super.findAll(Room.class);
    }
/*
    public Room findOne(Integer id) {
        return super.findOne(Room.class, id);
    }

    public List<Room> findAllByOwner(RoomOwner owner) {
        try {
            String tableName = "Room";
            return em.createQuery("SELECT e FROM Room e WHERE e.roomOwner.login=:login")
                    .setParameter("login", owner.getLogin()).getResultList();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Room> findEmptyRooms() {
        try {
            String tableName = "Room";
            return em.createQuery("SELECT e FROM Room e WHERE not e.isBusy").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
*/
}

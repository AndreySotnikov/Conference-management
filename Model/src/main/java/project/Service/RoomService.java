package project.Service;

import project.Entity.Room;
import project.Entity.RoomOwner;
import project.Util.CrudImplementation;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.List;


@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class RoomService extends CrudImplementation {

    public Room update(Integer id, Room room) {
        try {
            Room oldRoom = em.find(Room.class, id);
            oldRoom.setCapacity(room.getCapacity());
            oldRoom.setBusy(room.isBusy());
            oldRoom.setRoomOwner(room.getRoomOwner());
            oldRoom.setOrders(room.getOrders());
            return em.merge(oldRoom);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }




    public Room findOne(Integer id) {
        return super.findOne(Room.class, id);
    }

    public List<Room> findAll() { return super.findAll(Room.class); }


//    public List<Room> findAllByOwner(RoomOwner owner) {
//        try {
//            String tableName = "Room";
//            return em.createQuery("SELECT e FROM Room e WHERE e.roomOwner.login=:login")
//            return em.createQuery("SELECT e FROM :table e WHERE e.roomOwner.login=:login")
//                    .setParameter("table", tableName)
//                    .setParameter("login", owner.getLogin()).getResultList();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

    public List<Room> findEmptyRooms() {
        try {
            return em.createQuery("SELECT e FROM Room e WHERE not e.busy").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void save(Integer id, Room room) {
        room.setNumber(id);
        super.save(room);
    }

    public void remove(Integer id) {
        super.remove(Room.class, id);

    }

}

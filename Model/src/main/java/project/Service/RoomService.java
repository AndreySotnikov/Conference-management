package project.Service;

import project.Entity.Room;
import project.Entity.RoomOwner;
import project.Util.CrudImplementation;

import java.util.List;

/**
 * Created by nikitayakuntsev on 17.07.15.
 */
public class RoomService extends CrudImplementation {
    public Room update(int id, Room room) {
        try {
            Room oldRoom = em.find(Room.class, id);
            oldRoom.setCapacity(room.getCapacity());
            oldRoom.setIsBusy(room.getIsBusy());
            oldRoom.setRoomOwner(room.getRoomOwner());
            oldRoom.setSpeeches(room.getSpeeches());
            return em.merge(oldRoom);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
    public List<Room> findAll() { return super.findAll(Room.class); }

    public List<Room> findAllByOwner(RoomOwner owner) {
        try {
            String tableName = "Room";
            return em.createQuery("SELECT e FROM :table e WHERE e.roomOwner.login=:login")
                    .setParameter("table", tableName)
                    .setParameter("login", owner.getLogin()).getResultList();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Room> findEmptyRooms() {
        try {
            String tableName = "Room";
            return em.createQuery("SELECT e FROM :table e WHERE not e.isBusy")
                    .setParameter("table").getResultList();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}

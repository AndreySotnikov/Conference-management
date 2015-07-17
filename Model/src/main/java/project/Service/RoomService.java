package project.Service;

import project.Entity.Room;
import project.Util.CrudImplementation;

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

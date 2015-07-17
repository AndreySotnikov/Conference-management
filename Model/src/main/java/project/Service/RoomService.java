package project.Service;

import project.Entity.Conference;
import project.Entity.Room;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by nikitayakuntsev on 17.07.15.
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class RoomService {

    @PersistenceContext
    protected EntityManager em;

    public Room findOne(Integer id) {
        try {
            return em.find(Room.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Room> findAll() {
        try {
            List<Room> resultList = em.createQuery("select e from Room e").getResultList();
            return resultList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void save(Room t) {
        try {
            em.persist(t);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void remove(Integer id) {
        try {
            em.remove(findOne(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Room update(Integer id, Room room) {
        try {
            Room oldRoom = em.find(Room.class, id);
            oldRoom.setCapacity(room.getCapacity());
            oldRoom.setIsBusy(room.getIsBusy());
            oldRoom.setRoomOwner(room.getRoomOwner());
            oldRoom.setSpeeches(room.getSpeeches());
            return em.merge(oldRoom);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
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

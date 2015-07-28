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
public class RoomOwnerService extends CrudImplementation {

    public RoomOwner update(String id, RoomOwner roomOwner){
        try {
            RoomOwner oldRoomOwner = findOne(RoomOwner.class, id);
            oldRoomOwner.setEmail(roomOwner.getEmail());
            oldRoomOwner.setName(roomOwner.getName());
            oldRoomOwner.setPhone(roomOwner.getPhone());
            return em.merge(oldRoomOwner);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public RoomOwner update(String id, String name, String email, String phone){
        try {
            RoomOwner oldRoomOwner = findOne(RoomOwner.class, id);
            oldRoomOwner.setEmail(email);
            oldRoomOwner.setName(name);
            oldRoomOwner.setPhone(phone);
            return em.merge(oldRoomOwner);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public List<RoomOwner> findAll(){
        return super.findAll(RoomOwner.class);
    }

    public RoomOwner findOne(String id){
        return super.findOne(RoomOwner.class, id);
    }

    public void remove(String id){
        super.remove(RoomOwner.class, id);
    }

    public List<Room> myRooms(String id ) {
        RoomOwner me = findOne(id);
        return me.getRooms();
    }

}

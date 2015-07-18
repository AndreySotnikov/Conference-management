package project.Service;

import project.Entity.RoomOwner;
import project.Util.CrudImplementation;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.List;

/**
 * Created by Green-L on 18.07.2015.
 */
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

    public List<RoomOwner> findAll(){
        return super.findAll(RoomOwner.class);
    }

    public RoomOwner findOne(String id){
        return super.findOne(RoomOwner.class, id);
    }

    public void remove(String id){
        super.remove(RoomOwner.class, id);
    }

}
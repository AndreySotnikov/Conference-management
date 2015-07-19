package project.Service;

import project.Entity.Moderator;
import project.Entity.Room;
import project.Util.CrudImplementation;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.List;

/**
 * Created by nikitayakuntsev on 19.07.15.
 */


@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class ModeratorService extends CrudImplementation {
    public Moderator update(Integer id, Moderator moderator) {
        try {
            Moderator old = em.find(Moderator.class, id);
            old.setName(moderator.getName());
            old.setEmail(moderator.getEmail());
            old.setPhone(moderator.getPhone());
            return em.merge(old);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Moderator> findAll() {
        return super.findAll(Moderator.class);
    }

    public Moderator findOne(Integer id) {
        return super.findOne(Moderator.class, id);
    }

    public void remove(Integer id) {
        super.remove(Moderator.class, id);
    }
}

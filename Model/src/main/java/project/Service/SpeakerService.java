package project.Service;

import project.Entity.Speaker;
import project.Util.CrudImplementation;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.List;

/**
 * Created by nikita on 19.07.15.
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class SpeakerService extends CrudImplementation {
    public Speaker update (String id, Speaker speaker){
        try {
            Speaker oldSpeaker = findOne(Speaker.class, id);
            oldSpeaker.setName(speaker.getName());
            oldSpeaker.setPhone(speaker.getPhone());
            oldSpeaker.setLogin(speaker.getLogin());
            oldSpeaker.setEmail(speaker.getEmail());
            return em.merge(oldSpeaker);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Speaker> findAll(){
        return super.findAll(Speaker.class);
    }

    public Speaker findOne(String id){
        return super.findOne(Speaker.class, id);
    }

    public void remove(String id) { super.remove(Speaker.class, id);}
}

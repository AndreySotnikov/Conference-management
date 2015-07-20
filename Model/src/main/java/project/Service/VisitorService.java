package project.Service;

import project.Entity.Conference;
import project.Entity.Speech;
import project.Entity.Visitor;
import project.Util.CrudImplementation;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.List;


@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class VisitorService extends CrudImplementation {
    public Visitor update(String id, Visitor visitor){
        try {
            Visitor oldVisitor = findOne(Visitor.class, id);
            oldVisitor.setEmail(visitor.getEmail());
            oldVisitor.setLogin(visitor.getLogin());
            oldVisitor.setName(visitor.getName());
            oldVisitor.setPhone(visitor.getPhone());
            return em.merge(oldVisitor);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Visitor> findAll(){
        return super.findAll(Visitor.class);
    }

    public Visitor findOne(String id){
        return super.findOne(Visitor.class, id);
    }

    public void remove(String id) { super.remove(Visitor.class, id);}

    public Boolean registerToSpeech(String visitorId, Integer speechId) {
        Visitor me = findOne(visitorId);
        Speech sp = findOne(Speech.class, speechId);

        List<Speech> speeches = me.getSpeeches();
        if (speeches.contains(sp))
            return false;
        else {
            try {
                speeches.add(sp);
                em.merge(me);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
    }

    public Boolean registerToConference(String visitorId, Integer conferenceId) {
        Visitor me = findOne(visitorId);
        Conference conf = findOne(Conference.class, conferenceId);
        List<Conference> conferences = me.getConferences();
        if (conferences.contains(conf))
            return false;
        else {
            try {
                conferences.add(conf);
                em.merge(me);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return  false;
            }
        }
    }

}

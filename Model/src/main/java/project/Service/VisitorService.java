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

    public Boolean registerToSpeech(String visitorId, Integer speechId, Integer conferenceId) {
        Visitor visitor = findOne(visitorId);
        Speech speech = findOne(Speech.class, speechId);

        if (!speech.getConference().getId().equals(conferenceId))
            return false;
        else {
            List<Visitor> visitors = speech.getVisitors();
            if (visitors.contains(visitor))
                return false;
            else {
                try {
                    visitors.add(visitor);
                    em.merge(speech);
                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }
        }
    }


    public Boolean registerToConference(String visitorId, Integer conferenceId) {
        Visitor visitor = findOne(visitorId);
        Conference conference = findOne(Conference.class, conferenceId);
        List<Visitor> visitors = conference.getVisitors();
        if (visitors.contains(visitor))
            return false;
        else {
            try {
                visitors.add(visitor);
                em.merge(conference);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return  false;
            }
        }
    }

}

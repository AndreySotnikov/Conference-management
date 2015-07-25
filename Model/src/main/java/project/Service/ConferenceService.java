package project.Service;

import project.Entity.Conference;
import project.Entity.Speech;
import project.Util.CrudImplementation;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.sql.Date;
import java.util.List;


@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class ConferenceService extends CrudImplementation {
    public Conference update(Integer id, Conference entity) {
        try {
            Conference oldConference = em.find(Conference.class, id);
            oldConference.setName(entity.getName());
            oldConference.setStartDate(entity.getStartDate());
            oldConference.setEndDate(entity.getEndDate());
            oldConference.setDescription(entity.getDescription());
            oldConference.setOrganizer(entity.getOrganizer());
            oldConference.setSpeeches(entity.getSpeeches());
            return em.merge(oldConference);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Conference update(Integer id, String name, Date start, Date end, String description) {
        try {
            Conference oldConference = em.find(Conference.class, id);
            oldConference.setName(name);
            oldConference.setStartDate(start);
            oldConference.setEndDate(end);
            oldConference.setDescription(description);
            return em.merge(oldConference);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Conference> findAll(){
        return findAll(Conference.class);
    }

    public Conference findOne(Integer id){
        return findOne(Conference.class, id);
    }

    public List<Conference> findAllByLogin(String login){
        try {
            return em.createQuery("select e from Conference e where e.organizer.login=:login")
                    .setParameter("login", login).getResultList();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public void remove(int id){
        remove(Conference.class,id);
    }

    public List<Speech> speechesByConference(Integer id) {
        try {
            return em.createQuery("select e from Speech e where e.conference.id=:id")
                    .setParameter("id", id).getResultList();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}

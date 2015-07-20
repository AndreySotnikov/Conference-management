package project.Service;

import project.Entity.Organizer;
import project.Util.CrudImplementation;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.List;


@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class OrganizerService extends CrudImplementation {
    public Organizer update(String id, Organizer entity) {
        try {
            Organizer oldOrganizer = em.find(Organizer.class, id);
            oldOrganizer.setName(entity.getName());
            oldOrganizer.setEmail(entity.getEmail());
            oldOrganizer.setPhone(entity.getPhone());
            return em.merge(oldOrganizer);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Organizer update(String id, String name,String email,String phone) {
        try {
            Organizer oldOrganizer = em.find(Organizer.class, id);
            oldOrganizer.setName(name);
            oldOrganizer.setEmail(email);
            oldOrganizer.setPhone(phone);
            return em.merge(oldOrganizer);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Organizer> findAll(){
        return findAll(Organizer.class);
    }

    public Organizer findOne(String login){
        return findOne(Organizer.class, login);
    }

    public Organizer findByLogin(String login){
        try {
            return (Organizer) em.createQuery("select e from Organizer e where e.login=:login")
                    .setParameter("login", login).getResultList().get(0);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public void remove(String login){
        remove(Organizer.class,login);
    }
}

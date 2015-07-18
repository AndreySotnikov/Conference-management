package project.Service;

import project.Entity.Organizer;
import project.Util.CrudImplementation;

import java.util.List;

/**
 * Created by andrey on 18.07.15.
 */
public class OrganizerService extends CrudImplementation {
    public Organizer update(Integer id, Organizer entity) {
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

    public List<Organizer> findAll(){
        return findAll(Organizer.class);
    }

    public Organizer findOne(Integer id){
        return findOne(Organizer.class, id);
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
}

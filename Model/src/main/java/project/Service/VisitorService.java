package project.Service;

import project.Entity.Visitor;
import project.Util.CrudImplementation;

import java.util.List;

/**
 * Created by nikita on 18.07.15.
 */
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

    public void registerToSpeech(String visitor, Integer speech) {

    }

    public void registerToConference(String visitor, Integer conference) {

    }

}

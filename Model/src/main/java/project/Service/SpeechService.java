package project.Service;

import project.Entity.Conference;
import project.Entity.Speech;
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
public class SpeechService extends CrudImplementation {
    public Speech update (String id, Speech speech){
        try {
            Speech oldSpeech = findOne(Speech.class, id);
            oldSpeech.setCompleted(speech.getCompleted());
            oldSpeech.setConference(speech.getConference());
            oldSpeech.setReporter(speech.getReporter());
            oldSpeech.setRoom(speech.getRoom());
            oldSpeech.setSpeaker(speech.getSpeaker());
            oldSpeech.setStartDate(speech.getStartDate());
            oldSpeech.setTopic(speech.getTopic());
            oldSpeech.setText(speech.getText());
            oldSpeech.setApproved(speech.getApproved());
            return em.merge(oldSpeech);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public List<Speech> findByConference(Conference conference) {
        try {
            return em.createQuery("select e from Speech e where e.conference.id = :id")
                    .setParameter("id",conference.getId())
                    .getResultList();
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public Speech setApproved(String id, boolean approved){
        try {
            Speech oldSpeech  = findOne(Speech.class, id);
            oldSpeech.setApproved(approved);
            em.merge(oldSpeech);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Speech setText(String id, String text){
        try {
            Speech oldSpeech = findOne(Speech.class, id);
            oldSpeech.setText(text);
            return em.merge(oldSpeech);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public Speech findOne(String id){
        return super.findOne(Speech.class, id);
    }

    public void remove(String id) { super.remove(Speech.class, id);}
}

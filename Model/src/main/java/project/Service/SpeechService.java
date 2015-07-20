package project.Service;

import project.Entity.Speech;
import project.Util.CrudImplementation;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.List;


@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class SpeechService extends CrudImplementation {
    public Speech update (String id, Speech speech){
        try {
            Speech oldSpeech = findOne(Speech.class, id);
            oldSpeech.setCompleted(speech.isCompleted());
            oldSpeech.setConference(speech.getConference());
            oldSpeech.setReporter(speech.getReporter());
            oldSpeech.setRoomOrder(speech.getRoomOrder());
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

    public List<Speech> findByConference(String conferenceId) {
        try {
            return em.createQuery("select e from Speech e where e.conference.id = :id")
                    .setParameter("id",conferenceId)
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
            return em.merge(oldSpeech);
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

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
            oldSpeech.setConference(speech.getConference());
            oldSpeech.setSpeaker(speech.getSpeaker());
            oldSpeech.setStartDate(speech.getStartDate());
            oldSpeech.setTopic(speech.getTopic());
            return em.merge(oldSpeech);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public List<Speech> findByConference(Integer conferenceId) {
        try {
            return em.createQuery("select e from Speech e where e.conference.id = :id")
                    .setParameter("id",conferenceId)
                    .getResultList();
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public List<Speech> findBySpeaker(String speakerId){
        try {
            return em.createQuery("select e from Speech e where e.speaker.id = :id")
                    .setParameter("id",speakerId)
                    .getResultList();
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public void setApproved(Integer id, boolean approved){
        try {
            Speech oldSpeech  = findOne(Speech.class, id);
            oldSpeech.setApproved(approved);
            em.merge(oldSpeech);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setCompleted(Integer id, boolean completed){
        try {
            Speech speech = findOne(Speech.class, id);
            speech.setCompleted(completed);
            em.merge(speech);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setText(Integer id, String text){
        try {
            Speech oldSpeech = findOne(Speech.class, id);
            oldSpeech.setText(text);
            em.merge(oldSpeech);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public Speech findOne(Integer id){
        return super.findOne(Speech.class, id);
    }

    public void remove(Integer id) { super.remove(Speech.class, id);}
}

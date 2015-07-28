package project.Service;

import project.Entity.ReporterRequestsSpeech;
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
            oldSpeech.setRoomOrder(speech.getRoomOrder());
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
            return em.createQuery("select e from Speech e where e.speaker.id = :id and e.approved=true ")
                    .setParameter("id", speakerId)
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

    public boolean hasReportedRequested(Integer speechId, String reporterLogin) {
        Speech sp = findOne(speechId);
        return em
                .createQuery("SELECT e FROM ReporterRequestsSpeech e WHERE e.speech.id=:speechId AND e.reporter.login=:reporterLogin")
                .setParameter("speechId", speechId)
                .setParameter("reporterLogin", reporterLogin)
                .getResultList()
                .size() != 0;
    }

    public boolean hasModeratorRequested(Integer speechId, String moderatorLogin) {
        Speech sp = findOne(speechId);
        return em
                .createQuery("SELECT e FROM ModeratorRequestsSpeech e WHERE e.speech.id=:speechId AND e.moderator.login=:moderatorLogin")
                .setParameter("speechId", speechId)
                .setParameter("moderatorLogin", moderatorLogin)
                .getResultList()
                .size() != 0;
    }
}

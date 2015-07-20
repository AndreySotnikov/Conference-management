package project.Service;

import project.Entity.*;
import project.Util.CrudImplementation;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.Query;
import java.util.List;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class ModeratorSpeechService extends CrudImplementation {

    public ModeratorRequestsSpeech update(Integer id, boolean approved){
        try {
            ModeratorRequestsSpeech oldElem = findOne(ModeratorRequestsSpeech.class, id);
            oldElem.setApproved(approved);
            return em.merge(oldElem);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public List<ModeratorRequestsSpeech> findAll(){
        return super.findAll(ModeratorRequestsSpeech.class);
    }

    public ModeratorRequestsSpeech findOne(Integer id){
        return super.findOne(ModeratorRequestsSpeech.class, id);
    }

    public List<Speech> findSpeeches(boolean approved) {
        try {
            return em.createQuery("SELECT e.speech FROM ModeratorRequestsSpeech e WHERE e.approved=:approved")
                    .setParameter("approved", approved).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Speech> findSpeechesWithoutApprovedModerators() {
        return findSpeeches(false);
    }

    public List<Speech> findSpeechesWithApprovedModerators() {
        return findSpeeches(true);
    }

    public List<Moderator> findModerators(Integer speechId, boolean approved) {
        Query query = em.createQuery("SELECT e.moderator FROM ModeratorRequestsSpeech e WHERE "+
                "e.speech.id=:speechId AND e.approved=:approved");
        query.setParameter("speechId", speechId);
        query.setParameter("approved", approved);
        return query.getResultList();
    }

    public List<Moderator> findApprovedModeratorsBySpeech(Integer speechId){
        return findModerators(speechId, true);
    }

    public List<Moderator> findUnapprovedModeratorsBySpeech(Integer speechId){
        return findModerators(speechId, false);
    }

    public boolean registerModeratorOnSpeech(String moderatorId, Integer speechId){
        Query query = em.createQuery("SELECT e FROM ModeratorRequestsSpeech e WHERE "+
                "e.speech.id=:speechId AND e.moderator.login=:moderatorId");
        query.setParameter("speechId", speechId);
        query.setParameter("moderatorId", moderatorId);
        ModeratorRequestsSpeech moderatorRequestsSpeech = (ModeratorRequestsSpeech) query.getSingleResult();
        if (moderatorRequestsSpeech!=null)
            return false;
        Moderator moderator = findOne(Moderator.class, moderatorId);
        Speech speech = findOne(Speech.class, speechId);
        super.save(new ModeratorRequestsSpeech(moderator, speech, false));
        return true;
    }

    public boolean approveModeratorOnSpeech(String moderatorId, Integer speechId){
        Query query = em.createQuery("SELECT e FROM ModeratorRequestsSpeech e WHERE "+
                "e.speech.id=:speechId AND e.moderator.login=:moderatorId");
        query.setParameter("speechId", speechId);
        query.setParameter("moderatorId", moderatorId);
        ModeratorRequestsSpeech moderatorRequestsSpeech = (ModeratorRequestsSpeech) query.getSingleResult();
        if (moderatorRequestsSpeech==null)
            return false;
        update(moderatorRequestsSpeech.getId(), true);
        return true;
    }

    public void remove(Integer id){
        super.remove(ModeratorRequestsSpeech.class, id);
    }

}

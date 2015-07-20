package project.Service;

import project.Entity.Reporter;
import project.Entity.ReporterRequestsSpeech;
import project.Entity.Speech;
import project.Util.CrudImplementation;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Green-L on 18.07.2015.
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class ReporterSpeechService extends CrudImplementation {

    public ReporterRequestsSpeech update(Integer id, boolean approved){
        try {
            ReporterRequestsSpeech oldElem = findOne(ReporterRequestsSpeech.class, id);
            oldElem.setApproved(approved);
            return em.merge(oldElem);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public List<ReporterRequestsSpeech> findAll(){
        return super.findAll(ReporterRequestsSpeech.class);
    }

    public ReporterRequestsSpeech findOne(Integer id){
        return super.findOne(ReporterRequestsSpeech.class, id);
    }

    public List<Speech> findSpeeches(boolean approved) {
        try {
            return em.createQuery("SELECT e.speech FROM ReporterRequestsSpeech e WHERE e.approved=:approved")
                    .setParameter("approved", approved).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Speech> findSpeechesWithoutApprovedReporters() {
        return findSpeeches(false);
    }

    public List<Speech> findSpeechesWithApprovedReporters() {
        return findSpeeches(true);
    }

    public List<Reporter> findReporters(Integer speechId, boolean approved) {
        Query query = em.createQuery("SELECT e.reporter FROM ReporterRequestsSpeech e WHERE "+
                "e.speech.id=:speechId AND e.approved=:approved");
        query.setParameter("speechId", speechId);
        query.setParameter("approved", approved);
        return query.getResultList();
    }

    public List<Reporter> findApprovedReportersBySpeech(Integer speechId){
        return findReporters(speechId, true);
    }

    public List<Reporter> findUnapprovedReportersBySpeech(Integer speechId){
        return findReporters(speechId, false);
    }

    public boolean registerReporterOnSpeech(String reporterId, Integer speechId){
        Query query = em.createQuery("SELECT e FROM ReporterRequestsSpeech e WHERE "+
                "e.speech.id=:speechId AND e.reporter.login=:reporterId");
        query.setParameter("speechId", speechId);
        query.setParameter("reporterId", reporterId);
        ReporterRequestsSpeech reporterRequestsSpeech = (ReporterRequestsSpeech) query.getSingleResult();
        if (reporterRequestsSpeech!=null)
            return false;
        Reporter reporter = findOne(Reporter.class, reporterId);
        Speech speech = findOne(Speech.class, speechId);
        super.save(new ReporterRequestsSpeech(reporter, speech, false));
        return true;
    }

    public boolean approveReporterOnSpeech(String reporterId, Integer speechId){
        Query query = em.createQuery("SELECT e FROM ReporterRequestsSpeech e WHERE "+
                "e.speech.id=:speechId AND e.reporter.login=:reporterId");
        query.setParameter("speechId", speechId);
        query.setParameter("reporterId", reporterId);
        ReporterRequestsSpeech reporterRequestsSpeech = (ReporterRequestsSpeech) query.getSingleResult();
        if (reporterRequestsSpeech==null)
            return false;
        update(reporterRequestsSpeech.getId(), true);
        return true;
    }

    public void remove(Integer id){
        super.remove(ReporterRequestsSpeech.class, id);
    }

}

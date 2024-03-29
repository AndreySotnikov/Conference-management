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

    public List<Speech> findSpeeches(String reporterId, boolean approved) {
        Query query = em.createQuery("SELECT e.speech FROM ReporterRequestsSpeech e WHERE "+
                "e.reporter.id=:reporterId AND e.approved=:approved");
        query.setParameter("reporterId", reporterId);
        query.setParameter("approved", approved);
        return query.getResultList();
    }

    public boolean checkReporterSpeech(String reporterId, Integer speechId) {
        Query query = em.createQuery("SELECT e FROM ReporterRequestsSpeech e WHERE "+
            "e.reporter.id=:reporterId AND e.speech.id=:speechId AND e.approved=TRUE ");
        query.setParameter("reporterId", reporterId);
        query.setParameter("speechId", speechId);
        return query.getResultList().size()>0;
    }

    public List<Speech> findApprovedSpeechesByReporter(String reporterId){
        return findSpeeches(reporterId, true);
    }

    public List<Speech> findUnapprovedSpeechesByReporter(String reporterId){
        return findSpeeches(reporterId, false);
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
        if (!query.getResultList().isEmpty())
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
        List<ReporterRequestsSpeech> queryList = query.getResultList();
        if (queryList.size() != 1){
            return false;
        }
        update(queryList.get(0).getId(), true);
        return true;
    }

    public void remove(Integer id){
        super.remove(ReporterRequestsSpeech.class, id);
    }

}

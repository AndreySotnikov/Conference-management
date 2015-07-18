//package project.Service;
//
//import project.Entity.Other.ReporterSpeechId;
//import project.Entity.Reporter;
//import project.Entity.ReporterRequestsSpeech;
//import project.Entity.Speech;
//import project.Util.CrudImplementation;
//
//import javax.ejb.Stateless;
//import javax.ejb.TransactionAttribute;
//import javax.ejb.TransactionAttributeType;
//import javax.persistence.Query;
//import java.util.List;
//
///**
// * Created by Green-L on 18.07.2015.
// */
//@Stateless
//@TransactionAttribute(TransactionAttributeType.REQUIRED)
//public class ReporterSpeechService extends CrudImplementation {
//
//    public ReporterRequestsSpeech update(ReporterSpeechId id, boolean approved){
//        try {
//            ReporterRequestsSpeech oldElem = findOne(ReporterRequestsSpeech.class, id);
//            oldElem.setApproved(approved);
//            return em.merge(oldElem);
//        }catch (Exception e){
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    public List<ReporterRequestsSpeech> findAll(){
//        return super.findAll(ReporterRequestsSpeech.class);
//    }
//
//    public ReporterRequestsSpeech findOne(ReporterSpeechId id){
//        return super.findOne(ReporterRequestsSpeech.class, id);
//    }
//
//    public List<Speech> findSpeeches(boolean approved) {
//        try {
//            return em.createQuery("SELECT e.reporterSpeechId.speech FROM ReporterRequestsSpeech e WHERE e.approved=:approved")
//                    .setParameter("approved", approved).getResultList();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    public List<Speech> findSpeechesWithoutApprovedReporters() {
//        return findSpeeches(false);
//    }
//
//    public List<Speech> findSpeechesWithApprovedReporters() {
//        return findSpeeches(true);
//    }
//
//    public List<Reporter> findReporters(Speech speech, boolean approved) {
//        Query query = em.createQuery("SELECT e.reporterSpeechId.reporter FROM ReporterRequestsSpeech e WHERE "+
//                "e.reporterSpeechId.speech.id=:speechId AND e.approved=:approved");
//        query.setParameter("speechId", speech.getId());
//        query.setParameter("approved", approved);
//        return query.getResultList();
//    }
//
//    public List<Reporter> findApprovedReportersBySpeech(Speech speech){
//        return findReporters(speech, true);
//    }
//
//    public List<Reporter> findUnapprovedReportersBySpeech(Speech speech){
//        return findReporters(speech, false);
//    }
//
//    public void registerReporterOnSpeech(Reporter reporter, Speech speech){
//        super.save(new ReporterRequestsSpeech(new ReporterSpeechId(reporter, speech), false));
//    }
//
//    public boolean approveReporterOnSpeech(Reporter reporter, Speech speech){
////        ReporterRequestsSpeech reporterRequestsSpeech =
////                findOne(ReporterRequestsSpeech.class, new ReporterSpeechId(reporter, speech));
//        Query query = em.createQuery("SELECT e FROM ReporterRequestsSpeech e WHERE "+
//                "e.reporterSpeechId.speech.id=:speechId AND e.reporterSpeechId.reporter.login=:reporterId");
//        query.setParameter("speechId", speech.getId());
//        query.setParameter("reporterId", reporter.getLogin());
//        ReporterRequestsSpeech reporterRequestsSpeech = (ReporterRequestsSpeech) query.getSingleResult();
//        if (reporterRequestsSpeech==null)
//            return false;
//        update(new ReporterSpeechId(reporter, speech), true);
//        return true;
//    }
//
//    public void remove(ReporterSpeechId id){
//        super.remove(ReporterRequestsSpeech.class, id);
//    }
//
//}

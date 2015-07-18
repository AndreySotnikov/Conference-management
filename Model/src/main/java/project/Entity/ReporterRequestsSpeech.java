//package project.Entity;
//
//import project.Entity.Other.ReporterSpeechId;
//
//import javax.persistence.EmbeddedId;
//import javax.persistence.Entity;
//
///**
// * Created by Green-L on 18.07.2015.
// */
//@Entity
//public class ReporterRequestsSpeech {
//
//    @EmbeddedId
//    private ReporterSpeechId reporterSpeechId;
//
//    private boolean approved;
//
//    public ReporterRequestsSpeech() {
//    }
//
//    public ReporterRequestsSpeech(ReporterSpeechId reporterSpeechId, boolean approved) {
//        this.reporterSpeechId = reporterSpeechId;
//        this.approved = approved;
//    }
//
//    public ReporterSpeechId getReporterSpeechId() {
//        return reporterSpeechId;
//    }
//
//    public void setReporterSpeechId(ReporterSpeechId reporterSpeechId) {
//        this.reporterSpeechId = reporterSpeechId;
//    }
//
//    public boolean isApproved() {
//        return approved;
//    }
//
//    public void setApproved(boolean approved) {
//        this.approved = approved;
//    }
//}

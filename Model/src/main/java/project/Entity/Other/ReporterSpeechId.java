//package project.Entity.Other;
//
//import project.Entity.Reporter;
//import project.Entity.Speech;
//
//import javax.persistence.Embeddable;
//import java.io.Serializable;
//
///**
// * Created by Green-L on 18.07.2015.
// */
//@Embeddable
//public class ReporterSpeechId implements Serializable{
//
//    private Reporter reporter;
//    private Speech speech;
//
//    public ReporterSpeechId() {
//    }
//
//    public ReporterSpeechId(Reporter reporter, Speech speech) {
//        this.reporter = reporter;
//        this.speech = speech;
//    }
//
//    public Reporter getReporter() {
//        return reporter;
//    }
//
//    public void setReporter(Reporter reporter) {
//        this.reporter = reporter;
//    }
//
//    public Speech getSpeech() {
//        return speech;
//    }
//
//    public void setSpeech(Speech speech) {
//        this.speech = speech;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        ReporterSpeechId that = (ReporterSpeechId) o;
//
//        if (reporter != null ? !reporter.equals(that.reporter) : that.reporter != null) return false;
//        return !(speech != null ? !speech.equals(that.speech) : that.speech != null);
//
//    }
//
//    @Override
//    public int hashCode() {
//        int result = reporter != null ? reporter.hashCode() : 0;
//        result = 31 * result + (speech != null ? speech.hashCode() : 0);
//        return result;
//    }
//}

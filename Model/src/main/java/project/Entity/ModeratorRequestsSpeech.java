//package project.Entity;
//
//import project.Entity.Other.ModeratorSpeechId;
//
//import javax.persistence.EmbeddedId;
//import javax.persistence.Entity;
//
///**
// * Created by Green-L on 18.07.2015.
// */
//@Entity
//public class ModeratorRequestsSpeech {
//
//    @EmbeddedId
//    private ModeratorSpeechId moderatorSpeechId;
//
//    private boolean approved;
//
//    public ModeratorRequestsSpeech() {
//    }
//
//    public ModeratorRequestsSpeech(ModeratorSpeechId moderatorSpeechId, boolean approved) {
//        this.moderatorSpeechId = moderatorSpeechId;
//        this.approved = approved;
//    }
//
//    public ModeratorSpeechId getModeratorSpeechId() {
//        return moderatorSpeechId;
//    }
//
//    public void setModeratorSpeechId(ModeratorSpeechId moderatorSpeechId) {
//        this.moderatorSpeechId = moderatorSpeechId;
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

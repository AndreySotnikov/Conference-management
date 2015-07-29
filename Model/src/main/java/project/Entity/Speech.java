package project.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by nikitayakuntsev on 14.07.15.
 */
@Entity
public class Speech {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private Timestamp startDate;
    private String topic;

    private String text;

    private boolean approved;

    @ManyToOne
    private Speaker speaker;

    @OneToOne
    private RoomOrder roomOrder;

    @ManyToOne
    private Conference conference;

    private boolean completed;

    @OneToMany(mappedBy="speech", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private  List<ReporterRequestsSpeech> reporterRequests;


    @OneToMany(mappedBy = "speech", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Question> questions;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Visitor> visitors;

    @OneToMany(mappedBy = "speech", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<ModeratorRequestsSpeech> moderatorRequestsSpeech;

    public Speech() {
    }

    @PrePersist
    public void init(){
        approved = false;
        completed = false;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public Speaker getSpeaker() {
        return speaker;
    }

    public void setSpeaker(Speaker speaker) {
        this.speaker = speaker;
    }

    public RoomOrder getRoomOrder() {
        return roomOrder;
    }

    public void setRoomOrder(RoomOrder roomOrder) {
        this.roomOrder = roomOrder;
    }

    public Conference getConference() {
        return conference;
    }

    public void setConference(Conference conference) {
        this.conference = conference;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public List<ReporterRequestsSpeech> getReporterRequests() {
        return reporterRequests;
    }

    public void setReporterRequests(List<ReporterRequestsSpeech> reporterRequests) {
        this.reporterRequests = reporterRequests;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public List<Visitor> getVisitors() {
        return visitors;
    }

    public void setVisitors(List<Visitor> visitors) {
        this.visitors = visitors;
    }

    public List<ModeratorRequestsSpeech> getModeratorRequestsSpeech() {
        return moderatorRequestsSpeech;
    }

    public void setModeratorRequestsSpeech(List<ModeratorRequestsSpeech> moderatorRequestsSpeech) {
        this.moderatorRequestsSpeech = moderatorRequestsSpeech;
    }
}

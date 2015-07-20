package project.Entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

/**
 * Created by nikitayakuntsev on 14.07.15.
 */
@Entity
public class Speech {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private Date startDate;
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

    @OneToMany(mappedBy="speech")
    private transient List<ReporterRequestsSpeech> reporterRequests;


    @OneToMany(mappedBy = "speech", cascade = CascadeType.REMOVE)
    private transient List<Question> questions;
    @ManyToMany
    private List<Visitor> visitors;

    @OneToMany(mappedBy = "speech", cascade = CascadeType.REMOVE)
    private transient ModeratorRequestsSpeech moderatorRequestsSpeech;

    @Override
    public String toString() {
        return "Speech{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", topic='" + topic + '\'' +
                ", speaker=" + speaker +
                ", room=" + roomOrder.getRoom() +
                ", conference=" + conference +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Speech speech = (Speech) o;

        return !(id != null ? !id.equals(speech.id) : speech.id != null);

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public boolean isApproved() {
        return approved;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
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

    public ModeratorRequestsSpeech getModeratorRequestsSpeech() {
        return moderatorRequestsSpeech;
    }

    public void setModeratorRequestsSpeech(ModeratorRequestsSpeech moderatorRequestsSpeech) {
        this.moderatorRequestsSpeech = moderatorRequestsSpeech;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean getApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Speaker getSpeaker() {
        return speaker;
    }

    public void setSpeaker(Speaker speaker) {
        this.speaker = speaker;
    }

    public Room getRoom() {
        return roomOrder.getRoom();
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


    public Speech() {
    }
}

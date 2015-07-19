package project.Entity;

import javax.persistence.*;
import java.sql.Date;

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

    private Boolean approved;

    @ManyToOne
    private Speaker speaker;

    @ManyToOne
    private Room room;

    @ManyToOne
    private Conference conference;

    private Boolean completed;

    @ManyToOne
    private Reporter reporter;

    @Override
    public String toString() {
        return "Speech{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", topic='" + topic + '\'' +
                ", speaker=" + speaker +
                ", room=" + room +
                ", conference=" + conference +
                ", completed=" + completed +
                ", reporter=" + reporter +
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


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
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
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Conference getConference() {
        return conference;
    }

    public void setConference(Conference conference) {
        this.conference = conference;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public Reporter getReporter() {
        return reporter;
    }

    public void setReporter(Reporter reporter) {
        this.reporter = reporter;
    }

    public Speech() {
    }
}

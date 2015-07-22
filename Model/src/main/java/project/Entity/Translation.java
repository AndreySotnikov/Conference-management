package project.Entity;

import javax.persistence.*;
import java.sql.Timestamp;
//import java.sql.Date;

/**
 * Created by Green-L on 18.07.2015.
 */
@Entity
public class Translation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String text;
    private Timestamp timestamp;

    @ManyToOne
    private Speech speech;

    public Translation() {
    }

    public Translation(String text, Timestamp timestamp, Speech speech) {
        this.text = text;
        this.timestamp = timestamp;
        this.speech = speech;
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

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public Speech getSpeech() {
        return speech;
    }

    public void setSpeech(Speech speech) {
        this.speech = speech;
    }
}

package project.Entity;

import javax.persistence.*;

/**
 * Created by Green-L on 18.07.2015.
 */
@Entity
public class Translation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String text;
    private long time;

    @ManyToOne
    private Speech speech;

    public Translation() {
    }

    public Translation(String text, long time, Speech speech) {
        this.text = text;
        this.time = time;
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

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public Speech getSpeech() {
        return speech;
    }

    public void setSpeech(Speech speech) {
        this.speech = speech;
    }
}

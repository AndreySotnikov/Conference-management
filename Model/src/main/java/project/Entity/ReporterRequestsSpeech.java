package project.Entity;

import javax.persistence.*;

/**
 * Created by Green-L on 18.07.2015.
 */
@Entity
public class ReporterRequestsSpeech {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    private Reporter reporter;
    @ManyToOne
    private Speech speech;

    private boolean approved;

    public ReporterRequestsSpeech() {
    }

    public ReporterRequestsSpeech(Reporter reporter, Speech speech, boolean approved) {
        this.reporter = reporter;
        this.speech = speech;
        this.approved = approved;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Reporter getReporter() {
        return reporter;
    }

    public void setReporter(Reporter reporter) {
        this.reporter = reporter;
    }

    public Speech getSpeech() {
        return speech;
    }

    public void setSpeech(Speech speech) {
        this.speech = speech;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
}

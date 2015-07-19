package project.Entity;

import javax.persistence.*;

/**
 * Created by Green-L on 18.07.2015.
 */
@Entity
public class ModeratorRequestsSpeech {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    private Moderator moderator;
    @ManyToOne
    private Speech speech;

    private boolean approved;

    public ModeratorRequestsSpeech() {
    }

    public ModeratorRequestsSpeech(Moderator moderator, Speech speech, boolean approved) {
        this.moderator = moderator;
        this.speech = speech;
        this.approved = approved;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Moderator getModerator() {
        return moderator;
    }

    public void setModerator(Moderator moderator) {
        this.moderator = moderator;
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

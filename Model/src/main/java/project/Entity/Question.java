package project.Entity;

/**
 * Created by andrey on 18.07.15.
 */

import javax.persistence.*;
import java.util.List;

@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String text;
    private Boolean moderated;
    private Integer rating;
    private String answer;
    private Boolean answered;
    @ManyToOne
    private Speech speech;
    @ManyToMany(mappedBy = "questions")
    private List<Visitor> visitorsUpVoteQuestion;

    public Question() {
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

    public Boolean getModerated() {
        return moderated;
    }

    public void setModerated(Boolean moderated) {
        this.moderated = moderated;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Boolean getAnswered() {
        return answered;
    }

    public void setAnswered(Boolean answered) {
        this.answered = answered;
    }

    public Speech getSpeech() {
        return speech;
    }

    public void setSpeech(Speech speech) {
        this.speech = speech;
    }

    public List<Visitor> getVisitorsUpVoteQuestion() {
        return visitorsUpVoteQuestion;
    }

    public void setVisitorsUpVoteQuestion(List<Visitor> visitor) {
        this.visitorsUpVoteQuestion = visitor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Question question = (Question) o;

        return !(id != null ? !id.equals(question.id) : question.id != null);

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", moderated=" + moderated +
                ", rating=" + rating +
                ", answer='" + answer + '\'' +
                ", answered=" + answered +
                ", speech=" + speech +
                ", visitor=" + visitorsUpVoteQuestion +
                '}';
    }
}

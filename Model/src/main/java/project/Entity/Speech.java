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

    @ManyToOne
    private Speaker speaker;

    @ManyToOne
    private Room room;

    @ManyToOne
    private Conference conference;

    private Boolean completed;

    @ManyToOne
    private Reporter reporter;

    @OneToMany(mappedBy = "speech")
    private List<Question> questions;

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
}

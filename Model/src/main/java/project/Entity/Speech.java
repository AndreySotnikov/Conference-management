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

    @ManyToOne
    private Speaker speaker;

    @ManyToOne
    private Room room;

    @ManyToOne
    private Conference conference;

    private Boolean completed;

    @ManyToOne
    private Reporter reporter;
}

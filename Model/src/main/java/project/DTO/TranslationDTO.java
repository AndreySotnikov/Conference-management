package project.DTO;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by Green-L on 21.07.2015.
 */
public class TranslationDTO {

    private int id;
    private String text;
    private Timestamp timestamp;

    public TranslationDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
}

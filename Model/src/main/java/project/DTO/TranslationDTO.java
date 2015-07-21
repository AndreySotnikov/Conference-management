package project.DTO;

import java.sql.Date;

/**
 * Created by Green-L on 21.07.2015.
 */
public class TranslationDTO {

    private int id;
    private String text;
    private Date time;

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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}

package project.Entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by nikita on 19.07.15.
 */
@Entity
public class RoomOrder {
    public RoomOrder(Room room, Speech speech, Date dateFrom, Date dateTo) {
        this.room = room;
        this.speech = speech;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public RoomOrder() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne
    private Room room;
    @ManyToOne
    private Speech speech;
    private Date dateFrom;
    private Date dateTo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoomOrder roomOrder = (RoomOrder) o;

        if (id != null ? !id.equals(roomOrder.id) : roomOrder.id != null) return false;
        if (room != null ? !room.equals(roomOrder.room) : roomOrder.room != null) return false;
        if (speech != null ? !speech.equals(roomOrder.speech) : roomOrder.speech != null) return false;
        if (dateFrom != null ? !dateFrom.equals(roomOrder.dateFrom) : roomOrder.dateFrom != null) return false;
        return !(dateTo != null ? !dateTo.equals(roomOrder.dateTo) : roomOrder.dateTo != null);

    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (room != null ? room.hashCode() : 0);
        result = 31 * result + (speech != null ? speech.hashCode() : 0);
        result = 31 * result + (dateFrom != null ? dateFrom.hashCode() : 0);
        result = 31 * result + (dateTo != null ? dateTo.hashCode() : 0);
        return result;
    }

    public Integer getId() {

        return id;
    }

    public Room getRoom() {

        return room;
    }

    @Override
    public String toString() {
        return "RoomOrder{" +
                "id=" + id +
                ", room=" + room +
                ", speech=" + speech +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                '}';
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Speech getSpeech() {
        return speech;
    }

    public void setSpeech(Speech speech) {
        this.speech = speech;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }
}

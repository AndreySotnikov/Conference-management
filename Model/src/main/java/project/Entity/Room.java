/**
 * Created by nikitayakuntsev on 13.07.15.
 */
package project.Entity;
import javax.persistence.*;
import java.util.List;

@Entity
public class Room {
    public RoomOwner getRoomOwner() {
        return roomOwner;
    }

    public void setRoomOwner(RoomOwner roomOwner) {
        this.roomOwner = roomOwner;
    }

    public Room(Integer number, Integer capacity, RoomOwner roomOwner) {
        this.number = number;
        this.capacity = capacity;
        this.roomOwner = roomOwner;
        this.isBusy = false;
    }


    public Room(Integer number, Integer capacity, RoomOwner roomOwner, Boolean isBusy) {
        this.number = number;
        this.capacity = capacity;
        this.roomOwner = roomOwner;
        this.isBusy = isBusy;
    }


    @Id
    private Integer number;

    private Integer capacity;
    private Boolean isBusy;

    @OneToMany(mappedBy = "room")
    private List<Speech> speeches;

    @ManyToOne
    private RoomOwner roomOwner;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Boolean getIsBusy() {
        return isBusy;
    }

    public void setIsBusy(Boolean isBusy) {
        this.isBusy = isBusy;
    }

    public List<Speech> getSpeeches() {
        return speeches;
    }

    public void setSpeeches(List<Speech> speeches) {
        this.speeches = speeches;
    }

    public Room() {

    }

    @Override
    public String toString() {
        return "Room{" +
                "number=" + number +
                ", capacity=" + capacity +
                ", isBusy=" + isBusy +
                ", speeches=" + speeches +
                ", roomOwner=" + roomOwner +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Room room = (Room) o;

        return !(number != null ? !number.equals(room.number) : room.number != null);

    }

    @Override
    public int hashCode() {
        return number != null ? number.hashCode() : 0;
    }
}

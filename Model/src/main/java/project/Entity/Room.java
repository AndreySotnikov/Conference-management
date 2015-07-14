/**
 * Created by nikitayakuntsev on 13.07.15.
 */
package project.Entity;
import javax.persistence.*;

@Entity
public class Room {
    public RoomOwner getRoomOwner() {
        return roomOwner;
    }

    public void setRoomOwner(RoomOwner roomOwner) {
        this.roomOwner = roomOwner;
    }

    @Id
    private Integer number;

    private Integer capacity;
    private Boolean isBusy;

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

    public Room() {

    }
}

/**
 * Created by nikitayakuntsev on 13.07.15.
 */
package project.Entity;
import javax.persistence.*;
import java.util.List;

@Entity
public class Room {

    @Id
    @Column(unique = true,nullable = false)
    private Integer number;

    private Integer capacity;
    private boolean busy;

    @OneToMany(mappedBy = "room",  cascade = CascadeType.REMOVE)
    private transient List<RoomOrder> orders;

    @ManyToOne
    private RoomOwner roomOwner;

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
        this.busy = false;
    }

    public Room(Integer number, Integer capacity, RoomOwner roomOwner, boolean busy) {
        this.number = number;
        this.capacity = capacity;
        this.roomOwner = roomOwner;
        this.busy = busy;
    }



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

    public boolean isBusy() {
        return busy;
    }

    public void setBusy(boolean busy) {
        this.busy = busy;
    }

    public void setOrders(List<RoomOrder> orders) {
        this.orders = orders;
    }

    public List<RoomOrder> getOrders() {

        return orders;
    }

    public Room() {

    }

    @Override
    public String toString() {
        return "Room{" +
                "number=" + number +
                ", capacity=" + capacity +
                ", isBusy=" + busy +
                ", speeches=" + orders +
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

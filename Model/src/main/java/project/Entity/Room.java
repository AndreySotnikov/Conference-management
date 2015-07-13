/**
 * Created by nikitayakuntsev on 13.07.15.
 */
import javax.persistence.*;

@Entity
public class Room {
    @Id
    Integer number;

    Integer capacity;
    Boolean isBusy;

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

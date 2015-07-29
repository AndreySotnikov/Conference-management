package project.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Green-L on 18.07.2015.
 */
@Entity
public class RoomOwner {
    @Id
    String login;

    private String name;
    private String email;
    private String phone;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "roomOwner", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Room> rooms;

    public RoomOwner(String login, String name, String email, String phone) {
        this.login = login;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public RoomOwner() {
    }

    @Override
    public String toString() {
        return "RoomOwner{" +
                "login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", rooms=" + rooms +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoomOwner roomOwner = (RoomOwner) o;

        if (login != null ? !login.equals(roomOwner.login) : roomOwner.login != null) return false;
        if (name != null ? !name.equals(roomOwner.name) : roomOwner.name != null) return false;
        if (email != null ? !email.equals(roomOwner.email) : roomOwner.email != null) return false;
        return !(phone != null ? !phone.equals(roomOwner.phone) : roomOwner.phone != null);

    }

    @Override
    public int hashCode() {
        int result = login != null ? login.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        return result;
    }
}

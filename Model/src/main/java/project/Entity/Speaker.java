/**
 * Created by nikitayakuntsev on 13.07.15.
 */

package project.Entity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Speaker {
    @Id
    private String login;

    private String name;
    private String email;
    private String phone;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "speaker",  cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Speech> speeches;

    public Speaker(String login, String name, String email, String phone) {
        this.login = login;
        this.name = name;
        this.email = email;
        this.phone = phone;
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

    public Speaker() {

    }

    @Override
    public String toString() {
        return "Speaker{" +
                "login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", speeches=" + speeches +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Speaker speaker = (Speaker) o;

        if (login != null ? !login.equals(speaker.login) : speaker.login != null) return false;
        if (name != null ? !name.equals(speaker.name) : speaker.name != null) return false;
        if (email != null ? !email.equals(speaker.email) : speaker.email != null) return false;
        return !(phone != null ? !phone.equals(speaker.phone) : speaker.phone != null);

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
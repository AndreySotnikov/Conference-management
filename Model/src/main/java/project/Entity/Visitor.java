package project.Entity;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

/**
 * Created by nikitayakuntsev on 13.07.15.
 */

@Entity
public class Visitor {
    @Id
    private String login;

    private String name;
    private String email;
    private String phone;

    @ManyToMany(mappedBy = "visitors")
    private List<Conference> conferences;

    @ManyToMany(mappedBy = "visitors")
    private List<Speech> speeches;

    public List<Conference> getConferences() {
        return conferences;
    }

    public void setConferences(List<Conference> conferences) {
        this.conferences = conferences;
    }

    public List<Speech> getSpeeches() {
        return speeches;
    }

    public void setSpeeches(List<Speech> speeches) {
        this.speeches = speeches;
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

    public Visitor() {

    }

    @Override
    public String toString() {
        return "Visitor{" +
                "login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Visitor visitor = (Visitor) o;

        if (login != null ? !login.equals(visitor.login) : visitor.login != null) return false;
        if (name != null ? !name.equals(visitor.name) : visitor.name != null) return false;
        if (email != null ? !email.equals(visitor.email) : visitor.email != null) return false;
        return !(phone != null ? !phone.equals(visitor.phone) : visitor.phone != null);

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

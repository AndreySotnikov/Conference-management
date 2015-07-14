package project.Entity;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by nikitayakuntsev on 13.07.15.
 */

@Entity
public class Reporter {
    @Id
    private String login;

    private String name;
    private String email;
    private String phone;

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

    public Reporter() {

    }

    @Override
    public String toString() {
        return "Reporter{" +
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

        Reporter reporter = (Reporter) o;

        if (login != null ? !login.equals(reporter.login) : reporter.login != null) return false;
        if (name != null ? !name.equals(reporter.name) : reporter.name != null) return false;
        if (email != null ? !email.equals(reporter.email) : reporter.email != null) return false;
        return !(phone != null ? !phone.equals(reporter.phone) : reporter.phone != null);

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


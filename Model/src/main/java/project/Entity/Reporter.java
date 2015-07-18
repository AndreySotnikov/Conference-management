package project.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
    private boolean busy;

    public Reporter(String login, String name, String email, boolean busy) {
        this.login = login;
        this.name = name;
        this.email = email;
        this.busy = busy;
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

    public boolean isBusy() {
        return busy;
    }

    public void setBusy(boolean busy) {
        this.busy = busy;
    }

    public Reporter() {

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Reporter reporter = (Reporter) o;

        if (busy != reporter.busy) return false;
        if (login != null ? !login.equals(reporter.login) : reporter.login != null) return false;
        if (name != null ? !name.equals(reporter.name) : reporter.name != null) return false;
        return !(email != null ? !email.equals(reporter.email) : reporter.email != null);

    }

    @Override
    public int hashCode() {
        int result = login != null ? login.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (busy ? 1 : 0);
        return result;
    }
}


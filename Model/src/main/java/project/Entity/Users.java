package project.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by andrey on 14.07.15.
 */

@Entity
public class Users {
    @Id
    private String username;
    private String passwd;

    public Users(String username, String passwd) {
        this.username = username;
        this.passwd = passwd;
    }

    public Users() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Users users = (Users) o;

        if (username != null ? !username.equals(users.username) : users.username != null) return false;
        return !(passwd != null ? !passwd.equals(users.passwd) : users.passwd != null);

    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (passwd != null ? passwd.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Users{" +
                "username='" + username + '\'' +
                ", passwd='" + passwd + '\'' +
                '}';
    }
}


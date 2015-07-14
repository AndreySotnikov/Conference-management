package project.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by andrey on 14.07.15.
 */

@Entity
public class UserRoles {
    @Id
    String username;
    String role;

    public UserRoles(String username, String role) {
        this.username = username;
        this.role = role;
    }

    public UserRoles() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserRoles userRoles = (UserRoles) o;

        if (username != null ? !username.equals(userRoles.username) : userRoles.username != null) return false;
        return !(role != null ? !role.equals(userRoles.role) : userRoles.role != null);

    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserRoles{" +
                "username='" + username + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}

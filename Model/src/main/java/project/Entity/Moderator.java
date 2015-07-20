/**
 * Created by nikitayakuntsev on 13.07.15.
 */
package project.Entity;

import javax.persistence.*;
@Entity
public class Moderator {
    @Id
    private String login;
    private String name;
    private String email;
    private String phone;

    @OneToMany(mappedBy = "moderator",  cascade = CascadeType.REMOVE)
    private transient ModeratorRequestsSpeech moderatorRequestsSpeech;

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

    public ModeratorRequestsSpeech getModeratorRequestsSpeech() {
        return moderatorRequestsSpeech;
    }

    public void setModeratorRequestsSpeech(ModeratorRequestsSpeech moderatorRequestsSpeech) {
        this.moderatorRequestsSpeech = moderatorRequestsSpeech;
    }

    public Moderator(String login, String name, String email, String phone) {
        this.login = login;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public Moderator() {
    }

    @Override
    public String toString() {
        return "Moderator{" +
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

        Moderator moderator = (Moderator) o;

        if (login != null ? !login.equals(moderator.login) : moderator.login != null) return false;
        if (name != null ? !name.equals(moderator.name) : moderator.name != null) return false;
        if (email != null ? !email.equals(moderator.email) : moderator.email != null) return false;
        return !(phone != null ? !phone.equals(moderator.phone) : moderator.phone != null);

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

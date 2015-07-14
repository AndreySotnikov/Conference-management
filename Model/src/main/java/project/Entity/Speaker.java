/**
 * Created by nikitayakuntsev on 13.07.15.
 */

package project.Entity;
import javax.persistence.*;
import java.util.List;

@Entity
public class Speaker {
    @Id
    private String login;

    private String name;
    private String email;
    private String phone;

    @OneToMany(mappedBy = "speaker")
    private List<Speech> speeches;

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
}
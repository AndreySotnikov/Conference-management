package project.Service;

import project.Entity.Speech;
import project.Entity.UserRoles;
import project.Util.CrudImplementation;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.List;

/**
 * Created by andrey on 25.07.15.
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class UserRolesService extends CrudImplementation {
    public String whoAmI(String login) {
        try {
            UserRoles ur = (UserRoles)em.createQuery("select e from UserRoles e where e.username=:login")
                    .setParameter("login", login).getResultList().get(0);
            return ur.getRole();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}

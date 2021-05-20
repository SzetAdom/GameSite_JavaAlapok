
package Repository;

import Model.User;
import Model.Database;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

public class UserRepo {
    public static boolean addNewUser(User user){
        try{
            EntityManager em = Database.getDbConn();
            
            StoredProcedureQuery spq =  em.createStoredProcedureQuery("addNewUser");
            
            spq.registerStoredProcedureParameter("usernameIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("passwordIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("emailIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("birth_dateIN", Date.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("isadminIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("currentpointsIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("isactiveIN", Integer.class, ParameterMode.IN);
            
            spq.setParameter("usernameIN", user.getUsername());
            spq.setParameter("passwordIN", user.getPassword());
            spq.setParameter("emailIN", user.getEmail());
            spq.setParameter("birth_dateIN", user.getBirthDate());
            spq.setParameter("isadminIN", user.getIsadmin());
            spq.setParameter("currentpointsIN", user.getCurrentpoints());
            spq.setParameter("isactiveIN", user.getIsactive());
            
            spq.execute();
            
            return true;
        }
        catch(Exception ex){
            return false;
        }
    }
    
    public static boolean updateUser(User input){        
        try{
            EntityManager em = Database.getDbConn();
            em.getTransaction().begin();
              
            User newUser = em.find(User.class, input.getUserId());
            newUser.setUsername(input.getUsername());
            newUser.setPassword(input.getPassword());
            newUser.setEmail(input.getEmail());
            newUser.setBirthDate(input.getBirthDate());
            newUser.setIsadmin(input.getIsadmin());
            newUser.setCurrentpoints(input.getCurrentpoints());
            newUser.setIsactive(input.getIsactive());
            
            em.getTransaction().commit();
            return true;
        }
        catch(Exception ex){
            return false;
        }
    }
}

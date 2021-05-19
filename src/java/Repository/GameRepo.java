package Repository;

import Model.Database;
import Model.Game;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

/**
 *
 * @author adams
 */
public class GameRepo {

    public static Boolean addGame(Game game) {
        try {

            EntityManager em = Database.getDbConn();
            try {
                StoredProcedureQuery spq = em.createStoredProcedureQuery("addGame");

                spq.registerStoredProcedureParameter("in_name", String.class, ParameterMode.IN);
                spq.registerStoredProcedureParameter("in_category", String.class, ParameterMode.IN);
                spq.registerStoredProcedureParameter("in_description", String.class, ParameterMode.IN);
                spq.registerStoredProcedureParameter("in_release", Date.class, ParameterMode.IN);

                spq.setParameter("in_name", game.getName());
                spq.setParameter("in_category", game.getCategory());
                spq.setParameter("in_description", game.getDescription());
                spq.setParameter("in_release", game.getReleasedate());

                spq.execute();

                em.close();

                System.out.println("Game sikeresen hozzáadva!");
                return true;

            } catch (Exception ex) {
                em.close();

                System.out.println("Hiba: addGame" + ex.getMessage());
                return false;
            }
        } catch (Exception e) {
            System.out.println("Database connection hiba! - " + e.getMessage());
            return false;
        }
    }

}

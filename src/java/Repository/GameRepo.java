package Repository;

import Model.Database;
import Model.Game;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

                System.out.println("Hiba: addGame - " + ex.getMessage());
                return false;
            }
        } catch (Exception e) {
            System.out.println("Database connection hiba! - " + e.getMessage());
            return false;
        }
    }

    public static List<Game> getAllActiveGames() {

        try {

            EntityManager em = Database.getDbConn();
            try {
                StoredProcedureQuery spq = em.createStoredProcedureQuery("getAllActiveGames");
                List<Game> result = new ArrayList<>();

                List<Object[]> games = spq.getResultList();
                for (Object[] game : games) {
                    int id = Integer.parseInt(game[0].toString());
                    Game g = em.find(Game.class, id);
                    result.add(g);
                }

                em.close();

                return result;

            } catch (Exception ex) {
                em.close();

                System.out.println("Hiba: getAllActiveGames - " + ex.getMessage());
                return null;
            }
        } catch (Exception e) {
            System.out.println("Database connection hiba! - " + e.getMessage());
            return null;
        }
    }

    public static Game getGameById(Integer id) {

        try {

            EntityManager em = Database.getDbConn();
            try {
                Game game = em.find(Game.class, id);
                em.close();
                return game;

            } catch (Exception ex) {
                em.close();

                System.out.println("Hiba: getGameById - " + ex.getMessage());
                return null;
            }
        } catch (Exception e) {
            System.out.println("Database connection hiba! - " + e.getMessage());
            return null;
        }
    }

    public static Boolean updateGame(Game game) {
        try {

            EntityManager em = Database.getDbConn();
            try {
                em.getTransaction().begin();
                Game uj = em.find(Game.class, game.getGameId());
                uj.setName(game.getName());
                uj.setCategory(game.getCategory());
                uj.setDescription(game.getDescription());
                uj.setReleasedate(game.getReleasedate());
                em.getTransaction().commit();

                System.out.println("Game sikeresen frissítve!");
                return true;

            } catch (Exception ex) {
                em.close();

                System.out.println("Hiba: updateGame - " + ex.getMessage());
                return false;
            }
        } catch (Exception e) {
            System.out.println("Database connection hiba! - " + e.getMessage());
            return false;
        }
    }

    public static Boolean deleteGame(Integer id) {
        try {

            EntityManager em = Database.getDbConn();
            try {
                StoredProcedureQuery spq = em.createStoredProcedureQuery("logicalDeleteGame");

                spq.registerStoredProcedureParameter("in_id", Integer.class, ParameterMode.IN);

                spq.setParameter("in_id", id);

                spq.execute();

                em.close();

                System.out.println("Game sikeresen törölve!");
                return true;

            } catch (Exception ex) {
                em.close();

                System.out.println("Hiba: deleteGame - " + ex.getMessage());
                return false;
            }
        } catch (Exception e) {
            System.out.println("Database connection hiba! - " + e.getMessage());
            return false;
        }
    }

    public static Boolean likeGame(Integer id) {
        try {

            EntityManager em = Database.getDbConn();
            try {
                StoredProcedureQuery spq = em.createStoredProcedureQuery("likeGame");

                spq.registerStoredProcedureParameter("in_id", Integer.class, ParameterMode.IN);

                spq.setParameter("in_id", id);

                spq.execute();

                em.close();

                System.out.println("Game sikeresen likeolva!");
                return true;

            } catch (Exception ex) {
                em.close();

                System.out.println("Hiba: likeGame" + ex.getMessage());
                return false;
            }
        } catch (Exception e) {
            System.out.println("Database connection hiba! - " + e.getMessage());
            return false;
        }
    }

    public static Boolean dislikeGame(Integer id) {
        try {

            EntityManager em = Database.getDbConn();
            try {
                StoredProcedureQuery spq = em.createStoredProcedureQuery("dislikeGame");

                spq.registerStoredProcedureParameter("in_id", Integer.class, ParameterMode.IN);

                spq.setParameter("in_id", id);

                spq.execute();

                em.close();

                System.out.println("Game sikeresen dislikeolva!");
                return true;

            } catch (Exception ex) {
                em.close();

                System.out.println("Hiba: dislikeGame - " + ex.getMessage());
                return false;
            }
        } catch (Exception e) {
            System.out.println("Database connection hiba! - " + e.getMessage());
            return false;
        }
    }

}
